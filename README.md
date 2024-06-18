# Serverless Spring Boot 3 example
A basic pet store written with the [Spring Boot 3 framework](https://projects.spring.io/spring-boot/). The `StreamLambdaHandler` object is the main entry point for Lambda.

The application can be deployed in an AWS account using the [Serverless Application Model](https://github.com/awslabs/serverless-application-model). The `template.yml` file in the root folder contains the application definition.

## Pre-requisites
* [AWS CLI](https://aws.amazon.com/cli/)
* [SAM CLI](https://github.com/awslabs/aws-sam-cli)
* [Gradle](https://gradle.org/) or [Maven](https://maven.apache.org/)

## Deployment
Add Environment variables to template.yml
```
Resources:
    SpringLambdaFunction:
        Properties:
            Environment:
                Variables:
                    DB_HOST: 
                    DB_LOGIN: 
                    DB_PASSWORD:                
```

How to build and deploy in one click
```
./deploy.sh

```


In a shell, navigate to the sample's folder and use the SAM CLI to build a deployable package
```
$ sam build
```

This command compiles the application and prepares a deployment package in the `.aws-sam` sub-directory.

To deploy the application in your AWS account, you can use the SAM CLI's guided deployment process and follow the instructions on the screen

```
$ sam deploy --guided
```

Once the deployment is completed, the SAM CLI will print out the stack's outputs, including the new application URL. You can use `curl` or a web browser to make a call to the URL

```
...
---------------------------------------------------------------------------------------------------------
OutputKey-Description                        OutputValue
---------------------------------------------------------------------------------------------------------
PetStoreApi - URL for application            https://xxxxxxxxxx.execute-api.eu-central-1.amazonaws.com/users
---------------------------------------------------------------------------------------------------------

$ curl https://xxxxxxxxxx.execute-api.eu-central-1.amazonaws.com/users
```

## Run DB locally
`
docker run -p 5432:5432 -e POSTGRES_USER=spring-lambda -e POSTGRES_PASSWORD=spring123 -e POSTGRES_DB=spring-lambda -d --name spring-lambda postgres:16.0
`
