#!/usr/bin/env sh

# --------------------------------------------------
# Gradle startup script (Linux / macOS)
# --------------------------------------------------

APP_BASE_NAME=`basename "$0"`
APP_HOME=`dirname "$0"`

# Resolve APP_HOME (absolute path)
APP_HOME=`cd "$APP_HOME" && pwd`

# DEFAULT JVM OPTS (NO QUOTES AROUND INDIVIDUAL FLAGS)
DEFAULT_JVM_OPTS="-Xmx64m -Xms64m"

CLASSPATH=$APP_HOME/gradle/wrapper/gradle-wrapper.jar

# Use JAVA_HOME if set
if [ -n "$JAVA_HOME" ] ; then
    JAVA_EXE="$JAVA_HOME/bin/java"
else
    JAVA_EXE=java
fi

# Verify Java exists
if [ ! -x "$JAVA_EXE" ] ; then
    echo "ERROR: JAVA_HOME is not set and no 'java' command found."
    exit 1
fi

# Run Gradle Wrapper
exec "$JAVA_EXE" $DEFAULT_JVM_OPTS \
  -Dorg.gradle.appname=$APP_BASE_NAME \
  -classpath "$CLASSPATH" \
  org.gradle.wrapper.GradleWrapperMain "$@"
