#!/bin/bash

export _GRAPHFLOW_PROFILE="-Dspring.profiles.active=${GRAPHFLOW_PROFILE}"
export _JVM_OPTS="-Xmx1g -Xss512k"

echo "Connecting to $LINKED_SERVICES ..."


for i in `echo $LINKED_SERVICES | tr "," " "`
do
  until 2>/dev/null < /dev/tcp/${i%%:*}/${i##*:}
  do
    sleep 1s
    echo "waiting for $i"
  done
  echo "connected to $i"
done

echo "Connected to $LINKED_SERVICES"

#==================== RUN =================#
export _JAVA_OPTIONS="${_GRAPHFLOW_PROFILE}
${_JVM_OPTS}
"

exec java -jar /graphflow.jar
