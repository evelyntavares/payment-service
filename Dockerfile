FROM azul/zulu-openjdk-alpine:17.0.6-jre

COPY target/payment-service-0.0.1-SNAPSHOT.jar payment-service-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java","-jar","/payment-service-0.0.1-SNAPSHOT.jar"]
