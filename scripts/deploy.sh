#! /bin/bash

export __ScriptFile=${0##*/}
export __ScriptName=${__ScriptFile%.sh}
export __ScriptPath=${0%/*}; __ScriptPath=${__ScriptPath%/}
export __ScriptHost=$(hostname -f)
export __PWD=${PWD%/}

cd ${__ScriptPath}/../

cd ~/codes/octopus/
mvn clean
mvn package
cp target/octopus-0.0.1-SNAPSHOT-deploy/lib/*.jar ~/octopus/lib/
cd ~/octopus/


