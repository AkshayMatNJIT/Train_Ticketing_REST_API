FROM java:8
COPY target/train-ticket-buyer-0.0.1-SNAPSHOT.jar /home/train-ticket-buyer-0.0.1-SNAPSHOT.jar
WORKDIR /home
CMD ["java","-jar","/home/train-ticket-buyer-0.0.1-SNAPSHOT.jar"]
