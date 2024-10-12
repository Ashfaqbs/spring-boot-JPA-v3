## Props 
```
spring.application.name=SB3-J21-OracleDB-samples
#Need to provide

spring.datasource.url=jdbc:oracle:thin:@localhost:1522/mainschema
spring.datasource.username=SPRINGBOOTDEV
spring.datasource.password=admin
spring.datasource.driverClassName=oracle.jdbc.OracleDriver
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.OracleDialect
#spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.Oracle12cDialect -- wont work
#spring.datasource.driver-class-name=oracle.jdbc.OracleDriver

spring.jpa.show-sql=true
spring.jpa.hibernate.ddl-auto=update
#spring.jpa.database-platform=org.hibernate.dialect.OracleDialect
# Hibernate ddl auto (create, create-drop, validate, update)
#spring.jpa.hibernate.ddl-auto=update
#logging.level.org.hibernate=DEBUG
```


## Db client :
# DB info , its a docker container pointing to volume and the volume has : mainschema schema/DB
![image](https://github.com/user-attachments/assets/82fdcc03-70b0-4dd3-a643-02c3cf8a5162)
![image](https://github.com/user-attachments/assets/21ee1891-219c-44ec-8d7f-c5aba48cfe3e)

