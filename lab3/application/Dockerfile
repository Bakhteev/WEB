FROM ubuntu

RUN mkdir server

RUN apt-get update -y

RUN apt-get install wget -y

RUN apt-get install openjdk-17-jdk -y

RUN apt-get install openjdk-17-jre -y

RUN apt-get install curl -y

RUN cd ~ && \
    curl -sL https://deb.nodesource.com/setup_18.x -o nodesource_setup.sh

RUN apt-get install bash

RUN cd ~ && bash nodesource_setup.sh

RUN apt-get install nodejs -y

RUN apt-get install maven -y

RUN apt-get install zip unzip

RUN wget https://github.com/wildfly/wildfly/releases/download/27.0.0.Final/wildfly-27.0.0.Final.zip

RUN unzip wildfly-27.0.0.Final.zip

COPY . /server

RUN cd server/src/web && npm i && npm run build

RUN cd server && mvn install

RUN rm ./wildfly-27.0.0.Final/standalone/configuration/standalone.xml

RUN mv server/standalone.xml ./wildfly-27.0.0.Final/standalone/configuration

RUN mv server/target/lab3-1.0-SNAPSHOT.war ./wildfly-27.0.0.Final/standalone/deployments

RUN bash ./wildfly-27.0.0.Final/bin/add-user.sh -u 'root' -p 'password'

CMD bash wildfly-27.0.0.Final/bin/standalone.sh