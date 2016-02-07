Company Rest Service Provider
=============================

Create a tiny REST/JSON web service in Java using a suitable framework (Spark, Spring MVC, RestController) or similar. This web service should support the following:

- Create new company,
- Get a list of all companies,
- Get details about a company,
- Able to update a company,
- Able to add beneficial owner(s) of the company.

A company has the following attributes:

- Company ID
- Name
- Address
- City
- Country
- E-mail (not required)
- Phone (not required)
- One or more beneficial owner(s)

Create a tiny client using a well know JavaScript framework e.g AngularJS, React or similar.

Building The Project
====================

The project compiles with ```JDK >= 1.5``` and ```Maven >= 3.1.1``` as the build tool and to also manage the project dependencies.

To run Maven build, execute the following from a console/command prompt with the project root directory as the top level directory:

```mvn clean package```

This will create a distributable and deployable WAR file for the web application. This file can be deployed on the latest versions of web servers/containers e. Jetty, Tomcat, JBoss etc

This project includes an embedded Tomcat Server as a plug-in in the Maven Build file. In order to run this project using this plug-in, execute one of the following commands:

```mvn tomcat7:run-war```

```mvn tomcat7:run-war -DskipTests -Dinvoker.skip=true```

This will create a distributable WAR file and deploy it in the embedded Tomcat Server.

To view the home page of this web application, open the URL: ```http://localhost:8080/CompanyRestService``` in a web browser.

In order to interact with the REST web services using Git Bash execute the following:

```curl -X POST -H "Content-Type: application/json" -d '{ "name": "Felicity", "address": "Woodley", "city": "Berlin", "country": "Germany", "email": "software@engineer.com", "phoneNumber": "+49-234-090", "owner":[ { "name": "Blue" }, { "name": "Red" } ]}' http://localhost:8080/CompanyRestService/restservice/company```

```curl -X PUT -H "Content-Type: application/json" -d '{ "name": "Elizabeth", "address": "Langata", "city": "London", "country": "England", "email": "software@engineer.com", "phoneNumber": "+44-786-086", "owner":[ { "id":1, "name":"Green"}, { "id":2, "name":"Pink" } ]}' http://localhost:8080/CompanyRestService/restservice/company/1```

```curl -X PUT -H "Content-Type: application/json" -d '{ "name": "Brown" }' http://localhost:8080/CompanyRestService/restservice/companyowner/1```

```curl -X GET -H "Content-type: application/json" http://localhost:8080/CompanyRestService/restservice/company/1```

```curl -X GET -H "Content-type: application/json" http://localhost:8080/CompanyRestService/restservice/companies```

To run the Unit tests in this project, execute the following:

```mvn clean test```

### Instructions

The file: ```./CompanyRestService/src/main/resources/application.properties``` contains database and hibernate configurations and settings.
These properties may be modified/edited accordingly so as to enable the web application to be run on different database vendors e.g PostgreSQL, MySQL, SQLite etc.

Eclipse users run `mvn eclipse:eclipse` and then import the project or just import the code as a Maven project into IntelliJ, NetBeans, or Eclipse.

To generate project documentation of Java source files (Javadoc), run ```mvn javadoc:javadoc```

### Considerations

In RESTful web services, it is best to use session-based authentication, either by establishing a session token via a POST or by using an API key as a POST body argument or as a cookie.
Usernames, passwords, session tokens, and API keys should not appear in the URL, as this can be captured in web server logs and used by attackers with malicious intentions.

In order to make the web service redundant, the following considerations should be taken into account:

- Disaster recovery: you maintain servers in an additional data centre which allows you to recover data in the event your primary data centre is destroyed.

- Hot standby: you maintain duplicate servers in an additional data centre which are running and ready to take over immediately in the event the primary data centre fails.

- Live traffic handling: the same as the hot standby option but this data centre(s) serves web traffic too, and so there is often no real “primary” data centre.

- Replication: This mostly applies to databases. Database fail-over is product specific and it may be complicated when you consider how replication is implemented with regards to master/slave and how fail-over is triggered.