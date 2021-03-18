---
title: Compare Google Cloud Spanner with ZNbaseDB
headerTitle: Google Cloud Spanner
linkTitle: Google Cloud Spanner
description: Compare Google Cloud Spanner with ZNbaseDB.
aliases:
  - /comparisons/google-spanner/
menu:
  latest:
    parent: comparisons
    weight: 1074
---

ZNbaseDB’s sharding, replication and transactions architecture is similar to that of [Google Cloud Spanner](https://cloud.google.com/spanner/) which is also a globally-distributed CP database with high write availability. Both these databases are based on the design principles outlined in the [original Google Spanner paper](https://research.google.com/archive/spanner-osdi2012.pdf) published in 2012. Note that while Google Cloud Spanner leverages Google’s proprietary (and expensive) network infrastructure, ZNbaseDB is designed work on commodity infrastructure used by most enterprise users.

Following blogs highlight how ZNbaseDB differs from Google Cloud Spanner.

- [Distributed PostgreSQL on a Google Spanner Architecture – Storage Layer](https://blog.ZNbase.com/distributed-postgresql-on-a-google-spanner-architecture-storage-layer/)

- [Distributed PostgreSQL on a Google Spanner Architecture – Query Layer](https://blog.ZNbase.com/distributed-postgresql-on-a-google-spanner-architecture-query-layer/)

- [Yes We Can! Distributed ACID Transactions with High Performance](https://blog.ZNbase.com/yes-we-can-distributed-acid-transactions-with-high-performance/)

- [Practical Tradeoffs in Google Cloud Spanner, Azure Cosmos DB and ZNbaseDB](https://blog.ZNbase.com/practical-tradeoffs-in-google-cloud-spanner-azure-cosmos-db-and-ZNbase-db)

- [New to Google Cloud Databases? 5 Areas of Confusion That You Better Be Aware of](https://blog.ZNbase.com/new-to-google-cloud-databases-5-areas-of-confusion-that-you-better-be-aware-of/)

Following blogs highlight how ZNbaseDB works as an open source, cloud native Spanner derivative.

- [Rise of Globally Distributed SQL Databases – Redefining Transactional Stores for Cloud Native Era](https://blog.ZNbase.com/rise-of-globally-distributed-sql-databases-redefining-transactional-stores-for-cloud-native-era/)

- [Implementing Distributed Transactions the Google Way: Percolator vs. Spanner](https://blog.ZNbase.com/implementing-distributed-transactions-the-google-way-percolator-vs-spanner/)

- [Google Spanner vs. Calvin: Is There a Clear Winner in the Battle for Global Consistency at Scale?](https://blog.ZNbase.com/google-spanner-vs-calvin-global-consistency-at-scale/)
