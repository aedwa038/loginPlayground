#!/bin/bash
echo "Building Database"
cd login-playground-database &&  sh ./build.sh && cd ../
echo "Building Login App"
cd login-playground-app && mvn clean install docker:build && cd ../
echo "Building Docs"
cd login-playground-docs && sh ./build.sh && cd ../
echo "Done"
#docker-compose down && cd login-playground-app/ && mvn clean install docker:build && cd ../ && docker-compose up
