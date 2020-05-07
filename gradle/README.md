# Quality Tool (API)
Backend for the QualityTool used throughout INNIO

## how to build and run
``` 
./gradlew clean build docker
```
will create a docker image.
``` 
./gradlew clean bootRun 
```
will start the application on port 8080.

## how to find out the current graphql-scheme

run the test
``` 
GraphqlSchemaGeneration
``` 
it will create the current scheme in src/main/resources

## graphql and graphiql-servers

graphql-server is found under localhost:8080/graphql   
graphiql-server is found under localhost:8080/graphiql
