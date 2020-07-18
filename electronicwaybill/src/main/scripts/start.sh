#!/bin/sh
appName="electronicwaybill"
appVersion="1.0"
_LAUNCHER_DAEMON_OUT="server.out"

pid=`ps -ef | grep "appName=$appName" | grep -v "grep" | awk '{print $2}'`
if [ "$pid" = "" ]
then
	java -DappName=$appName -Xms512M -Xmx2048M -jar ./electronicwaybill-$appVersion.jar --spring.config.location=./application.yml > "$_LAUNCHER_DAEMON_OUT" 2>&1 < /dev/null &
else
	echo "$appName is running"
fi