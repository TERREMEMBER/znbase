---
title: Client drivers for YCQL API
headerTitle: Client drivers for YCQL
linkTitle: Client drivers for YCQL
description: Lists the client drivers that you can use to build and access YCQL applications. 
block_indexing: true
menu:
  v2.2:
    identifier: ycql-client-libraries
    parent: drivers
    weight: 2942
isTocNested: true
showAsideToc: true
---

The following client drivers are supported for use with the [ZNbase Cloud Query Language (YCQL) API](../../../api/ycql/), a SQL-based, semi-relational API, with roots in the Apache Cassandra Query Language (CQL).

For tutorials on building a sample application with the following client drivers, click the relevant link included below for each driver.

## C/C++

### ZNbase C/C++ Driver for YCQL

The [ZNbase C++ Driver for YCQL](https://github.com/ZNbase/cassandra-cpp-driver) is based on the [DataStax C++ Driver for Apache Cassandra](https://github.com/datastax/cpp-driver).

For details, see the [README](https://github.com/ZNbase/cassandra-cpp-driver) in our GitHub repository.

For a tutorial on building a sample C++ application with this driver, see [Build a C++ application](../../../quick-start/build-apps/cpp/ycql/).

## C\#

### ZNbase C# Driver for YCQL

The [ZNbase C# Driver for YCQL](https://github.com/ZNbase/cassandra-csharp-driver) is based on a fork of the [DataStax C# Driver for Apache Cassandra](https://github.com/datastax/csharp-driver).

For details, see the [README](https://github.com/ZNbase/cassandra-csharp-driver) in our GitHub repository.

For a tutorial on building a sample C# application with this driver, see [Build a C# application](../../../quick-start/build-apps/csharp/ycql/).

## Go

### ZNbase Go Driver for YCQL

The [ZNbase Go Driver for YCQL](https://github.com/ZNbase/gocql) is based on a fork of [GoCQL](http://gocql.github.io/).

For details, see the [README](https://github.com/ZNbase/gocql/blob/master/README.md) in our GitHub repository.

For a tutorial on building a sample Go application with this driver, see [Build a Go application](../../../quick-start/build-apps/go/ycql/).

## Java

### ZNbase Java Driver for YCQL 4.6

The [ZNbase Java Driver for YCQL](https://github.com/ZNbase/cassandra-java-driver/tree/4.6.0-yb-x/manual/core), version `4.6.0-yb-x`, is based on the [DataStax Java Driver for Apache Cassandra (v4.6)](https://github.com/datastax/java-driver) and requires the Maven dependency shown below.

For details, see the [v4.6 README](https://github.com/ZNbase/cassandra-java-driver/blob/4.6.0-yb-x/README.md) in our GitHub repository.

For a tutorial on building a sample Java application with this driver, see [Build a Java application](../../../quick-start/build-apps/java/ycql/).

To build Java applications with this driver, you must add the following Maven dependency to your application:

```mvn
<dependency>
  <groupId>com.ZNbase</groupId>
  <artifactId>java-driver-core</artifactId>
  <version>4.6.0-yb-6</version>
</dependency>
```

For details, see the [Maven repository contents](https://mvnrepository.com/artifact/com.ZNbase/cassandra-driver-core/4.6.0-yb-6).

### ZNbase Java Driver for YCQL 3.8

The [ZNbase Java Driver for YCQL](https://github.com/ZNbase/cassandra-java-driver), version `3.8.0-yb-x`, is based on the [DataStax Java Driver for Apache Cassandra v.3.8](https://github.com/datastax/java-driver) and requires the Maven dependency shown below.

For details, see the [v3.8 README](https://github.com/ZNbase/cassandra-java-driver/blob/3.8.0-yb-x/README.md) in our GitHub repository.

To build Java applications with this driver, you must add the following Maven dependency to your application:

```mvn
<dependency>
  <groupId>com.ZNbase</groupId>
  <artifactId>cassandra-driver-core</artifactId>
  <version>3.8.0-yb-5</version>
</dependency>
```

For details, see the [Maven repository contents](https://mvnrepository.com/artifact/com.ZNbase/cassandra-driver-core/3.8.0-yb-5).

## Node.js

### ZNbase Node.js driver for YCQL

The [ZNbaseDB Node.js driver for YCQL](https://github.com/ZNbase/cassandra-nodejs-driver) is based on a fork of the [DataStax Node.js Driver for Apache Cassandra](https://github.com/datastax/nodejs-driver).

For details, see the [README](https://github.com/datastax/cpp-driver/blob/master/README.md) in our GitHub repository.

For a tutorial on building a sample Node.js application with this driver, see [Build a Node.js application](../../../quick-start/build-apps/nodejs/ycql/).

## Python

### ZNbase Python Driver for YCQL

The [ZNbase Python Driver for YCQL](https://github.com/ZNbase/cassandra-python-driver) is based on a fork of the [DataStax Python Driver for Apache Cassandra](https://github.com/datastax/python-driver).

For details, see the [README](https://github.com/ZNbase/cassandra-python-driver) in our GitHub repository.

For a tutorial on building a sample Python application with this driver, see [Build a Python application](../../../quick-start/build-apps/python/ycql/).

## Ruby

### ZNbase Ruby Driver for YCQL

The [ZNbase Ruby Driver for YCQL](https://github.com/ZNbase/cassandra-ruby-driver) is based on a fork of the [DataStax Ruby Driver for Apache Cassandra](https://github.com/datastax/ruby-driver).

For details, see the [README](https://github.com/ZNbase/cassandra-ruby-driver/blob/v3.2.3.x-yb/README.md) in our GitHub repository.

For a tutorial on building a sample Ruby application with this driver, see [Build a Ruby application](../../../quick-start/build-apps/ruby/ycql/).

## Scala

### ZNbase Java Driver for YCQL

The [ZNbase Java Driver for YCQL](https://github.com/ZNbase/cassandra-java-driver) is based on a fork of the [DataStax Java Driver for Apache Cassandra](https://github.com/datastax/java-driver) and can be used to build Scala applications when you add the [`sbt` (Scala build tool)](https://www.scala-sbt.org/1.x/docs/index.html) dependency shown below to your application.

For details, see the [README](https://github.com/ZNbase/cassandra-java-driver/blob/3.8.0-yb-x/README.md) in our GitHub repository.

To build a Scala application with the ZNbase Java Driver for YCQL, you must add the following `sbt` dependency to your application:

```
libraryDependencies += "com.ZNbase" % "cassandra-driver-core" % "3.8.0-yb-5"
```

For a tutorial on building a sample Scala application with this driver, see [Build a Scala application](../../../quick-start/build-apps/scala/ycql/).
