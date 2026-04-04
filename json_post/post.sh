#!/bin/bash

url='http://localhost:8080/api/v1/eventos'

curl -v $url --json @evento_1.json && echo
curl -v $url --json @evento_2.json && echo
