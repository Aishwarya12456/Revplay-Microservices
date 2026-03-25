
$services = @(
    @{ name = "eureka"; path = "revplay-eureka-server\target\revplay-eureka-server-0.0.1-SNAPSHOT.jar"; port = 8761 },
    @{ name = "config"; path = "revplay-config-server\target\revplay-config-server-0.0.1-SNAPSHOT.jar"; port = 8888 },
    @{ name = "user"; path = "revplay-user-service\target\revplay-user-service-0.0.1-SNAPSHOT.jar"; port = 8085; profile = "local" },
    @{ name = "catalog"; path = "revplay-catalog-service\target\revplay-catalog-service-0.0.1-SNAPSHOT.jar"; port = 8082; profile = "local" },
    @{ name = "favorites"; path = "revplay-favorites-service\target\revplay-favorites-service-0.0.1-SNAPSHOT.jar"; port = 8084; profile = "local" },
    @{ name = "analytics"; path = "revplay-analytics-service\target\revplay-analytics-service-0.0.1-SNAPSHOT.jar"; port = 8087; profile = "local" },
    @{ name = "gateway"; path = "revplay-api-gateway\target\revplay-api-gateway-0.0.1-SNAPSHOT.jar"; port = 8080 }
)

Write-Host "Stopping any existing Java processes..."
Stop-Process -Name java -Force -ErrorAction SilentlyContinue
Start-Sleep -Seconds 2

foreach ($s in $services) {
    Write-Host "Starting $($s.name)..."
    $args = @("-jar", $s.path)
    if ($s.profile) { $args += "--spring.profiles.active=$($s.profile)" }
    
    Start-Process java -ArgumentList $args -NoNewWindow
    
    if ($s.name -eq "eureka" -or $s.name -eq "config") {
        Write-Host "Waiting for $($s.name) to initialize..."
        Start-Sleep -Seconds 15
    } else {
        Start-Sleep -Seconds 5
    }
}

Write-Host "All services started in background. Please wait 30 seconds for them to fully initialize."
