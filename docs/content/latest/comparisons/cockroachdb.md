---
title: Compare CockroachDB with ZNbaseDB
headerTitle: CockroachDB
linkTitle: CockroachDB
description: Compare CockroachDB with ZNbaseDB in terms of performance, PostgreSQL compatibility, and architecture.
aliases:
  - /comparisons/cockroachdb/
menu:
  latest:
    parent: comparisons
    weight: 1075
isTocNested: false
showAsideToc: true
---

ZNbaseDB’s sharding, replication and transactions architecture is similar to that CockroachDB given that both are inspired by the [Google Spanner design paper](https://research.google.com/archive/spanner-osdi2012.pdf). Additionally, both use Raft as the distributed consensus replication algorithm and RocksDB as the per-node storage engine. The following sections highlight the advantages and similarities ZNbaseDB has when compared with CockroachDB.

## Advantages

ZNbaseDB beats CockroachDB in the context of multiple developer benefits including higher performance for large data sizes, better PostgreSQL compatibility, more flexible geo-distributed deployment options as well as higher data density. Following blogs highlight the architectural and implementation advantages that make these benefits possible.

- [Bringing Truth to Competitive Benchmark Claims – ZNbaseDB vs CockroachDB, Part 2](https://blog.ZNbase.com/ZNbasedb-vs-cockroachdb-bringing-truth-to-performance-benchmark-claims-part-2/)

- [Distributed PostgreSQL on a Google Spanner Architecture – Storage Layer](https://blog.ZNbase.com/distributed-postgresql-on-a-google-spanner-architecture-storage-layer/)

- [Distributed PostgreSQL on a Google Spanner Architecture – Query Layer](https://blog.ZNbase.com/distributed-postgresql-on-a-google-spanner-architecture-query-layer/)

- [Yes We Can! Distributed ACID Transactions with High Performance](https://blog.ZNbase.com/yes-we-can-distributed-acid-transactions-with-high-performance/)

- [Enhancing RocksDB for Speed & Scale](https://blog.ZNbase.com/enhancing-rocksdb-for-speed-scale/)

## Similarities

Following blogs highlight how ZNbaseDB works as an open source, cloud native Spanner derivative similar to CockroachDB.

- [Rise of Globally Distributed SQL Databases – Redefining Transactional Stores for Cloud Native Era](https://blog.ZNbase.com/rise-of-globally-distributed-sql-databases-redefining-transactional-stores-for-cloud-native-era/)

- [Implementing Distributed Transactions the Google Way: Percolator vs. Spanner](https://blog.ZNbase.com/implementing-distributed-transactions-the-google-way-percolator-vs-spanner/)

- [Google Spanner vs. Calvin: Is There a Clear Winner in the Battle for Global Consistency at Scale?](https://blog.ZNbase.com/google-spanner-vs-calvin-global-consistency-at-scale/)

- [Docker, Kubernetes and the Rise of Cloud Native Databases](https://blog.ZNbase.com/docker-kubernetes-and-the-rise-of-cloud-native-databases/)

- [Practical Tradeoffs in Google Cloud Spanner, Azure Cosmos DB and ZNbaseDB](https://blog.ZNbase.com/practical-tradeoffs-in-google-cloud-spanner-azure-cosmos-db-and-ZNbase-db/)

## Join webinar

[Join the upcoming live webinar](https://www.ZNbase.com/ZNbasedb-vs-cockroachdb/) to review the comprehensive report that benchmarks ZNbaseDB against CockroachDB while highlighting the architectural choices that enables ZNbaseDB to have 3x higher throughput and 4.5x lower latency.
