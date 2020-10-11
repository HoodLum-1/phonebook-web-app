# Phonebook

A simple phonebook web page that loads contacts into a table.
The columns are all sortable and filterable. 
Minimum functionality includes: view all contacts, create a new contact, update contact, delete a contact.

## Assumptions: 
- You have the docker engine installed and running
- You have mysql installed and running


# Starting the application manually

- open both the api and front-end in your respective IDE's for Angular and Java
- run the api, the service will be accessible via port 5000
- start the front-end it willbe accessible via port 4200 Navigate to http://localhost:4200 to see the application
- Ensure you have mysql running with an empty database titled phonebook created
- Change the mysql user and password configuration in the application.yml if need be to suit your configuration


# Starting the application with docker file

- excecute the command docker-compose up -d in the terminal to start the service and front-end
- docker-compose stop  to stop the service and front-end


# Accessing Swagger 

after successfully starting the application. Navigate to http://localhost:5000/swagger-ui.html to see and try out the available rest endpoints.

