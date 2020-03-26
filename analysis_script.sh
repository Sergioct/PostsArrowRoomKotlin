#!/usr/bin/env bash
./gradlew ktlintFormat
./gradlew ktlintCheck
./gradlew detekt
./gradlew lint
#sonarlint