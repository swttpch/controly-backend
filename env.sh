#!/bin/sh

echo "Criando variaveis de ambiente"

export AZ_RESOURCE_GROUP=controly
export AZ_DATABASE_NAME=db_controly
export AZ_LOCATION=eastus
export AZ_SQL_SERVER_USERNAME=appcontroly
export AZ_SQL_SERVER_PASSWORD=C0NTR0LY**
export AZ_LOCAL_IP_ADDRESS=${179.209.44.231}

export SPRING_DATASOURCE_URL="jdbc:sqlserver://$AZ_DATABASE_NAME.database.windows.net:1433;database=$AZ_DATABASE_NAME;user=$AZ_SQL_SERVER_USERNAME@$AZ_RESOURCE_GROUP;password=$AZ_SQL_SERVER_PASSWORD;encrypt=true;trustServerCertificate=false;hostNameInCertificate=*.database.windows.net;loginTimeout=30;"
export SPRING_DATASOURCE_USERNAME=spring@$AZ_DATABASE_NAME
export SPRING_DATASOURCE_PASSWORD=$AZ_SQL_SERVER_PASSWORD