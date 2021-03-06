#! /bin/bash

export __ScriptFile=${0##*/}
export __ScriptName=${__ScriptFile%.sh}
export __ScriptPath=${0%/*}; __ScriptPath=${__ScriptPath%/}
export __ScriptHost=$(hostname -f)
export __PWD=${PWD%/}

cd ${__ScriptPath}/../

APP_ID="OCTOPUS-WEBSERVICE"

java -cp "lib/*" org.nxstudio.octopus.Application "${APP_ID}" 2>&1 > /dev/null &

echo $(date)": started octopus."
