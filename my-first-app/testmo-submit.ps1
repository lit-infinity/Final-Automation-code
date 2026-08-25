# -------------------------------------------------------
# Testmo Result Submission Script
# Usage: .\testmo-submit.ps1
# Requires: Node.js + @testmo/testmo-cli installed globally
# -------------------------------------------------------

# --- CONFIGURE THESE ---
$TESTMO_INSTANCE = "https://rapidops.testmo.net"        # Your Testmo URL
$TESTMO_TOKEN    = $env:TESTMO_TOKEN                    # Set this as an env variable
$PROJECT_ID      = "1"                                  # Your Testmo project ID
$RUN_NAME        = "Regression Run - $(Get-Date -Format 'yyyy-MM-dd HH:mm')"
$RESULTS_PATH    = "target\surefire-reports\*.xml"
$SUITE_FILE      = "Testng regression.xml"
# -----------------------

Write-Host "========================================" -ForegroundColor Cyan
Write-Host " Step 1: Running TestNG tests via Maven" -ForegroundColor Cyan
Write-Host "========================================" -ForegroundColor Cyan

& "C:\Users\vanita.patel\apache-maven\apache-maven-3.9.16\bin\mvn.cmd" clean test -DsuiteXmlFile="$SUITE_FILE"

$mvnExitCode = $LASTEXITCODE

Write-Host ""
Write-Host "========================================" -ForegroundColor Cyan
Write-Host " Step 2: Submitting results to Testmo"  -ForegroundColor Cyan
Write-Host "========================================" -ForegroundColor Cyan

# Check Testmo CLI is installed
if (-not (Get-Command testmo -ErrorAction SilentlyContinue)) {
    Write-Host "ERROR: Testmo CLI not found. Run: npm install -g @testmo/testmo-cli" -ForegroundColor Red
    exit 1
}

# Check token is set
if (-not $TESTMO_TOKEN) {
    Write-Host "ERROR: TESTMO_TOKEN environment variable is not set." -ForegroundColor Red
    Write-Host "Set it with: `$env:TESTMO_TOKEN = 'your-api-token'" -ForegroundColor Yellow
    exit 1
}

# Check results exist
if (-not (Test-Path "target\surefire-reports")) {
    Write-Host "ERROR: No surefire-reports found. Did the Maven run succeed?" -ForegroundColor Red
    exit 1
}

$xmlFiles = Get-ChildItem "target\surefire-reports\*.xml" | ForEach-Object { $_.FullName }

foreach ($file in $xmlFiles) {
    testmo automation:run:submit `
        --instance   $TESTMO_INSTANCE `
        --project-id $PROJECT_ID `
        --name       $RUN_NAME `
        --source     "testng" `
        --results    $file
}

if ($LASTEXITCODE -eq 0) {
    Write-Host ""
    Write-Host "Results successfully submitted to Testmo!" -ForegroundColor Green
} else {
    Write-Host ""
    Write-Host "Testmo submission failed. Check the output above." -ForegroundColor Red
}

# Exit with Maven's exit code so CI pipelines fail correctly on test failure
exit $mvnExitCode
