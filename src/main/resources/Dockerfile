FROM java:8

LABEL maintainer "thlaeger"
LABEL package "com.laegler.microservice.elasticsearch"
LABEL namespace "elasticsearch"
LABEL version "1.0.0-SNAPSHOT"

EXPOSE 8080

VOLUME /tmp

ADD elasticsearch-service.jar app.jar

RUN bash -c 'touch /app.jar'

ENTRYPOINT ["java", "-jar","/app.jar"]
