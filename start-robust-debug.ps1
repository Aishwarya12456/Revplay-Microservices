function Start-Microservice {
    param($name, $path, $port, $overrides = "")
    Write-Host "Starting $name on port $port..."
    $args = @("-jar", "$path", "--server.port=$port")
    if ($overrides) {
        # Fix for overrides parsing
        $args += $overrides.Split(" ", [System.StringSplitOptions]::RemoveEmptyEntries)
    }
    # Using -RedirectStandardOutput and -PassThru to see if we can get better control
    $process = Start-Process java -ArgumentList $args -NoNewWindow -RedirectStandardOutput "f:\tmp\$name.log" -RedirectStandardError "f:\tmp\$name`_err.log" -PassThru
    return $process
}

function Wait-For-URL {
    param($url, $timeoutSeconds = 60)
    Write-Host "Waiting for $url..."
    $start = Get-Date
    while ($true) {
        try {
            $response = Invoke-WebRequest -Uri $url -Method Get -TimeoutSec 2 -ErrorAction Stop
            if ($response.StatusCode -eq 200) {
                Write-Host "$url is ready!"
                return $true
            }
        } catch {
            Write-Host "Wait-For-URL ($url) error: $($_.Exception.Message)"
        }
        
        if (((Get-Date) - $start).TotalSeconds -gt $timeoutSeconds) {
            Write-Warning "Timed out waiting for $url"
            return $false
        }
        Start-Sleep -Seconds 3
    }
}

# 1. Stop all Java processes
Write-Host "Stopping all Java processes..."
Stop-Process -Name java -Force -ErrorAction SilentlyContinue
Start-Sleep -Seconds 2

# 2. Start Eureka
Start-Microservice "eureka" "revplay-eureka-server\target\revplay-eureka-server-0.0.1-SNAPSHOT.jar" 8761
Wait-For-URL "http://127.0.0.1:8761" 60

# 3. Start Config Server
Start-Microservice "config" "revplay-config-server\target\revplay-config-server-0.0.1-SNAPSHOT.jar" 8888
Wait-For-URL "http://127.0.0.1:8888/revplay-catalog-service/local" 90

# 4. Start Core Services
Start-Microservice "user" "revplay-user-service\target\revplay-user-service-0.0.1-SNAPSHOT.jar" 8085 "--spring.profiles.active=local"
Start-Microservice "catalog" "revplay-catalog-service\target\revplay-catalog-service-0.0.1-SNAPSHOT.jar" 8082 "--spring.profiles.active=local"
Start-Microservice "playback" "revplay-playback-service\target\revplay-playback-service-0.0.1-SNAPSHOT.jar" 8086 "--spring.profiles.active=local"

# 5. Start Social Services with DB Overrides
$dbCreds = "--spring.datasource.username=root --spring.datasource.password=Aishu@123"
# Use escaped double quotes for the URL parameter
Start-Microservice "analytics" "revplay-analytics-service\target\revplay-analytics-service-0.0.1-SNAPSHOT.jar" 8087 "--spring.profiles.active=local $dbCreds --spring.datasource.url=`"jdbc:mysql://127.0.0.1:3306/revplay_analytics_db?createDatabaseIfNotExist=true&zeroDateTimeBehavior=convertToNull`""
Start-Microservice "favorites" "revplay-favorites-service\target\revplay-favorites-service-0.0.1-SNAPSHOT.jar" 8084 "--spring.profiles.active=local $dbCreds --spring.datasource.url=`"jdbc:mysql://127.0.0.1:3306/revplay_favorites_db?createDatabaseIfNotExist=true&zeroDateTimeBehavior=convertToNull`""
Start-Microservice "playlist" "revplay-playlist-service\target\revplay-playlist-service-0.0.1-SNAPSHOT.jar" 8083 "--spring.profiles.active=local $dbCreds --spring.datasource.url=`"jdbc:mysql://127.0.0.1:3306/revplay_playlist_db?createDatabaseIfNotExist=true&zeroDateTimeBehavior=convertToNull`""

# 6. Start Gateway
Start-Microservice "gateway" "revplay-api-gateway\target\revplay-api-gateway-0.0.1-SNAPSHOT.jar" 8080

Write-Host "Requested all services to start. Waiting for them to initialize..."
Start-Sleep -Seconds 30
串串
