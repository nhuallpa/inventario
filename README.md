# Inventario 

A REST API is provided to manage an inventory of items for sale in branches.

## Getting Started 🚀

These instructions will help you set up a copy of the project and run it on your local machine for development and testing purposes.

Look at the [Architecture Decision Record](./ADR.md) for more details about the architecture decisions.

### Pre-requisitos 📋

What you need to install the software

```
Java JDK 21
Gradle
```

### Installation 🔧

Clone the repository on your local machine and install the dependencies with gradle tool

```bash
./gradlew build
```

## Running the tests ⚙️

To run the unit and integration tests, use the following command

```bash
./gradlew test
```

## Starting the Local Server ⚙️

Finally, you can start the Tomcat instance with the following command from the project folder

```bash
./gradlew bootRun
```

It's time to enjoy the application with the following request examples.

## Request Examples 📦
Requests can be made using curl or Postman. The server runs by default on port 8080.

- Get All Items
``` bash
    curl --location 'http://localhost:8080/items'
```

- Get Item by ID
``` bash
    curl --location 'http://localhost:8080/items/1'
```

- Increment Stock
``` bash
    curl --location --request PUT 'http://localhost:8080/items/1/increment-stock' \
    --header 'Content-Type: application/json' \
    --data '{
        "quantity": 3
    }'
```

- Decrement Stock
``` bash
    curl --location --request PUT 'http://localhost:8080/items/1/decrement-stock' \
    --header 'Content-Type: application/json' \
    --data '{
        "quantity": 3
    }'
```

## Authors ✒️

* **Huallpa Nestor** - *Initial Work* - [Linkedin](https://www.linkedin.com/in/nestor-huallpa-7239b011)

