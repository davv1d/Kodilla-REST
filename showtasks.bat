call runcrud.bat
if "%ERRORLEVEL%" == "0" goto open
echo.
echo runcrud.bat has errors - breaking work
goto fail

:open
sleep 5
start "Firefox!" "C:\Program Files\Mozilla Firefox\firefox.exe" http://localhost:8080/crud/v1/task/getTasks
goto end

:fail
echo.
echo There were errors

:end
echo.
echo Work is finished.