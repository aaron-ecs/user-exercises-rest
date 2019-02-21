FROM        maven:3.6.0-jdk-8-slim

COPY        . /var/www

WORKDIR     /var/www
VOLUME      [ "/var/www" ]

RUN         mvn clean install
EXPOSE      8080

ENTRYPOINT  [ "mvn", "spring-boot:run", "-Drun.jvmArguments=-Dserver.port=8080" ]