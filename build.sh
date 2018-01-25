#!/bin/bash
echo "Send command: mvn clean package"
mvn clean package
echo "Remove the old project directoy."
rm -rf $CATALINA_HOME/webapps/wpac4redis
echo "Copy *.war to webapps."
cp /home/shixzh/workspace/wpac4redis/target/wpac4redis.war $CATALINA_HOME/webapps

tomcat=`ps -ef | grep $CATALINA_HOME | grep -v 'grep\|tail' | awk '{print $2}'`
echo "Tomcat pid: "${tomcat}
if test -z ${tomcat}
then
    echo "Start up tomcat."
    startup.sh
else
    echo "Shut down tomcat."
    shutdown.sh
    echo "Start up tomcat."
    startup.sh
fi
echo "Done."
