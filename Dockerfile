FROM ubuntu:20.04

ARG DEBIAN_FRONTEND=noninteractive
RUN apt update && \
    apt-get -qq install -y openjdk-11-jre-headless && \
    apt-get -qq install -y mysql-server mysql-client

COPY adv_web.jar /app/adv_web.jar
COPY adv_web.sql /app/adv_web.sql

RUN service mysql start && \
    mysql -u root -e "CREATE DATABASE adv_web;" && \
    mysql -u root adv_web < /app/adv_web.sql && \
    mysql -u root -e "ALTER USER 'root'@'localhost' IDENTIFIED WITH mysql_native_password BY '12345';"

EXPOSE 8080

CMD service mysql start && java -jar /app/adv_web.jar