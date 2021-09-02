#!/bin/bash

export CURL_PROXY=
export HOST=http://localhost:8000

#export CURL_PROXY="--socks5-hostname localhost:1080 -k"
#export HOST=https://server-product-operator.apps.envm.envm.dplt.eu

echo "deploy product $1 specification $2 at "$HOST/api/v1/deployment/$1/$2
return_code=$(curl -sw '%{http_code}' $CURL_PROXY -X POST -H "Content-Type: application/json" $HOST/api/v1/deployment/$1/$2)
echo " Deployment operation HTTP status: "$return_code