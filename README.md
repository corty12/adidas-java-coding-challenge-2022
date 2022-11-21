# Adidas Backend Challenge

The repository contains microservices needed for a draft API, enabling a priority queue subscription system.

Common use case would be:

- Entry point is the PUBLIC SERVICE microservice available to the general public. It exposes a RESTFul API on port 8080
  at /queue-subscription where users will enter the sale.


- From the PUBLIC SERVICE microservice, the new email entry is sent through a Kafka producer-consumer queue to the
  PRIORITY SALE SERVICE microservice, where the message is processed.


- PRIORITY SALE SERVICE microservice validates the email and processes it. The email is validated against ADI-CLUB
  microservice, which exposes the adidas club members. ADI-CLUB SERVICE is right now mocked but it would ideally expose
  the data from a MongoDB.


- Once the email is validated, PRIORITY SALE SERVICE stores the member data either in MEMBER_SUBSCRIPTIONS or
  NON_MEMBER_SUBSCRIPTIONS table, which is hosted in a MySQL Database directly on PRIORITY SALE SERVICE. It holds which
  the emails registered to the sale queue and whether or not they have already been awarded (DELETED field)


- PRIORITY SALE SERVICE also exposes a REST API at /winners, which would be used as an internal endpoint called from the
  backend, and as a testing resource. Whenever it receives a request at /winners with the number of winners, PRIORITY
  SALE SERVICE queries both members and non-members database, filtering non DELETED users, and sorting them by points
  and registration date.


- Once the lucky winners have been established, PRIORITY SALE SERVICE sends a Kafka message that gets consumed by EMAIL
  SERVICE microservice.


- At this point, EMAIL SERVICE only prints a log line indicating the email which has been awarded the sale, but it would
  ideally sent a real email through an SMTP server.

--------------------------------------------------------------------------------------------------------------------

# What to do?

The repo contains a skeleton of 4 Spring Boot applications, plus a Docker Compose configuration which spins up the
following working environment.

<img width="365" alt="image" src="https://user-images.githubusercontent.com/15728394/199699196-3bf20be2-cc51-4718-8cc2-454c8397c9d4.png">

- _Public Service_ in the main entry point to our system, and the only accesible one to the public.
- _Priority Sale Service_ is the service containing the priority sale logic.
- _Adi-Club Service_ contains the information of our adiClub members. e-mail, points, registration date...
- _Email Service_ sends a confirmation mail to the lucky ones.

We would like you to implement the following:
- Subscription to the sale using the Public Service, and the internal logic, using all internal services. Reordering the queue upon new registrations, selecting the next winner... 
- The communications among the different services, either sync or async.
- API documentation using Swagger, API BluePrint or the tool you feel more comfortable with.

Do not forget our challenge Non Functional Requirements:
- **Security** for non-public services
- Proper **exception handling** and REST responses.​
- Unit **testing** for meaningful code (business logic / services).​

We encourage you to take a look at our architectural principles. And of course, you have total freedom to propose or/and implement the improvements you want! Changes on the architecture, Introducing new services and/or containers, Reactive APIs, Distributed logging.... **Your creativity is more than welcome!**

# What would you need?
The code requires the following tools:
- Maven 3.8.4
- JDK11
- A Docker container engine: docker desktop, podman, rancher or any other

# How to build & run the project
### Build jar files
`mvn clean install`

### Build docker images & start the containers
`docker-compose up -d`

### Cleanup project
Using docker compose you need to run the following command:
`docker-compose down --rmi`

Using podman you should first stop the compose environment:
`docker-compose down` 
And then you will need to delete the images manually.

# Useful commands
### Test dummy endpoint in public-service from your local machine
`curl localhost:8080/dummy -s`

should return http status 200 and the message
`Hello, this is a dummy response from public service`

### Test adiclub-service endpoint from priority-sale-service container
`docker exec adidas-be-challenge-prioritysaleservice bash -c "curl -s adidas-be-challenge-adiclubservice:8080/adiclub?emailAddress=test1@gmail.com"`

should return a json response similar to:
`{"email":"test1@gmail.com","points":511,"registrationDate":"2022-04-17T08:12:41.467026Z"}`

### Access Open API documentation
In order to see UI documentation you can write the following url in your browser:
`http://localhost:8080/swagger-ui/index.html#/`

### Watch public-service logs
`docker logs -f adidas-be-challenge-publicservice`
