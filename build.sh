#!/bin/bash
echo "Building Database"
cd login-playground-database &&  sh ./build.sh && cd ../
echo "Building Login App"
cd login-playground-app && mvn clean install docker:build  && cd ../
echo "Building Docs"
cd login-playground-docs && sh ./build.sh && cd ../
echo "Publising to Docker hub"
echo "$DOCKER_PASSWORD" | docker login --username "$DOCKER_USERNAME" --password-stdin
docker push aedwa038/playground-docs:latest
docker push aedwa038/login_db:latest
echo "Done"
#docker-compose down && cd login-playground-app/ && mvn clean install docker:build && cd ../ && docker-compose up
