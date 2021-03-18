---
title: Network security
headerTitle: Network security
linkTitle: Network security
description: Network security
menu:
  v2.2:
    parent: security
    identifier: network-security
    weight: 30
isTocNested: true
showAsideToc: true
---
To ensure that ZNbase Platform ZNbaseDB runs in a trusted network environment you can restrict machine and port access. Here are some steps to ensure that:

- Servers running ZNbaseDB services are directly accessible only by the ZNbase Platform, servers running the application, and database administrators.
- Only ZNbase Platform and servers running applications can connect to ZNbaseDB services on the RPC ports. Access to the ZNbaseDB ports should be denied to everybody else.

Check the list of [default ports](../../reference/configuration/default-ports) that need to be opened on the ZNbaseDB servers for the ZNbase Platform and other applications to connect.
