#!/bin/bash

echo "Trying to stop docker compose setup ..."
cd src/main/resources
docker-compose down --rmi all --volumes  --remove-orphans
cd ../../..
echo "Docker Compose Setup stopped."