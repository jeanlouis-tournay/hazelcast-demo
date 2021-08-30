#!/bin/sh

cp ../target/hazelcast-demo-0.0.1-SNAPSHOT.jar  .
docker build . -t softwarefactory.dplt.eu/hazelcast-demo:latest
rm hazelcast-demo-0.0.1-SNAPSHOT.jar
docker push softwarefactory.dplt.eu/hazelcast-demo:latest
