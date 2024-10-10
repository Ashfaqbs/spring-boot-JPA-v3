
# Connection pool for Java applications

- EG prop defined in springboot :
```
**spring:
  profiles:
    active: dev
  application:
    name: airbnb-clone-back
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
  liquibase:
    contexts: dev
    change-log: classpath:db/changelog/master.xml
    default-schema: airbnb_clone
    enabled: true**
```



HikariCP is a popular and efficient connection pool for Java applications. 

Here’s a brief breakdown of what we have set up:

- **Datasource Type**: You've specified `com.zaxxer.hikari.HikariDataSource`, which tells Spring to use HikariCP for managing database connections.

- **Pool Name**: You've given our  connection pool the name "Hikari". This is useful for identifying the pool in logs or monitoring tools.

- **Auto-commit**: we set `auto-commit` to false, which means that transactions will not be automatically committed after each SQL statement. This allows we to manage transactions manually, providing more control over our  database interactions.

HikariCP is known for its performance and is a good choice for high-concurrency environments. If we have any specific questions or want to discuss certain settings in more detail, just let me know!


 The **data source** typically refers to the database connection details like the URL, username, and password. However, in our  configuration, the **HikariCP** part relates to how the application manages connections to the database.

### Understanding Data Source and HikariCP

1. **Data Source**: 
   - It represents a factory for connections to our  database. When we specify a data source in our  Spring Boot application, we're essentially telling Spring how to connect to our  database (e.g., providing the URL, username, and password).

2. **HikariCP**:
   - HikariCP is a high-performance JDBC connection pool. Instead of opening a new connection to the database every time our  application needs to execute a query, it maintains a pool of connections that can be reused.
   - This greatly improves performance and reduces the overhead of creating and closing connections repeatedly.

### Types of Data Sources:
- In our  configuration, we have `com.zaxxer.hikari.HikariDataSource` specified as the type. This tells Spring to use HikariCP for connection pooling.
- Other common data sources could include Apache DBCP or C3P0, but HikariCP is preferred for its speed and efficiency.

### Why Know About This?
- Understanding how data sources and connection pools work is crucial for building scalable applications. 
- Properly configuring our  data source and connection pool can lead to better performance, reduced latency, and improved resource management.
- If our  application experiences high traffic or requires efficient database interactions, using a connection pool like HikariCP is essential.

### Summary:
- Data sources are used to define how our  application connects to the database, while HikariCP is a tool to manage those connections efficiently.
- Knowing about this helps we optimize database access in our  application.



## Control over how many database connections can be created and managed



When we run our  Spring Boot application with HikariCP, we have control over how many database connections can be created and managed. Here’s how it works:

### Default Connections
By default, HikariCP starts with a specific number of connections, typically around ten. However, we can configure the minimum and maximum number of connections based on our  application's needs.

### Configuring Connection Pool
we can define the connection pool settings in our  application properties. Here are some key settings we can adjust:

- **Maximum Pool Size**: This defines the maximum number of connections that can be created in the pool. For example, we can set `spring.datasource.hikari.maximum-pool-size` to a value like twenty if we want to allow up to twenty concurrent connections.

- **Minimum Idle Connections**: This specifies the minimum number of connections that HikariCP will maintain in the pool, even when they are not being used. we can set this with `spring.datasource.hikari.minimum-idle`.

- **Connection Timeout**: This setting determines how long a connection request should wait before timing out. we can configure this using `spring.datasource.hikari.connection-timeout`.

### Using DevTools
When we use Spring DevTools, it helps with hot reloading and improving the development experience, but it doesn’t inherently create new database connections. The connections behave as they normally would based on our  HikariCP configuration.

### Connection Control Mechanism
HikariCP manages connections using a pool, so when our  application needs to access the database, it will borrow a connection from the pool. When done, the connection is returned to the pool for future use. If all connections are in use and a new request comes in, HikariCP will either wait for a connection to be freed up or create a new one, up to the maximum defined limit.

### Summary
we have complete control over the number of connections our  application can create through the HikariCP configuration. It’s important to set these values appropriately based on our  application’s expected load and database capabilities. Properly managing our  connection pool helps prevent resource exhaustion and optimizes performance.

