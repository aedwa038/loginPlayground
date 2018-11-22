FROM openjdk:8-jdk-alpine

VOLUME /tmp
ADD maven/login-playground-@dockerfile.release.version@.jar login-playground-@dockerfile.release.version@.jar
RUN sh -c 'touch /login-playground-@dockerfile.release.version@.jar'
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/login-playground-@dockerfile.release.version@.jar"]
