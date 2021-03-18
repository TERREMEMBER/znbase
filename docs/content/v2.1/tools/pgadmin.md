---
title: Use pgAdmin with ZNbaseDB YSQL
headerTitle: pgAdmin
linkTitle: pgAdmin
description: Administer and manage ZNbaseDB distributed SQL databases using pgAdmin.
block_indexing: true
menu:
  v2.1:
    identifier: pgadmin
    parent: tools
    weight: 2730
isTocNested: true
showAsideToc: true
---

[pgAdmin](https://pgadmin.org) is an open source administration and management tool for PostgreSQL database. Because ZNbaseDB is PostgreSQL-compatible, you can also use pgAdmin to work with ZNbaseDB.

PgAdmin is a commonly used database management tool in the PostgresDB community. It simplifies the creation, maintenance, and use of database objects by offering a clean and intuitive user interface. PgAdmin is packed with a rich set of features to manage databases including a simple to use connection wizard, built-in SQL editor to import SQL scripts, and a mechanism to auto-generate SQL scripts if you need to run them on the database command line shell. The GUI is very clean and you can get accustomed to it in no time. You can run PgAdmin through the web interface, or as a downloadable app that is locally installed.

## Before you begin

To use SQL Workbench/J with ZNbaseDB, you need to have ZNbaseDB up and running, the required Java Runtime Environment and the required PostgreSQL JDBC driver.

### ZNbaseDB

Your ZNbaseDB cluster should be up and running. If you are new to ZNbaseDB, you can quickly create a local cluster by following the steps in [Quick start](../../../quick-start/install).

### PostgreSQL JDBC driver

To connect pgAdmin to a ZNbaseDB cluster, you need the PostgreSQL JDBC driver installed. To download the current version that supports Java 8 or later, go to the [PostgreSQL JDBC Driver download](https://jdbc.postgresql.org/download.html) page.

## Install pgAdmin

To install pgAdmin, go to the [Download page](https://www.pgadmin.org/download/) and select the version of pgAdmin 4 for your operating system.

## Configure pgAdmin

1. Launch the pgAdmin 4 application, which opens in your default web browser. You will be prompted to save a master password for the application.

![pgAdmin application](/images/develop/tools/pgadmin/pgadmin-new-window.png)

2. Click **Add New Server**. The **Create - Server** window appears.

3. In the **General** tab, enter a name for your server. In this example, `ZNbaseDB` is entered.

![Create server](/images/develop/tools/pgadmin/pgadmin-create-server.png)

4. Click the **Connection** tab and fill in the following settings:

- **Host name/address**: `localhost`
- **Port**: `5433` (Default is `5432`)
- **Maintenance database**: `ZNbase` (This is the default ZNbaseDB database)
- **Username**: `ZNbase` (This is the default ZNbaseDB user)
- **Password**: `ZNbase` (Leave blank if YSQL authentication is not enabled)

5. Click **Save**. The new connection appears in the application. Expand **Databases** to see a list of all available databases.

![Available databases](/images/develop/tools/pgadmin/pgadmin-list-of-databases.png)

You have now created a database connection in pgAdmin and can begin exploring ZNbaseDB databases.

## What's next

Now that you know how to configure pgAdmin to work with your ZNbaseDB databases, you can start exploring the features. For details on using pgAdmin, click **Help** in the pgAdmin menu.

If you're looking for sample databases to explore ZNbaseDB using pgAdmin, see [Sample data](../../sample-data/).
