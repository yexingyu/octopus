#! /bin/bash

export __ScriptFile=${0##*/}
export __ScriptName=${__ScriptFile%.sh}
export __ScriptPath=${0%/*}; __ScriptPath=${__ScriptPath%/}
export __ScriptHost=$(hostname -f)
export __PWD=${PWD%/}

cd ${__ScriptPath}/../

APP_ID="OCTOPUS-WEBSERVICE"
PIDS=$(ps ax | grep -i "${APP_ID}" | grep java | grep "Application" | grep -v grep | awk '{print $1}')

if [ -z "$PIDS" ]; then
	echo "No Octopus Webservice to stop"
	exit 1
else 
	kill -s TERM ${PID}
fi
