#!/bin/bash
echo "$DOCKER_PASSWORD" | docker login -u "$DOCKER_USERNAME" --password-stdin
docker push aedwa038/playground-docs:latest
docker push aedwa038/login_db:latest