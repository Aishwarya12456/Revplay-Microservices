function Start-Microservice {
    param($name, $path, $port, $overrides = "")
    Write-Host "Starting $name on port $port..."
    $args = @("-jar", "$path", "--server.port=$port")
    if ($overrides) {
        $args += $overrides.Split(" ")
    }
    Start-Process java -ArgumentList $args -NoNewWindow -RedirectStandardOutput "f:\tmp\$name.log" -RedirectStandardError "f:\tmp\$name`_err.log"
}

# Start Essential Infrastructure
Start-Microservice "eureka" "revplay-eureka-server\target\revplay-eureka-server-0.0.1-SNAPSHOT.jar" 8761
Start-Sleep -Seconds 12
Start-Microservice "config" "revplay-config-server\target\revplay-config-server-0.0.1-SNAPSHOT.jar" 8888
Start-Sleep -Seconds 12

# Start Core Services
Start-Microservice "user" "revplay-user-service\target\revplay-user-service-0.0.1-SNAPSHOT.jar" 8085 "--spring.profiles.active=local"
Start-Microservice "catalog" "revplay-catalog-service\target\revplay-catalog-service-0.0.1-SNAPSHOT.jar" 8082 "--spring.profiles.active=local"
Start-Microservice "playback" "revplay-playback-service\target\revplay-playback-service-0.0.1-SNAPSHOT.jar" 8086 "--spring.profiles.active=local"

# Start Social Services with DB Overrides
$dbCreds = "--spring.datasource.username=root --spring.datasource.password=Aishu@123"
Start-Microservice "analytics" "revplay-analytics-service\target\revplay-analytics-service-0.0.1-SNAPSHOT.jar" 8087 "--spring.profiles.active=local $dbCreds --spring.datasource.url=`"jdbc:mysql://localhost:3306/revplay_analytics_db?createDatabaseIfNotExist=true&zeroDateTimeBehavior=convertToNull`""
Start-Microservice "favorites" "revplay-favorites-service\target\revplay-favorites-service-0.0.1-SNAPSHOT.jar" 8084 "--spring.profiles.active=local $dbCreds --spring.datasource.url=`"jdbc:mysql://localhost:3306/revplay_favorites_db?createDatabaseIfNotExist=true&zeroDateTimeBehavior=convertToNull`""
Start-Microservice "playlist" "revplay-playlist-service\target\revplay-playlist-service-0.0.1-SNAPSHOT.jar" 8083 "--spring.profiles.active=local $dbCreds --spring.datasource.url=`"jdbc:mysql://localhost:3306/revplay_playlist_db?createDatabaseIfNotExist=true&zeroDateTimeBehavior=convertToNull`""

# Start Gateway
Start-Microservice "gateway" "revplay-api-gateway\target\revplay-api-gateway-0.0.1-SNAPSHOT.jar" 8080
