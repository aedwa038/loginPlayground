#!/bin/bash
echo "Building Database"
cd login-playground-database &&  sh ./build.sh && cd ../
echo "Building Login App"
cd login-playground-app && mvn clean install docker:build  && cd ../
echo "Building Docs"
cd login-playground-docs && sh ./build.sh && cd ../
echo "Publising to Docker hub"
echo "$DOCKER_PASSWORD" | docker login --username "$DOCKER_USERNAME" --password-stdin
#echo "$DOCKER_PASSWORD"
#docker login -u $DOCKER_USERNAME -p $DOCKER_PASSWORD ;
docker push $DOCKER_USERNAME/playground-docs:latest
docker push $DOCKER_USERNAME/login_db:latest
docker push $DOCKER_USERNAME/login-playground:latest
echo "Done"
#docker-compose down && cd login-playground-app/ && mvn clean install docker:build && cd ../ && docker-compose up
