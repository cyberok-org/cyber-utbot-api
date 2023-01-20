#!/bin/sh
cd ../..
git apply --reject --whitespace=fix cyber-utbot-api/utbot_update.patch
