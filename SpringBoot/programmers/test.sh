#!/bin/bash
cp pom.xml /tmp
cp -a src /tmp
mvn clean test -f /tmp/pom.xml
