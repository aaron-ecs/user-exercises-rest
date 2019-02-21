# user-exercises-rest

### Purpose of project
A very simple Spring rest webservice to help aid other example test frameworks in this repository.

### Installing and Running
Import into your IDE as a maven project.

**Running via IDE**

Create a run configuration:
 Main Class:            "com.williams.userexercisesrest.UserExercisesRestApplication"
 Working Directory:     "[Your Project Location]\user-exercises-rest"
 Classpath:             "user-exercises-rest"
 
**Running via Terminal**

 You can run in the terminal `mvn spring-boot:run -Drun.jvmArguments="-Dserver.port=8080"`

**Running via Docker**

Build/Pull:

`docker build -f Dockerfile -t aaronmwilliams/user-exercises-rest .` or pull latest image from DockerHub `docker pull aaronmwilliams/user-exercises-rest`

Run:

`docker run -d -p 8080:8080 aaronmwilliams/user-exercises-rest`

By default port 8080 will be used within the service.  If running on a server you may want to use this port configuration `-p 80:8080`

### Running Tests
All unit tests
`mvn test "-Dtest=*UnitTest.java"`

All integration tests
`mvn test "-Dtest=*IntegrationTest.java"`


### API Documentation
With the service running you can visit http://localhost:8080/swagger-ui.html

### Querying In Memory h2 Database
With the service running visit http://localhost:8080/h2-console/login.do and make sure the JDBC URL is set as jdbc:h2:mem:testdb

Example queries listed below. Please remember the service starts with no data injection script you need to run the tests to create data!

```
    SELECT * FROM EXERCISE;
    SELECT * FROM USER;
    SELECT * FROM USER_EXERCISE_LOG;
 ```

### Supporting Repositories
The following testing frameworks can be used to test this service when running.

Rest-assured testing framework: https://bitbucket.org/aaronmwilliams/user-exercises-restassured-testing

### IDE Plugins
The project uses Lombok. So you will need to install this plugin otherwise you will see compile errors.

### Running on Jenkins CI
Please note to run on a Jenkins server you will need the following configuration:
- Global dockerhub credentials
- Python installed
- AWS provider chain configuration

**Pipeline Steps**
- *Pull*: git pull on your branch
- *Build*: builds a new docker image
- *Test*: runs docker-compose file to run the service and then tests
- *Publish Artifact to S3*: zips the project, pulls `deployment-scripts` then runs `publish-to-s3.py script
- *Push to Docker Hub*: pushes new images to docker hub using the branch and build number
- *Deploy to EC2*: runs script `deploy_to_ec2.py` script to push live
- *Clean Up*: deletes archive file and newly created image locally

## To Do
- Terraform scripts to create EC2 (and more?)
- Different Jenkins file for master and any other branch
- Bug fix relating to time format missing leading 0