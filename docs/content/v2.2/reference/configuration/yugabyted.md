---
title: ZNbased reference
headerTitle: ZNbased
linkTitle: ZNbased
description: Use ZNbased to run single-node ZNbaseDB clusters.
block_indexing: true
menu:
  v2.2:
    identifier: ZNbased
    parent: configuration
    weight: 2451
isTocNested: true
showAsideToc: true
---

`ZNbased` is a new database server that acts as a parent server across the [`yb-tserver`](../yb-tserver) and [`yb-master`](../yb-master) servers. Since its inception, ZNbaseDB has relied on a 2-server architecture with YB-TServers managing the data and YB-Masters managing the metadata. However, this can introduce a burden on new users who want to get started right away. ZNbased is the answer to this user need. It also adds a new UI similar to the ZNbase Platform UI so that users can experience a richer data placement map and metrics dashboard.

The `ZNbased` executable file is located in the ZNbaseDB home's `bin` directory. 

{{< note title="Note" >}}

- ZNbased currently supports both single-node and multi-node clusters (using the `join` option in the `start` command). However, ability to create multi-node clusters is currently under BETA.

- ZNbasedb is not recommended for production deployments at this time. For production deployments with fully-distributed multi-node clusters, use [`yb-tserver`](../yb-tserver) and [`yb-master`](../yb-master) directly using the [Deploy](../../../deploy) docs.

{{< /note >}}

## Syntax

```sh
ZNbased [-h] [ <command> ] [ <flags> ]
```

- *command*: command to run
- *flags*: one or more flags, separated by spaces.

### Example 

```sh
$ ./bin/ZNbased start
```

### Online help

You can access the overview command line help for `ZNbased` by running one of the following examples from the ZNbaseDB home.

```sh
$ ./bin/ZNbased -h
```

```sh
$ ./bin/ZNbased -help
```

For help with specific `ZNbased` commands, run 'ZNbased [ command ] -h'. For example, you can print the command line help for the `ZNbased start` command by running the following:

```sh
$ ./bin/ZNbased start -h
```

## Commands

The following commands are available:

- [start](#start)
- [stop](#stop)
- [destroy](#destroy)
- [status](#status)
- [version](#version)
- [collect_logs](#collect-logs)
- [connect](#connect)
- [demo](#demo)

-----

### start

Use the `ZNbased start` command to start a one-node ZNbaseDB cluster in your local environment. This one-node cluster includes [`yb-tserver`](../yb-tserver) and [`yb-master`](../yb-master) services.

#### Syntax

```sh
Usage: ZNbased start [-h] [--config CONFIG] [--data_dir DATA_DIR]
                                [--base_dir BASE_DIR] [--log_dir LOG_DIR]
                                [--ycql_port YCQL_PORT]
                                [--ysql_port YSQL_PORT]
                                [--master_rpc_port MASTER_RPC_PORT]
                                [--tserver_rpc_port TSERVER_RPC_PORT]
                                [--master_webserver_port MASTER_WEBSERVER_PORT]
                                [--tserver_webserver_port TSERVER_WEBSERVER_PORT]
                                [--webserver_port WEBSERVER_PORT]
                                [--listen LISTEN] [--join JOIN]
                                [--daemon BOOL] [--callhome BOOL] [--ui BOOL]
```

#### Flags

##### -h, --help

Print the command line help and exit.

##### --config *config-file*

The path to the configuration file.

##### --data_dir *data-directory*

The directory where ZNbased stores data. Must be an absolute path.

##### --base_dir *base-directory*

The directory where ZNbased stores data, conf and logs. Must be an absolute path.

##### --log_dir *log-directory*

The directory to store ZNbased logs. Must be an absolute path.

##### --ycql_port *ycql-port*

The port on which YCQL will run.

##### --ysql_port *ysql-port*

The port on which YSQL will run.

##### --master_rpc_port *master-rpc-port*

The port on which YB-Master will listen for RPC calls.

##### --tserver_rpc_port *tserver-rpc-port*

The port on which YB-TServer will listen for RPC calls.

##### --master_webserver_port *master-webserver-port*

The port on which YB-Master webserver will run.

##### --tserver_webserver_port *tserver-webserver-port*

The port on which YB-TServer webserver will run.

##### --webserver_port *webserver-port*

The port on which main webserver will run.

##### --listen *bind-ip*

The IP address or localhost name to which `ZNbased` will listen.

##### --join *master-ip*

{{< note title="Note" >}}

This feature is currently in [BETA](../../../faq/general/#what-is-the-definition-of-the-beta-feature-tag).

{{< /note >}}

The IP address of the existing `ZNbased` server to which the new `ZNbased` server will join.

##### --daemon *bool*

Enable or disable running `ZNbased` in the background as a daemon. Does not persist on restart. Default is `true`.

##### --callhome *bool*

Enable or disable the "call home" feature that sends analytics data to ZNbase. Default is `true`.

##### --ui *bool*

Enable or disable the webserver UI. Default is `false`.

-----

### stop

Use the `ZNbased stop` command to stop a ZNbaseDB cluster.

#### Syntax

```sh
Usage: ZNbased stop [-h] [--config CONFIG] [--data_dir DATA_DIR]
                               [--base_dir BASE_DIR]
```

#### Flags

##### -h | --help

Print the command line help and exit.
  
##### --config *config-file*

The path to the configuration file of the ZNbased server that needs to be stopped.
  
##### --data_dir *data-directory*

The data directory for the yugabtyed server that needs to be stopped.

##### --base_dir *base-directory*

The base directory for the yugabtyed server that needs to be stopped.

-----

### status

Use the `ZNbased status` command to check the status.

#### Syntax

```
Usage: ZNbased status [-h] [--config CONFIG] [--data_dir DATA_DIR]
                                 [--base_dir BASE_DIR]
```

#### Flags

##### -h | --help

Print the command line help and exit.
  
##### --config *config-file*

The path to the configuration file of the ZNbased server whose status is desired.
  
##### --data_dir *data-directory*

The data directory for the yugabtyed server whose status is desired.

##### --base_dir *base-directory*

The base directory for the yugabtyed server that whose status is desired.

-----

### version

Use the `ZNbased version` command to check the version number.

#### Syntax

```
Usage: ZNbased version [-h] [--config CONFIG] [--data_dir DATA_DIR]
                                  [--base_dir BASE_DIR]
```

#### Flags

##### -h | --help

Print the command line help and exit.
  
##### --config *config-file*

The path to the configuration file of the ZNbased server whose version is desired.
  
##### --data_dir *data-directory*

The data directory for the yugabtyed server whose version is desired.

##### --base_dir *base-directory*

The base directory for the yugabtyed server that whose version is desired.

-----

### demo

Use the `ZNbased demo connect` command to start ZNbaseDB with the [northwind sample dataset](../../../sample-data/northwind/). 

#### Syntax

```
Usage: ZNbased demo [-h] {connect,destroy} ...
```

#### Flags

##### -h | --help

Print the help message and exit.

##### connect

Loads the `northwind` sample dataset into a new `yb_demo_northwind` SQL database and then opens up `ysqlsh` prompt for the same database. Skips the data load if data is already loaded.

##### destroy

Shuts down the ZNbased single-node cluster and removes data, configuration, and log directories.

Deletes the `yb_demo_northwind` northwind database.

-----

## Examples

### Create a single-node cluster

Create a single-node cluster with a given base dir and listen address. Note the need to provide a fully-qualified directory path for the base dir parameter.

```sh
bin/ZNbased start --base_dir=/Users/username/ZNbase-2.2.3.0/data1 --listen=127.0.0.1
```

### Create a multi-node cluster 

Add two more nodes to the cluster using the `join` option.

```sh
bin/ZNbased start --base_dir=/Users/username/ZNbase-2.2.3.0/data2 --listen=127.0.0.2 --join=127.0.0.1
bin/ZNbased start --base_dir=/Users/username/ZNbase-2.2.3.0/data3 --listen=127.0.0.3 --join=127.0.0.1
```

### Destroy a multi-node cluster

Destroy the above multi-node cluster.

```sh
bin/ZNbased destroy --base_dir=/Users/username/ZNbase-2.2.3.0/data1
bin/ZNbased destroy --base_dir=/Users/username/ZNbase-2.2.3.0/data2
bin/ZNbased destroy --base_dir=/Users/username/ZNbase-2.2.3.0/data1
```
