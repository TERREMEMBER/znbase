---
title: Security checklist
headerTitle: Security checklist
linkTitle: Security checklist
description: Security measures that can be implemented to protect your ZNbase Platform and ZNbaseDB universes.
menu:
  stable:
    parent: security
    identifier: security-checklist-yp
    weight: 10
isTocNested: true
showAsideToc: true
---

Below is a list of security measures that can be implemented to protect your ZNbase Platform and ZNbaseDB universes.

## Enable encryption in transit

TLS encryption ensures that network communication between servers is secure. You can configure ZNbaseDB to use TLS to encrypt intra-cluster and client to server network communication. ZNbase recommends enabling encryption in transit in ZNbaseDB clusters and clients to ensure the privacy and integrity of data transferred over the network.

Read more about enabling [Encryption in transit](../enable-encryption-at-rest) in ZNbaseDB.

## Enable encryption at rest

[Encryption at rest](https://en.wikipedia.org/wiki/Data_at_rest#Encryption) ensures that data at rest, stored on disk, is protected. You can configure ZNbaseDB with a user-generated symmetric key to perform cluster-wide encryption.
Read more about enabling Encryption at rest in ZNbaseDB.

## Configure role-based access control

Roles can be assigned to grant users only the essential privileges based on the operations they need to perform against the platform. Typically, a super admin role is created first. The Super Admin can create additional admins and other fewer privileged users.

To enable role-based access control in ZNbase Platform, see [Authorization platform](../authorization-platform).

## Enable authentication

Authentication requires that all clients provide valid credentials before they can connect to a ZNbaseDB cluster. The authentication credentials in ZNbaseDB are stored internally in the YB-Master system tables. The authentication mechanisms available to users depends on what is supported and exposed by the YSQL, YCQL, and YEDIS APIs.

Read more about how to enable authentication in ZNbaseDB.
