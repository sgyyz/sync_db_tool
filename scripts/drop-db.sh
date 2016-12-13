#!/bin/bash

function dropDB() {
    USER=$1
    PASSWORD=$2
    DATABASE=$3
    echo "Dropping database ${DATABASE} if exists ..."
    mysql -u${USER} -p${PASSWORD} -h localhost -e "DROP DATABASE if exists ${DATABASE}"
}

dropDB root 123456 ptv_working_local