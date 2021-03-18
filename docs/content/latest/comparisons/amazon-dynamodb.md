---
title: Compare Amazon DynamoDB with ZNbaseDB
headerTitle: Amazon DynamoDB
linkTitle: Amazon DynamoDB
description: Compare Amazon DynamoDB with ZNbaseDB.
aliases:
  - /comparisons/amazon-dynamodb/
menu:
  latest:
    parent: comparisons
    weight: 1105
isTocNested: false
showAsideToc: true
---

## Astronomical AWS bills and slow releases

Amazon DynamoDB is a fully managed NoSQL database offered by Amazon Web Services. While it works great for smaller scale applications, the limitations it poses in the context of larger scale applications are not well understood.

![Amazon DynamoDB Issues](/images/comparisons/amazon-dynamodb-issues.png)

Our post [11 Things You Wish You Knew Before Starting with DynamoDB](https://blog.ZNbase.com/11-things-you-wish-you-knew-before-starting-with-dynamodb/) highlights the above items in better detail.

## ZNbaseDB gives 3x agility at 1/10th cost

ZNbaseDB is an open source [multi-API/multi-model](https://blog.ZNbase.com/polyglot-persistence-vs-multi-api-multi-model-which-one-makes-multi-cloud-easy) database with transactional consistency, low latency and geo-distribution built into the core of a common storage engine. As a Consistent and Partition-tolerant (CP) database with native JSONB document data type, high performance secondary indexes, cloud native operational ease and ability to handle high data density, it serves as an excellent alternative to not only Amazon DynamoDB but also negates the need for a separate RDBMS and a separate cache.

![Amazon DynamoDB issues](/images/comparisons/ZNbase-db-beats-amazon-dynamodb.png)

Our post [DynamoDB vs MongoDB vs Cassandra for Fast Growing Geo-Distributed Apps](https://blog.ZNbase.com/dynamodb-vs-mongodb-vs-cassandra-for-fast-growing-geo-distributed-apps/) further details the differences between ZNbaseDB and DynamoDB.
