FROM maven:3.8.3-openjdk-17
ADD src src
ADD pom.xml .
RUN mvn package
EXPOSE 8855
ENTRYPOINT ["java","-jar","target/qabackend.jar"]