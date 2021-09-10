# Cooking

***
manage your favourite recipes.

This project has been implemented as an assigment. Spring boot and hibernate used to develop the back end part. In this
project there are two types of roles including admin and user. Admin users can insert initialize data to database and
also block and unblock the users. Users have capabilities to create and modify their recipes. This part of project has
been developed by spring security including jwt as access token.

# Build docker image

just run this command to build corresponding image `docker build -t cooking-app .`

# Run docker image

run this command `docker run -it -p 8080:8080 cooking-app` and check the url on
browser: http://localhost:8080/swagger-ui/index.html to see all implemented apis
