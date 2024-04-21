FROM java:8
MAINTAINER zhoujunlin
VOLUME /tmp
WORKDIR /app
ADD kaido-1.0.0.jar /app/kaido.jar
EXPOSE 8490
RUN bash -c 'touch /app/kaido.jar'
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar", "/app/kaido.jar"]
