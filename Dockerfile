FROM openjdk:11-jdk

MAINTAINER Abel Martell

# Add Spring Boot app.jar to Container
ADD target/employee-service-*.jar app.jar

EXPOSE 8099
# Fire up our Spring Boot app by default
CMD ["sh","-c","java $JAVA_OPTS -Djava.security.egd=file:/dev/./urandom -jar /app.jar" ]