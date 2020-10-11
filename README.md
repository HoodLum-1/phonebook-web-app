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

# Phonebook User Interface

## Development server

Run `ng serve` for a dev server. Navigate to `http://localhost:4200/`. The app will automatically reload if you change any of the source files.

## Code scaffolding

Run `ng generate component component-name` to generate a new component. You can also use `ng generate directive|pipe|service|class|guard|interface|enum|module`.

## Build

Run `ng build` to build the project. The build artifacts will be stored in the `dist/` directory. Use the `--prod` flag for a production build.

## Running unit tests

Run `ng test` to execute the unit tests via [Karma](https://karma-runner.github.io).

## Running end-to-end tests

Run `ng e2e` to execute the end-to-end tests via [Protractor](http://www.protractortest.org/).
