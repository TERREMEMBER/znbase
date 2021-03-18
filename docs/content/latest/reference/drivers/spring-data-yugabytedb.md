---
title: Spring Data ZNbaseDB
headerTitle: Spring Data ZNbaseDB
linkTitle: Spring Data ZNbaseDB
description: Spring Data ZNbaseDB
beta: /latest/faq/general/#what-is-the-definition-of-the-beta-feature-tag
section: REFERENCE
menu:
  latest:
    identifier: spring-data-ZNbasedb
    parent: drivers
    weight: 2943
aliases:
  - /latest/reference/connectors/spring-data-ZNbasedb
isTocNested: true
showAsideToc: true
---


The Spring Data ZNbaseDB driver brings the power of distributed SQL to Spring developers by using the [ZNbaseDB JDBC Driver](https://github.com/ZNbase/jdbc-ZNbasedb). The end result is that most features of PostgreSQL v11.2 are now available as a massively-scalable, fault-tolerant distributed database. Spring Data ZNbaseDB is based on [Spring Data JPA](https://github.com/spring-projects/spring-data-jpa).

## Unique features

In addition to providing most PostgreSQL features on top of a scalable and resilient distributed database, this project aims to enable the following:

* Eliminate load balancer from SQL (cluster-awareness)
* Develop geo-distributed Apps (topology-awareness)
* Row level geo-partitioning support (partition-awareness)

Go to the [Spring Data ZNbaseDB GitHub project](https://github.com/ZNbase/spring-data-ZNbasedb/) to watch, star, file issues, and contribute.

## Get started

### Maven configuration

Add the Maven dependency:

```xml
<dependency>
  <groupId>com.ZNbase</groupId>
  <artifactId>spring-data-ZNbasedb</artifactId>
  <version>2.1.10-yb-1</version>
</dependency>
```

### Data source configuration

To enable the ZNbaseDB configuration, create configuration class:

```java
@Configuration
public class DatabaseConfiguration extends AbstractZNbaseConfiguration {
  // Here you can override the dataSource() method to configure the DataSource in code.
}
```

Configure your `application.properties`. For instance:

```
spring.ZNbase.initialHost=localhost
spring.ZNbase.port=5433
spring.ZNbase.database=ZNbase
spring.ZNbase.user=ZNbase
spring.ZNbase.password=ZNbase
spring.ZNbase.maxPoolSizePerNode=8
spring.ZNbase.connectionTimeoutMs=10000
spring.ZNbase.generate-ddl=true
spring.ZNbase.packages-to-scan=com.example.spring.jpa.springdatajpaexample.domain
```

To learn more about using the Spring Data ZNbaseDB module, see [Spring Data ZNbaseDB example](https://github.com/ZNbase/spring-data-ZNbasedb-example).
