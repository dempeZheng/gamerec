#!/bin/bash
echo "stoping gamerec analystics ..."
ps -ef | grep Analystic | grep -v grep | awk '{print $2}' | while read pid
do
    kill -15 $pid
done