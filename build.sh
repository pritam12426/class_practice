#!/usr/bin/env -S bash -x
javac -d build/classes -cp "libs/*" -sourcepath src/main/java $(find src/main/java/com/App -name "*.java")
