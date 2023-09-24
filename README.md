# Payment Service

Payment Service is a small project that simulates transactions between users. Each user has its own balance and can send
an amount to other users.

### Rules

* There are 2 types of users: COMMON and SELLER. Both users have name, balance, user type and email. A username will be
  generated using the name by replacing any special characters with '-'. Username is also unique.
* COMMON users can send money for both types of users, but SELLER users can only receive money.
* Users must have balance to complete the transaction.

# Technologies

* Java
* Spring Boot
* JUnit 5
* REST
* PostgreSQL

# Getting Started

### Prerequisites

* [Java 17](https://www.azul.com/downloads/zulu-community)
* [Maven-3](https://maven.apache.org/download.cgi)
* [Git](https://git-scm.com/downloads)

It's possible to run the application through Docker:

* [Docker 23.0 +](https://www.docker.com/products/overview)
* [Docker Compose 2.20 +](https://docs.docker.com/compose/install/)

Run the following command on the project root folder:

```bash
docker compose up
```


