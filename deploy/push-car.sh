export TARGET=http://hz-demo-hz-demo.apps.envm.envm.dplt.eu
export CURL_PROXY="--socks5-hostname localhost:1080 -k"


push_data() {
  echo "call product operator to push $1 at "$TARGET/cars/$2
  return_code=$(curl -sw '%{http_code}' $CURL_PROXY -X POST -H "Content-Type: application/json" -d @$1 $TARGET/cars/$2)
  echo " Push platform operation HTTP status: "$return_code
  if [ "$return_code" != "201" ]; then
    echo "unable to push car"
    exit 1
  fi
  echo "Operation succeeded"
}

push_data car.json G-12345

