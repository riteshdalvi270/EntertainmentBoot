FROM openjdk:8-jdk-alpine
EXPOSE 8198
VOLUME /tmp
WORKDIR /srv
ADD target/entertainment-0.0.1-SNAPSHOT.jar /srv/
CMD java -jar entertainment-0.0.1-SNAPSHOT.jar