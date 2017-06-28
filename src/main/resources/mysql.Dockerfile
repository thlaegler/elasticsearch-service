FROM mysql:5.7

ENV MYSQL_ROOT_PASSWORD=root
#ENV MYSQL_DATABASE=controlpanel
ENV MYSQL_USER=readonly
ENV MYSQL_PASSWORD=everythingisawesome

ADD create-schema.sql /docker-entrypoint-initdb.d/create.sql
ADD import.sql /docker-entrypoint-initdb.d/import.sql

EXPOSE 3306