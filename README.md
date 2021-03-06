# Personio-RestApi

To run this application:
1. You can either  checkout the project and run it from the main class.
2. Or run in docker container following these instructions
    1. Create docker image - run this command in terminal:
       - docker build -t spring-boot-hierarchy.jar:latest .
       - Run "docker images" command to check if an image has been created
        
    2. Run docker container with the created image
        - docker run -p 8090:8090 spring-boot-hierarchy.jar
            <br /> By changing "-p 8090:8090" you can choose the port the application should run on
    
    3. To stop the container you can list all containers with "docker ps" command
    and run "docker stop {name of container}" with the name of the container
       
3. You can try out the endpoints with the Postman collection included in the project
- Personio.postman_collection.json

4. Application has basic authentication and you can configure the username 
   and password in application.properties file. Defaults are - username: user ; password: password
   
5. If you are calling the endpoints from postman, you should click on Authorization, select type Basic-Auth and enter the username and password.

If you are new to docker you can check out https://docs.docker.com/get-started/
    
