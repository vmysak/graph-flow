#!/bin/bash

sh ./gradlew \
    clean \
    build \
    buildDocker \
    buildApiDocker
