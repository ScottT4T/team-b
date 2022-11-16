#!/bin/bash
clear
cd "$(dirname "$0")"

./nginx/nginx-1.22.1/nginx.exe
echo Press ENTER to STOP
read ACTION_ID
./nginx/nginx-1.22.1/nginx.exe -s stop