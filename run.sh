#!/bin/bash

javac -d src/com/tsp/out src/com/tsp/*.java
cat $1 | java -classpath src/com/tsp/out/ com.tsp.Main -v