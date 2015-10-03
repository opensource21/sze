#!/bin/bash
CURRENT_DIR=`pwd`
cd `dirname $0`
SCRIPT_DIR=`pwd`
export SCRIPT_DIR

java -jar sze.jar > stdout.log 2>&1 &

cd ${CURRENT_DIR}
