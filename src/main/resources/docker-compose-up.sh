#!/bin/bash

echo "Trying to start Docker Compose Setup ..."
cd src/main/resources
docker-compose up --no-build --remove-orphans
cd ../../..
echo "Docker Compose Setup started."