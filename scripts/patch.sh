#!/bin/sh
cd ../..
git diff utbot-framework utbot-framework-api settings.gradle.kts > cyber-utbot-api/utbot_update_fe0b89c789da5559bf8912e0179cdcee026e6137.patch
git diff --cached utbot-framework utbot-framework-api settings.gradle.kts >> cyber-utbot-api/utbot_update_fe0b89c789da5559bf8912e0179cdcee026e6137.patch
