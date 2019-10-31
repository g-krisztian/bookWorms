# BookWorms Library Application

TODO Short description of the application.

## How to use

* TODO how to start and stuff

##### Running
* run at least one: mvn clean install
* run: mvn spring-boot:run
##### Using
* Address: localhost:8080 
* Postman (Collection V2): src/postman/

 [![Run in Postman](https://run.pstmn.io/button.svg)](https://app.getpostman.com/run-collection/196f31bd711898bc2616)

##### Shut down
* POST localhost:8080/actuator/shutdown
* alternative way :
``` 
netstat -a -b -o 
taskkill /PID <pid>
```


## For developers
* initial data (?) src/resources/data.sql
* Debug: mvn spring-boot:run --debug
* Database settings: application.properties
