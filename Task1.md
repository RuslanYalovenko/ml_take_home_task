# Getting Started

### Assumptions

* No history of weather-location records is stored at this point
* Only latest record is stored
* Changes should be made to save weather history
* Weather in location does not depend on user so separate storage for location-weather-timestamp is needed

### Getting Started

* From root folder run
    ```sh
        docker-compose up
    ```
    * Message producer is not implemented yet. No way to test for now.

### Next Steps
* Explore use cases
* Collect more requirements
* Understand kafka consumer and web service workload
* Figure out what data should be stored by this service (data ownership)
* Build historical location weather storage (most likely)
* Refactor code, split into separate modules

### Resources

## Code samples

* [Dockerizing Java Apps using Jib](https://www.baeldung.com/jib-dockerizing)
* [Unlock the Power of Apache Kafka with The Official Docker Image](https://medium.com/towards-data-engineering/unlock-the-power-of-apache-kafka-with-the-official-docker-image-5a65192e618b)
* [Introduction to Spring Data Redis](https://www.baeldung.com/spring-data-redis-tutorial)
* [https://www.baeldung.com/spring-data-redis-reactive](https://www.baeldung.com/spring-data-redis-reactive)
* ...

