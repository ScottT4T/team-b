#!/bin/bash
clear
cd "$(dirname "$0")"
mkdir run

for file in ./backend/target/backend-*.jar; do
      cp "$file" ./run/backend.jar
done

java -jar run/backend.jar