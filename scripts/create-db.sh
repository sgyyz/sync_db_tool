#!/bin/bash

function createDB() {
    USER=$1
    PASSWORD=$2
    DATABASE=$3
    echo "Creating database ${DATABASE} if not exists ..."
    mysql -u${USER} -p${PASSWORD} -h localhost -e "CREATE DATABASE IF NOT EXISTS ${DATABASE} DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci"
}

createDB root 123456 ptv_working_local
