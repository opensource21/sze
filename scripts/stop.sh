#!/bin/bash
CURRENT_DIR=`pwd`
cd `dirname $0`
SCRIPT_DIR=`pwd`
export SCRIPT_DIR

PID=`cat application.pid`

kill ${PID}

cd ${CURRENT_DIR}
