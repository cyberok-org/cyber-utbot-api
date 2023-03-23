#!/bin/sh
cd ../..
git diff utbot-framework utbot-framework-api settings.gradle.kts > cyber-utbot-api/utbot_update_22badb4.patch
git diff --cached utbot-framework utbot-framework-api settings.gradle.kts >> cyber-utbot-api/utbot_update_22badb4.patch
