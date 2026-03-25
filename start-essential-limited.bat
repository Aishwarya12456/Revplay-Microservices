@echo off
set JAVA_OPTS=-Xmx256m -Dmaven.repo.local=f:\springrev\maven-repo-revplay -Djava.io.tmpdir=f:\springrev\tmp -Dspring.profiles.active=local

echo Starting Eureka Server...
start "EUREKA" cmd /c "mvn spring-boot:run -Dspring-boot.run.jvmArguments=\"%JAVA_OPTS%\" -f revplay-eureka-server"

timeout /t 20

echo Starting Config Server...
start "CONFIG" cmd /c "mvn spring-boot:run -Dspring-boot.run.jvmArguments=\"%JAVA_OPTS%\" -f revplay-config-server"

timeout /t 20

echo Starting User Service...
start "USER" cmd /c "mvn spring-boot:run -Dspring-boot.run.jvmArguments=\"%JAVA_OPTS%\" -f revplay-user-service"

timeout /t 20

echo Starting Catalog Service...
start "CATALOG" cmd /c "mvn spring-boot:run -Dspring-boot.run.jvmArguments=\"%JAVA_OPTS%\" -f revplay-catalog-service"

timeout /t 20

echo Starting API Gateway...
start "GATEWAY" cmd /c "mvn spring-boot:run -Dspring-boot.run.jvmArguments=\"%JAVA_OPTS%\" -f revplay-api-gateway"

echo Essential services starting. Please wait for them to register with Eureka (8761).
