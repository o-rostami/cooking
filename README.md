# Cooking

<h3>manage your favourite recipes.</h3>
***

This project has been implemented as an assigment. In this project two popular frameworks including Spring boot and
hibernate used to develop the back end part.The project consists of two types of roles including admin and user. Admin
users can insert and initialize database and also block and unblock the users. Users after registration have
capabilities to create and modify their recipes. The security part of project has been developed by spring security and
jwt as access token(user should add jwt token to header as `Authorization` with the value of `Bearer token`)

The postman collection including test of every request exists with the name of `asdf` 

# Build docker image

just run this command to build corresponding image `docker build -t cooking-app .`

# Run docker image

run this command `docker run -it -p 8080:8080 cooking-app` and check the url on
browser: http://localhost:8080/swagger-ui/index.html to see all implemented apis

