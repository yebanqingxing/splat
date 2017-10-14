set MAVEN_OPTS= -Xms128 -Xmx256m
mvn clean package --define maven.test.skip=true --define environment.type=uat