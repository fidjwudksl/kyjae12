@echo off
echo =============================
echo [ Spring Boot 프로젝트 실행 (UTF-8) ]
echo =============================

REM 경로 설정 (필요 시 수정)
cd /d %~dp0

REM 빌드
echo 빌드 중...
gradlew build -Dfile.encoding=UTF-8

IF %ERRORLEVEL% NEQ 0 (
    echo 빌드 실패. 오류를 확인하세요.
    pause
    exit /b
)

REM 실행
echo 실행 중...
gradlew bootRun -Dfile.encoding=UTF-8

pause