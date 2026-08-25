@echo off
echo ========================================
echo  Running TestNG Suite + Submitting to Testmo
echo ========================================

:: --- CONFIG ---
set TESTMO_TOKEN=testmo_api_eyJpdiI6IkJOL0FTeG45eGh4V3h6dkJKNWQrbEE9PSIsInZhbHVlIjoiWmdyWVBGT3V2dVVZSEFOVXJScG13VmRKbGtUOFN4U2FlMit5NC9sNCthWjFhSE5WZTlEN25rWTlmakZ0SjdUQiIsIm1hYyI6IjRiN2QyZGQ4N2NkOWU4OGEzYzNlNjI0OTkwNGU5ODNhMWQ1MmNhZTA4M2MyYmUwZjJiNjIzMDFhM2FmYjk3YjkiLCJ0YWciOiIifQ==
set TESTMO_INSTANCE=https://rapidops.testmo.net
set PROJECT_ID=1
set MVN=C:\Users\vanita.patel\apache-maven\apache-maven-3.9.16\bin\mvn.cmd
set TESTMO_CMD=C:\Users\vanita.patel\AppData\Roaming\npm\testmo.cmd
set SUITE=Testng regression.xml
:: --------------

echo.
echo Step 1: Running TestNG suite: %SUITE%
echo.

call "%MVN%" clean test -DsuiteXmlFile="%SUITE%"

echo.
echo Step 2: Submitting results to Testmo...
echo.

for %%f in (target\surefire-reports\TEST-*.xml) do (
    echo Submitting: %%f
    call "%TESTMO_CMD%" automation:run:submit --instance %TESTMO_INSTANCE% --project-id %PROJECT_ID% --name "Regression Run" --source testng --results "%%f"
)

echo.
echo Done! Check results at: %TESTMO_INSTANCE%/projects/view/%PROJECT_ID%
echo.
pause
