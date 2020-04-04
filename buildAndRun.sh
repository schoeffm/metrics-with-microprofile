#!/bin/bash

pushd quarkus-service
    ./mvnw clean package -DskipTests
popd

echo "Quarkus-Service successfully build - starting everything up now"

docker-compose up -d --build


echo -e "\n\n\033[38;5;202mNow you should use the \033[0m'produceSteadyLoad.sh'\033[38;5;202m-script to put some \033[0m"
echo -e "\033[38;5;202mload on the deployed services. Do this in a separate shell window\033[0m"
echo -e "\033[38;5;202msince that script will run for a while.\033[0m"
