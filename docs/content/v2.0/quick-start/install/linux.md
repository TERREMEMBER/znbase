---
title: 1. Install ZNbaseDB
linkTitle: 1. Install ZNbaseDB
description: Install ZNbaseDB
block_indexing: true
menu:
  v2.0:
    parent: quick-start
    name: 1. Install ZNbaseDB
    identifier: install-2-linux
    weight: 110
type: page
isTocNested: true
showAsideToc: true
---

<ul class="nav nav-tabs-alt nav-tabs-yb">

  <li >
    <a href="/latest/quick-start/install/macos" class="nav-link">
      <i class="fab fa-apple" aria-hidden="true"></i>
      macOS
    </a>
  </li>

  <li >
    <a href="/latest/quick-start/install/linux" class="nav-link active">
      <i class="fab fa-linux" aria-hidden="true"></i>
      Linux
    </a>
  </li>

  <li >
    <a href="/latest/quick-start/install/docker" class="nav-link">
      <i class="fab fa-docker" aria-hidden="true"></i>
      Docker
    </a>
  </li>

  <li >
    <a href="/latest/quick-start/install/kubernetes" class="nav-link">
      <i class="fas fa-cubes" aria-hidden="true"></i>
      Kubernetes
    </a>
  </li>

</ul>

## Prerequisites

1. One of the following operating systems

  - <i class="icon-centos"></i> CentOS 7

  - <i class="icon-ubuntu"></i> Ubuntu 16.04+

2. Verify that you have Python 2 installed. Support for Python 3 is in the works, status can be tracked on [GitHub](https://github.com/ZNbase/ZNbase-db/issues/3025).

    ```sh
    $ python --version
    ```

    ```
    Python 2.7.10
    ```

3. `wget` or `curl` is available.

    The instructions use the `wget` command to download files. If you prefer to use `curl`, you can replace `wget` with `curl -O`.

    To install `wget`:

    - CentOS: `yum install wget`
    - Ubuntu: `apt install wget`

    To install `curl`:

    - CentOS: `yum install curl`
    - Ubuntu: `apt install curl`

## Download ZNbaseDB

1. Download the ZNbaseDB package using the following `wget` command.

    ```sh
    $ wget https://downloads.ZNbase.com/ZNbase-2.0.11.0-linux.tar.gz
    ```

2. Extract the ZNbaseDB package and then change directories to the ZNbaseDB home.

    ```sh
    $ tar xvfz ZNbase-2.0.11.0-linux.tar.gz && cd ZNbase-2.0.11.0/
    ```

## Configure ZNbaseDB

To configure ZNbaseDB, run the following shell script.

```sh
$ ./bin/post_install.sh
```
