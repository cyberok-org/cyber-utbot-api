#!/bin/bash
java -jar build/libs/cyber-utbot-api-2023.05-SNAPSHOT.jar generate \
  -cp /home/andrew/ex/build/classes/java/main:/home/andrew/.jdks/openjdk-17.0.2/bin/javax.servlet-api-3.1.0.jar \
  -s /home/andrew/ex/src/main/java/org/example/checks2/Example.java \
  -b /home/andrew/UTBot/UTBotJava/cyber-utbot-exploit-base \
  -p
