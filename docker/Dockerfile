FROM adoptopenjdk/openjdk11:x86_64-debian-jre-11.0.8_10

# create a dplt user and group: non-root
# use it and work into its home directory
RUN groupadd -g 1000 dplt && \
    useradd -g 1000 -u 1000 -m mplt

USER 1000

# read-only container: declare volumes in which the system writes
VOLUME /tmp

COPY hazelcast-demo-0.0.1-SNAPSHOT.jar /home/app.jar

# start the springboot application
ENTRYPOINT java -jar  /home/app.jar

