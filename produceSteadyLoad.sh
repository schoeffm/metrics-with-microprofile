#!/bin/bash

for i in {1..1000};
do
    n=$(($i%5))
    for service in 8081 8082 8083;
    do
        curl -s -o /dev/null "http://localhost:${service}/simplyHello" 2>&1
        curl -s -o /dev/null "http://localhost:${service}/timedHello" 2>&1
    done

    if [[ "$n" -eq "0" ]]; then
        echo "Processed $i calls by now ..."
    fi
done

echo "Done Processing"