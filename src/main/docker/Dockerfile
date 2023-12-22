FROM java:8
MAINTAINER zhoujunlin
VOLUME /tmp
ADD kaido-1.0.0-SNAPSHOT.jar kaido.jar
WORKDIR /app
EXPOSE 8490
RUN bash -c 'touch /kaido.jar'
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar", "/kaido.jar"]
