# mars-rover-api
This repo contains the api to control the rover from Earth. 

I have implemented the Mars rover kata with hexagonal architecture in Java 8. 
As the first iteration of this Kata, there is just one input port. Its implemented a RESTFul Endpoint using Spring Rest.

For now it does not save any state of Rover's position. Every execution of the POST method assumes the Rover starts at position 0, 0, N.

I will be using this exercise in future to practice other Cloud Native principles and micro services architectures.

List of features:
* External Configuration
* Junit 5 test cases
* API documentation with Swagger
* API testing with Karate
* CD Pipeline with Github Actions

#### How to run locally:
For now this is a simple Spring Boot app. To run locally, after you cloning this repo, you need to:
* Open a Terminal
* Go to directory where you clone the repo
* Run : mvn clean install
* Then run :  "java -jar ./target/mars-rover-0.9.jar"

#### How to use it:
To use it, you have 2 options:
* Swagger UI : localhost:8080/swagger-ui.html
* Terminal : curl -s --request POST http://localhost:8080/rest/mars/{instructions}

#### Dependencies:
* Maven