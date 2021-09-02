#!/bin/bash
export CURL_PROXY=
export HOST=http://localhost:8000

#export CURL_PROXY="--socks5-hostname localhost:1080 -k"
#export HOST=https://server-product-operator.apps.envm.envm.dplt.eu

push_data() {
  echo "call product operator to push $1 at "$HOST/api/v1/$2
  return_code=$(curl -sw '%{http_code}' $CURL_PROXY -X POST -H -k "Content-Type: application/json" -d @$1 $HOST/api/v1/$2)
  echo " Push platform operation HTTP status: "$return_code
  if [ "$return_code" != "202" ]; then
    echo "unable to push descriptors"
  fi
  echo "Operation succeeded"
}




push_data "contract.json" "model/contract/digital-products"
push_data "deployment.json" "model/contract/specification"
