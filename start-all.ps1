$services = @(
    @{ name = "Eureka"; path = "revplay-eureka-server/target/revplay-eureka-server-0.0.1-SNAPSHOT.jar"; port = 8761; args = "" },
    @{ name = "Config"; path = "revplay-config-server/target/revplay-config-server-0.0.1-SNAPSHOT.jar"; port = 8888; args = "--spring.profiles.active=native --spring.cloud.config.server.native.search-locations=file:F:/config-repo-temp/" },
    @{ name = "Gateway"; path = "revplay-api-gateway/target/revplay-api-gateway-0.0.1-SNAPSHOT.jar"; port = 8080; args = "" },
    @{ name = "User"; path = "revplay-user-service/target/revplay-user-service-0.0.1-SNAPSHOT.jar"; port = 8085; args = "--spring.profiles.active=local" },
    @{ name = "Catalog"; path = "revplay-catalog-service/target/revplay-catalog-service-0.0.1-SNAPSHOT.jar"; port = 8082; args = "--spring.profiles.active=local" },
    @{ name = "Playlist"; path = "revplay-playlist-service/target/revplay-playlist-service-0.0.1-SNAPSHOT.jar"; port = 8083; args = "--spring.profiles.active=local" },
    @{ name = "Favorites"; path = "revplay-favorites-service/target/revplay-favorites-service-0.0.1-SNAPSHOT.jar"; port = 8084; args = "--spring.profiles.active=local" },
    @{ name = "Playback"; path = "revplay-playback-service/target/revplay-playback-service-0.0.1-SNAPSHOT.jar"; port = 8086; args = "--spring.profiles.active=local" },
    @{ name = "Analytics"; path = "revplay-analytics-service/target/revplay-analytics-service-0.0.1-SNAPSHOT.jar"; port = 8087; args = "--spring.profiles.active=local" }
)

Write-Host "Cleaning up existing processes..."
Stop-Process -Name "java" -Force -ErrorAction SilentlyContinue
Stop-Process -Name "node" -Force -ErrorAction SilentlyContinue

foreach ($svc in $services) {
    Write-Host "Starting $($svc.name)..."
    $procArgs = @("-Xmx512m", "-jar", $svc.path)
    if ($svc.args -ne "") {
        $procArgs += $svc.args -split " "
    }
    Start-Process java -ArgumentList $procArgs -WindowStyle Minimized
    
    # Wait for port to be active
    Write-Host "Waiting for $($svc.name) on port $($svc.port)..."
    $timeout = 60
    $elapsed = 0
    while (-not (Test-NetConnection localhost -Port $svc.port -ErrorAction SilentlyContinue).TcpTestSucceeded -and $elapsed -lt $timeout) {
        Start-Sleep -Seconds 3
        $elapsed += 3
    }
    if ($elapsed -ge $timeout) {
        Write-Warning "Timeout waiting for $($svc.name) on port $($svc.port)."
    } else {
        Write-Host "$($svc.name) is up!"
    }
}

Write-Host "ALL BACKEND SERVICES UP. STARTING FRONTEND..."
cd revplay-frontend
Stop-Process -Name "node" -Force -ErrorAction SilentlyContinue
Start-Process npm -ArgumentList "start" -WindowStyle Minimized
Write-Host "Frontend starting on port 4200..."
