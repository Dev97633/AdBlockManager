@rem
@rem Gradle startup script for Windows
@rem

@if "%DEBUG%"=="" @echo off
set DIRNAME=%~dp0
if "%DIRNAME%"=="" set DIRNAME=.
set APP_BASE_NAME=%~n0
set APP_HOME=%DIRNAME%

@rem Resolve any "." and ".." in APP_HOME
for %%i in ("%APP_HOME%") do set APP_HOME=%%~fi

@rem Add default JVM options here
set DEFAULT_JVM_OPTS="-Xmx64m" "-Xms64m"

@rem Use JAVA_HOME if available
if not "%JAVA_HOME%"=="" goto findJavaHome
set JAVA_EXE=java
goto checkJava

:findJavaHome
set JAVA_EXE=%JAVA_HOME%\bin\java.exe

:checkJava
if exist "%JAVA_EXE%" goto init
echo ERROR: JAVA_HOME is not set or Java could not be found >&2
exit /b 1

:init
set CLASSPATH=%APP_HOME%\gradle\wrapper\gradle-wrapper.jar

"%JAVA_EXE%" %DEFAULT_JVM_OPTS% ^
  -Dorg.gradle.appname=%APP_BASE_NAME% ^
  -classpath "%CLASSPATH%" ^
  org.gradle.wrapper.GradleWrapperMain %*

:end
exit /b %ERRORLEVEL%
