# Function to start a microservice
function Start-Microservice {
    param($name, $port, $jarPath, $logPath)
    Write-Host "Starting $name on port $port..."
    $args = @("-jar", "$jarPath", "--spring.profiles.active=local", "--server.port=$port", "--spring.datasource.username=$DB_USER", "--spring.datasource.password=$DB_PASS")
    Start-Process java -ArgumentList $args -RedirectStandardOutput "$logPath.log" -RedirectStandardError "$logPath.err" -WindowStyle Hidden
}

# 1. Eureka
Start-Microservice "eureka" 8761 "f:\springrev\Revplay-Microservice\revplay-eureka-server\target\revplay-eureka-server-0.0.1-SNAPSHOT.jar" "f:\tmp\eureka.log"
Write-Host "Waiting for Eureka (15s)..."
Start-Sleep -Seconds 15

# 2. Config
Start-Microservice "config" 8888 "f:\springrev\Revplay-Microservice\revplay-config-server\target\revplay-config-server-0.0.1-SNAPSHOT.jar" "f:\tmp\config.log"
Write-Host "Waiting for Config Server (20s)..."
Start-Sleep -Seconds 20

# 3. Core Services
Start-Microservice "user" 8085 "f:\springrev\Revplay-Microservice\revplay-user-service\target\revplay-user-service-0.0.1-SNAPSHOT.jar" "f:\tmp\user.log"
Start-Microservice "catalog" 8082 "f:\springrev\Revplay-Microservice\revplay-catalog-service\target\revplay-catalog-service-0.0.1-SNAPSHOT.jar" "f:\tmp\catalog.log"
Start-Microservice "playback" 8086 "f:\springrev\Revplay-Microservice\revplay-playback-service\target\revplay-playback-service-0.0.1-SNAPSHOT.jar" "f:\tmp\playback.log"
Write-Host "Waiting for Core Services (20s)..."
Start-Sleep -Seconds 20

# 4. Feature Services
Start-Microservice "analytics" 8087 "f:\springrev\Revplay-Microservice\revplay-analytics-service\target\revplay-analytics-service-0.0.1-SNAPSHOT.jar" "f:\tmp\analytics.log"
Start-Microservice "favorites" 8084 "f:\springrev\Revplay-Microservice\revplay-favorites-service\target\revplay-favorites-service-0.0.1-SNAPSHOT.jar" "f:\tmp\favorites.log"
Start-Microservice "playlist" 8083 "f:\springrev\Revplay-Microservice\revplay-playlist-service\target\revplay-playlist-service-0.0.1-SNAPSHOT.jar" "f:\tmp\playlist.log"

# 5. Gateway
Start-Microservice "gateway" 8080 "f:\springrev\Revplay-Microservice\revplay-api-gateway\target\revplay-api-gateway-0.0.1-SNAPSHOT.jar" "f:\tmp\gateway.log"

Write-Host "All services requested to start. Final wait (30s)..."
Start-Sleep -Seconds 30
Write-Host "Done! Check jps and logs."
串串
