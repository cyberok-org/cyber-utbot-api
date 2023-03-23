#!/bin/bash
cd `dirname $0`/..
jdeps --class-path 'libs/*' -recursive --dot-output deps ./build/libs/cyber-utbot-api-2023.03-SNAPSHOT.jar
