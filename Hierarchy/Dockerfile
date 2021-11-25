FROM openjdk:8-jdk-alpine
EXPOSE 8090
ADD build/libs/Hierarchy-0.0.1-SNAPSHOT.jar spring-boot-hierarchy.jar
ENTRYPOINT ["java","-jar","spring-boot-hierarchy.jar"]