FROM openjdk:alpine
ARG JAR_NAME
ENV JAR_NAME $JAR_NAME
MAINTAINER jauffrey.flach@gmail.com
VOLUME /tmp
ENV JAVA_OPTS=""
ADD maven/$JAR_NAME /boot2dcos/$JAR_NAME
EXPOSE 8080
ENTRYPOINT [ "sh", "-c", "java $JAVA_OPTS -Djava.security.egd=file:/dev/./urandom -Dspring.config.location=/genconf/config.yaml -jar /boot2dcos/$JAR_NAME" ]