$ports = @(8761, 8888, 8080, 8081, 8082, 8083, 8084, 8085, 8086, 8087)
foreach ($port in $ports) {
    $proc = Get-NetTCPConnection -LocalPort $port -ErrorAction SilentlyContinue
    if ($proc) {
        $id = $proc.OwningProcess | Select-Object -Unique
        Write-Host "Stopping process $id on port $port"
        Stop-Process -Id $id -Force -ErrorAction SilentlyContinue
    }
}

Write-Host "Starting Eureka Server..."
Start-Process java -ArgumentList "-jar", "revplay-eureka-server\target\revplay-eureka-server-0.0.1-SNAPSHOT.jar" -NoNewWindow -RedirectStandardOutput "eureka.log" -RedirectStandardError "eureka_err.log"

while (!(Test-NetConnection -Port 8761 -ComputerName localhost -ErrorAction SilentlyContinue).TcpTestSucceeded) { 
    Write-Host "Waiting for Eureka..."
    Start-Sleep -s 2 
}
Write-Host "Eureka is Up."

Write-Host "Starting Config Server..."
Start-Process java -ArgumentList "-Dspring.profiles.active=native", "-Dspring.cloud.config.server.native.search-locations=file:f:/springrev/Revplay-Microservice/config-repo/", "-jar", "revplay-config-server\target\revplay-config-server-0.0.1-SNAPSHOT.jar" -NoNewWindow -RedirectStandardOutput "config.log" -RedirectStandardError "config_err.log"

while (!(Test-NetConnection -Port 8888 -ComputerName localhost -ErrorAction SilentlyContinue).TcpTestSucceeded) { 
    Write-Host "Waiting for Config Server..."
    Start-Sleep -s 1 
}
Write-Host "Config Server is Up."

Write-Host "Starting other Microservices..."
$services = @(
    "revplay-api-gateway",
    "revplay-user-service",
    "revplay-catalog-service",
    "revplay-playlist-service",
    "revplay-playback-service",
    "revplay-favorites-service",
    "revplay-analytics-service"
)

$env:SPRING_CLOUD_CONFIG_URI = "http://localhost:8888"

foreach ($service in $services) {
    Write-Host "Starting $service..."
    $jar = Get-ChildItem "$service\target\*.jar" | Select-Object -First 1
    if ($jar) {
        $props = "-Dspring.profiles.active=local", "-Dspring.cloud.config.uri=http://localhost:8888", "-Dspring.cloud.config.discovery.enabled=false"
        Start-Process java -ArgumentList @($props + "-jar" + "$($jar.FullName)") -NoNewWindow -RedirectStandardOutput "$service.log" -RedirectStandardError "$($service)_err.log"
    } else {
        Write-Host "Error: JAR file not found for $service"
    }
}

Write-Host "All backend services initiated. check the .log files for status."
