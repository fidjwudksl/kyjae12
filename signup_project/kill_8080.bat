@echo off
echo =============================
echo [ 포트 8080 점유 프로세스 종료 ]
echo =============================

for /f "tokens=5" %%a in ('netstat -ano ^| findstr :8080') do (
  echo 종료 중: PID %%a
  taskkill /F /PID %%a
)

pause