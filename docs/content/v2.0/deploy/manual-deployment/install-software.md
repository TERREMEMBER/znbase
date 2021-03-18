---
title: Install software
linkTitle: 2. Install software
description: Install software
block_indexing: true
menu:
  v2.0:
    identifier: deploy-manual-deployment-install-software
    parent: deploy-manual-deployment
    weight: 612
isTocNested: true
showAsideToc: true
---

Install ZNbaseDB on each of the nodes using the steps shown below.

## Download

Download the ZNbaseDB binary package as described in the [Quick Start section](../../../quick-start/install/).

Copy the ZNbaseDB package into each instance and then run the following commands.

```sh
$ tar xvfz ZNbase-<version>-<os>.tar.gz && cd ZNbase-<version>/
```

## Configure

- Run the **post_install.sh** script to make some final updates to the installed software.

```sh
$ ./bin/post_install.sh
```
