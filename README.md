## Understanding db/jpa properties in springboot

```
-- from airbnb backend props 
spring:
  datasource:
    type: com.zaxxer.hikari.HikariDataSource
    hikari:
      poolName: Hikari
      auto-commit: false
  data:
    jpa:
      repositories:
        bootstrap-mode: deferred
  jpa:
    open-in-view: false
    hibernate:
      ddl-auto: validate
    properties:
      hibernate:
        default_schema: airbnb_clone
-- ignore
 liquibase:
    contexts: dev
    change-log: classpath:db/changelog/master.xml
    default-schema: airbnb_clone
    enabled: true
```

###  **Datasource Configuration**
```yml
spring:
  datasource:
    type: com.zaxxer.hikari.HikariDataSource
    hikari:
      poolName: Hikari
      auto-commit: false
```
- **type: com.zaxxer.hikari.HikariDataSource**: Specifies the use of `HikariCP` as the connection pool, which is a highly efficient and fast JDBC connection pooling library.
- **hikari.poolName: Hikari**: Sets the name of the Hikari connection pool.
- **hikari.auto-commit: false**: Disables auto-commit for database transactions, meaning changes to the database won't be committed until explicitly done.

###  **JPA Data and Repositories**
```yml
spring:
  data:
    jpa:
      repositories:
        bootstrap-mode: deferred
```
- **repositories.bootstrap-mode: deferred**: The JPA repositories are initialized after the application context is fully started, which helps prevent issues with database initialization.

###  **JPA and Hibernate Configuration**
```yml
spring:
  jpa:
    open-in-view: false
    hibernate:
      ddl-auto: validate
    properties:
      hibernate:
        default_schema: airbnb_clone
```
- **open-in-view: false**: Disables the Open Session in View pattern, which keeps the Hibernate session open for the entire web request. Setting this to `false` is recommended in production to prevent issues like LazyInitializationException.
- **hibernate.ddl-auto: validate**: This ensures that Hibernate will validate the schema but not make any changes to it. If the schema doesn’t match, an error will be thrown. 
- **hibernate.properties.default_schema: airbnb_clone**: Sets `airbnb_clone` as the default schema for Hibernate, so all database operations will use this schema.

###  **Liquibase Configuration**
```yml
spring:
  liquibase:
    contexts: dev
    change-log: classpath:db/changelog/master.xml
    default-schema: airbnb_clone
    enabled: true
  #    drop-first: true
```
- **liquibase.contexts: dev**: Liquibase will run database migrations within the `dev` context. Liquibase is a tool for database versioning and schema management.
- **change-log: classpath:db/changelog/master.xml**: Specifies the location of the Liquibase changelog file, which contains the database changesets.
- **default-schema: airbnb_clone**: Liquibase will apply changes to the `airbnb_clone` schema by default.
- **enabled: true**: Liquibase is enabled, so it will run on application startup.
- **drop-first: true** (commented): If uncommented, this would drop the existing database schema before applying changes. It's typically used in development but dangerous for production environments.

