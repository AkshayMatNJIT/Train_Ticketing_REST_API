# Train_Ticketing_REST_API
Java Springboot REST API with MySQL and Docker

Instructions to run the project –

Pre-requisites –

1.	Docker installed on your machine.
2.	IntelliJ Idea IDE.
3.	Postman Client.

To run the project –

1.	Extract and open the project folder in IntelliJ

In one terminal instance -

1.	mvn install -Dmaven.test.skip=true         // to create executable jar and skip tests
2.	docker-compose up                                  // to execute docker-compose file

(First time only, to add database tables in mysql container) -
In a new terminal instance -

1.	mysql --host=127.0.0.1 --port=13306 -uroot -pPassword
2.	use mysql
3.	Open the file “MySqlQueriesUsed.sql” present in project’s root folder and run the first 3 queries to create tables and add foreign key
