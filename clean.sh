#!/bin/bash
echo "Clearning Login App" && cd login-playground-app && mvn clean && cd ../ && echo "Cleaning Docs" && cd login-playground-docs && sh ./clean.sh && cd ../