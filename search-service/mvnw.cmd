@REM ----------------------------------------------------------------------------
@REM Maven Start Up Batch script
@REM
@REM Required ENV vars:
@REM JAVA_HOME - location of a JDK home dir
@REM
@REM Optional ENV vars
@REM M2_HOME - location of maven's installed home (default is %USERPROFILE%\m2)
@REM MAVEN_BATCH_ECHO - set to 'on' to enable the echoing of the batch commands
@REM MAVEN_BATCH_PAUSE - set to 'on' to wait for a keystroke before ending
@REM MAVEN_OPTS - parameters passed to the Java VM when running Maven
@REM     e.g. to debug Maven itself, use
@REM set MAVEN_OPTS=-Xdebug -Xrunjdwp:transport=dt_socket,server=y,suspend=y,address=8000
@REM MAVEN_SKIP_RC - flag to disable loading of mavenrc files
@REM
@REM BEGIN INITIALIZATION
@REM ==============================================================================
@setlocal

@REM ==============================================================================
@REM Java and Maven Detection
@REM ==============================================================================

if not "%JAVA_HOME%" == "" goto foundJava

for /f "tokens=*" %%i in ('where java.exe 2^>nul') do set JAVA_HOME=%%~dpi..
if "%JAVA_HOME%" == "" (
    echo.
    echo ERROR: JAVA_HOME not found and java.exe not in PATH
    echo.
    exit /b 1
)

:foundJava
if exist "%JAVA_HOME%\bin\java.exe" goto init

@REM ==============================================================================
@REM Maven Home Setup
@REM ==============================================================================

:init
if "%M2_HOME%"=="" set M2_HOME=%USERPROFILE%\.m2\wrapper\dists\apache-maven-3.9.5
if not exist "%M2_HOME%" goto downloadMaven

goto runMaven

:downloadMaven
echo Downloading Maven 3.9.5...
if not exist "%USERPROFILE%\.m2\wrapper\dists" mkdir "%USERPROFILE%\.m2\wrapper\dists"
powershell -Command "& {$MavenUrl='https://repo.maven.apache.org/maven2/org/apache/maven/apache-maven/3.9.5/apache-maven-3.9.5-bin.zip'; $OutPath='%USERPROFILE%\.m2\wrapper\dists\maven.zip'; try { Invoke-WebRequest -Uri $MavenUrl -OutFile $OutPath -UseBasicParsing } catch { Write-Host 'Download failed'; exit 1 }; Expand-Archive -Path $OutPath -DestinationPath '%USERPROFILE%\.m2\wrapper\dists' -Force; Remove-Item $OutPath }"

if errorlevel 1 (
    echo Maven download failed
    exit /b 1
)

:runMaven
set MAVEN_CMD="%M2_HOME%\bin\mvn.cmd"

if not exist %MAVEN_CMD% (
    echo Maven not found at %MAVEN_CMD%
    exit /b 1
)

%MAVEN_CMD% %*

:end
@endlocal
