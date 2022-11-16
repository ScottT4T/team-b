#!/bin/bash
clear
cd "$(dirname "$0")"

brew services start nginx
echo Press ENTER to STOP
read ACTION_ID
brew services stop nginx