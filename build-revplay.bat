@echo off

set MAVEN_OPTS="-Dmaven.repo.local=f:\springrev\maven-repo-revplay"
set MVN_CMD=call mvn %MAVEN_OPTS% install -DskipTests

echo =====================================
echo Building RevPlay Microservices
echo Using local repo: f:\springrev\maven-repo-revplay
echo =====================================

echo Building Eureka Server...
cd revplay-eureka-server
%MVN_CMD%
if errorlevel 1 goto error

echo Building Security Library...
cd ..\revplay-security
%MVN_CMD%
if errorlevel 1 goto error

echo Building Config Server...
cd ..\revplay-config-server
%MVN_CMD%
if errorlevel 1 goto error

echo Building User Service...
cd ..\revplay-user-service
%MVN_CMD%
if errorlevel 1 goto error

echo Building Catalog Service...
cd ..\revplay-catalog-service
%MVN_CMD%
if errorlevel 1 goto error

echo Building Playlist Service...
cd ..\revplay-playlist-service
%MVN_CMD%
if errorlevel 1 goto error

echo Building Playback Service...
cd ..\revplay-playback-service
%MVN_CMD%
if errorlevel 1 goto error

echo Building Favorites Service...
cd ..\revplay-favorites-service
%MVN_CMD%
if errorlevel 1 goto error

echo Building Analytics Service...
cd ..\revplay-analytics-service
%MVN_CMD%
if errorlevel 1 goto error

echo Building API Gateway...
cd ..\revplay-api-gateway
%MVN_CMD%
if errorlevel 1 goto error

cd ..

echo =====================================
echo All JARs built successfully
echo =====================================
goto end

:error
echo =====================================
echo BUILD FAILED!
echo =====================================
exit /b 1

:end
pause