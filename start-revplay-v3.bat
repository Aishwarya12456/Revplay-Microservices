@echo off

echo =====================================
echo Starting RevPlay Microservices (v3)
echo =====================================

REM ---------------- EUREKA ----------------
echo Starting EUREKA SERVER...
start cmd /k "cd revplay-eureka-server\target && for %%i in (*.jar) do java -jar %%i"

echo Waiting for Eureka...
:loopEureka
curl -s http://localhost:8761 >nul
if errorlevel 1 (
 ping -n 3 127.0.0.1 >nul
 goto loopEureka
)
echo Eureka Started!

REM ---------------- CONFIG ----------------
echo Starting CONFIG SERVER...
start cmd /k "cd revplay-config-server\target && for %%i in (*.jar) do java -jar %%i"

echo Waiting for Config Server...
:loopConfig
curl -s http://localhost:8888 >nul
if errorlevel 1 (
 ping -n 3 127.0.0.1 >nul
 goto loopConfig
)
echo Config Server Started!

REM ---------------- USER SERVICE ----------------
echo Starting USER SERVICE...
start cmd /k "cd revplay-user-service\target && for %%i in (*.jar) do java -jar %%i --spring.profiles.active=local"

echo Waiting for User Service...
:loopUser
curl -s http://localhost:8085/actuator/health >nul
if errorlevel 1 (
 ping -n 3 127.0.0.1 >nul
 goto loopUser
)
echo User Service Started!

REM ---------------- CATALOG SERVICE ----------------
echo Starting CATALOG SERVICE...
start cmd /k "cd revplay-catalog-service\target && for %%i in (*.jar) do java -jar %%i --spring.profiles.active=local"

echo Waiting for Catalog Service...
:loopCatalog
curl -s http://localhost:8082/actuator/health >nul
if errorlevel 1 (
 ping -n 3 127.0.0.1 >nul
 goto loopCatalog
)
echo Catalog Service Started!

REM ---------------- PLAYLIST SERVICE ----------------
echo Starting PLAYLIST SERVICE...
start cmd /k "cd revplay-playlist-service\target && for %%i in (*.jar) do java -jar %%i --spring.profiles.active=local"

echo Waiting for Playlist Service...
:loopPlaylist
curl -s http://localhost:8083/actuator/health >nul
if errorlevel 1 (
 ping -n 3 127.0.0.1 >nul
 goto loopPlaylist
)
echo Playlist Service Started!

REM ---------------- PLAYBACK SERVICE ----------------
echo Starting PLAYBACK SERVICE...
start cmd /k "cd revplay-playback-service\target && for %%i in (*.jar) do java -jar %%i --spring.profiles.active=local"

echo Waiting for Playback Service...
:loopPlayback
curl -s http://localhost:8086/actuator/health >nul
if errorlevel 1 (
 ping -n 3 127.0.0.1 >nul
 goto loopPlayback
)
echo Playback Service Started!

REM ---------------- FAVORITES SERVICE ----------------
echo Starting FAVORITES SERVICE...
start cmd /k "cd revplay-favorites-service\target && for %%i in (*.jar) do java -jar %%i --spring.profiles.active=local"

echo Waiting for Favorites Service...
:loopFavorites
curl -s http://localhost:8084/actuator/health >nul
if errorlevel 1 (
 ping -n 3 127.0.0.1 >nul
 goto loopFavorites
)
echo Favorites Service Started!

REM ---------------- ANALYTICS SERVICE ----------------
echo Starting ANALYTICS SERVICE...
start cmd /k "cd revplay-analytics-service\target && for %%i in (*.jar) do java -jar %%i --spring.profiles.active=local"

echo Waiting for Analytics Service...
:loopAnalytics
curl -s http://localhost:8087/actuator/health >nul
if errorlevel 1 (
 ping -n 3 127.0.0.1 >nul
 goto loopAnalytics
)
echo Analytics Service Started!

REM ---------------- API GATEWAY ----------------
echo Starting API GATEWAY...
start cmd /k "cd revplay-api-gateway\target && for %%i in (*.jar) do java -jar %%i"

echo =====================================
echo RevPlay System Started Successfully
echo =====================================
