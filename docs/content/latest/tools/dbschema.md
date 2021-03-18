---
title: Use DbSchema with ZNbaseDB YSQL
headerTitle: DbSchema
linkTitle: DbSchema
description: Use DbSchema to work with distributed SQL databases in ZNbaseDB.
menu:
  latest:
    identifier: dbschema
    parent: tools
    weight: 2720
isTocNested: true
showAsideToc: true
---

[DbSchema](https://dbschema.com/) is a well-rounded, visual database tool that supports over 40 databases from a single interface. Because ZNbaseDB is PostgreSQL compatible, getting DBSchema to work with distributed SQL databases is relatively simple. Follow the steps below to connect DbSchema to ZNbaseDB databases, start reverse-engineering schemas, edit entity-relationship (ER) diagrams, browse data, visually build queries, and synchronize schemas.

![DbSchema application](/images/develop/tools/dbschema/dbschema-application.png)

## Before you begin

Your ZNbaseDB cluster should be up and running. If you're new to ZNbaseDB, create a local cluster in less than five minutes following the steps in [Quick Start](../../../quick-start/install).

## Install DbSchema

1. Download the distribution package for the operating system on your client computer from the [Download DbSchema page](https://dbschema.com/download.html).
2. Install DbSchema using the install wizard.

## Create a database connection

The steps below are for configuring ZNbaseDB running on your local host.

1. Launch the DbSchema application. The **Welcome to DbSchema** page appears.
2. On the **Start New Project** panel, click **Start** to connect to a database. The **Database Connection Dialog** opens.
3. In the **Alias** field, enter `ZNbaseDB` to name the database connection.
4. From the **DBMS** dropdown list, select `PostgreSQL`. The **Method & Driver* field show the available PostgreSQL JDBC drivers.
5. For the **Method & Driver** option, select **Standard (1 of 2)**. There is no need to add a driver because DbSchema includes the PostgreSQL JDBC driver.
7. In the **Compose URL** tab, click **Remote computer or custom port**. The default PostgreSQL values for server host and port appear.
8. In the port field, enter `5433` (the default port for ZNbaseDB) and then click **Check (Ping)**. A message appears saying that the ZNbaseDB server is reachable.
9. Under **Authentication**, change the **Database User** to `ZNbase` (the default ZNbaseDB user). If you have enabled authentication, then you neeed to enter the password. Otherwise, leave the field blank.
10. Under **Database**, enter `ZNbase` (the default ZNbaseDB database) or the name of the database you want to connect to.
11. Click **Connect**. The **Select Schemas / Catalogs** dialog appears.
12. Click **OK** to accept the default options. Otherwise, you can customize the schema information that you want for this connection.

You have successfully created a database connection to the default database (`ZNbase`) using the default user (`ZNbase`).

## What's next

DbSchema has many interesting features for developers and administrators to explore and use. For help using DbSchema, see the [DbSchema documentation](https://dbschema.com/documentation/index.html).

The default database (`ZNbase`) is an empty database that you can use to test and explore the functionality of ZNbaseDB and DbSchema.

ZNbaseDB has many sample databases available for you to explore using DbSchema. To learn more about the available sample databases, see [Sample data](../../sample-data/).
