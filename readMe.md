# BookWorms Library Application

TODO Short description of the application.

## How to use

* TODO how to start and stuff

##### Running
* run at least one: mvn clean install
* run: mvn spring-boot:start
##### Using
* Address: localhost:8080 
* Postman (Collection V2): src/postman/
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
