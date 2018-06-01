# Sales Messages Processing


## Specs

- Java 8
- Maven 3
- Dependency: jersey-server

I thought using REST is easier and faster way to develop and use this app instead of develop and use a sandalone app.
To document and test the REST API I use to use Swagger, but I should have to add more dependencies to the project. 

## Run the app

> mvn exec:java

Check if app is running

- http://localhost:8080/application.wadl


## How to call messages

- `Message Type One`
```
http://localhost:8080/salesmessage/messageTypeOne/{product}/{price}
e.g: PUT http://localhost:8080/salesmessage/messageTypeOne/apple/5
```


- `Message Type Two`
```
http://localhost:8080/salesmessage/messageTypeTwo/{product}/{price}/{ocurrences}
e.g: PUT http://localhost:8080/salesmessage/messageTypeTwo/orange/3/6
```


- `Message Type Three`
```
http://localhost:8080/salesmessage/messageTypeThree/{product}/{operation}
e.g: PUT http://localhost:8080/salesmessage/messageTypeThree/onion/31/ADD
Operations could be: ADD | SUBTRACT | MULTIPLY
``` 

