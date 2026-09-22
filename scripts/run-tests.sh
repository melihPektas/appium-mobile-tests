#!/usr/bin/env bash
# Runs inside the Android emulator job: start Appium, run the suite, stop Appium.
set -uo pipefail

appium --log-level warn --log appium.log &
APPIUM_PID=$!

for _ in $(seq 1 60); do
  curl -sf http://127.0.0.1:4723/status > /dev/null && break
  sleep 1
done

adb devices
mvn -B test
STATUS=$?

kill "$APPIUM_PID" 2> /dev/null || true
exit "$STATUS"
