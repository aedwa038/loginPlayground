#!/bin/bash
docker login -e $DOCKER_EMAIL -u $DOCKER_USER -p $DOCKER_PASS
echo "Building Database"
cd login-playground-database &&  sh ./build.sh && cd ../
echo "Building Login App"
cd login-playground-app && mvn clean install docker:build docker:push && cd ../
echo "Building Docs"
cd login-playground-docs && sh ./build.sh && cd ../
echo "Done"
#docker-compose down && cd login-playground-app/ && mvn clean install docker:build && cd ../ && docker-compose up
