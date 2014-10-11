#!/bin/bash
echo "stoping gamerec api ..."
ps -ef | grep GameRecBootStrap | grep -v grep | awk '{print $2}' | while read pid
do
    kill -15 $pid
done