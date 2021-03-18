---
title: FAQs about operating ZNbaseDB clusters
headerTitle: Operations FAQ
linkTitle: Operations FAQ
description: Answers to common questions about operating ZNbaseDB clusters
menu:
  latest:
    identifier: faq-operations
    parent: faq
    weight: 2720
isTocNested: false
showAsideToc: true
---

## Do ZNbaseDB clusters need an external load balancer?

For YSQL, an external load balancer is recommended, but note the following:

- If you use the [ZNbaseDB JDBC driver (beta)](../../reference/drivers/ZNbasedb-jdbc-driver) with an application regularly opens and closes connections to clients, then the ZNbaseDB JDBC driver effectively provides basic load balancing by randomly using connections to your nodes.
- If you use the [Spring Data ZNbase driver (beta)](../../reference/drivers/spring-data-ZNbasedb) with your Spring application, then the underlying ZNbaseDB JDBC driver provides basic load balancing.
- If you have an application that is not in the same Kubernetes cluster, then you should use an external load balancing system.

For YCQL, ZNbaseDB provides automatic load balancing.

## Can write ahead log (WAL) files be cleaned up or reduced in size?

For most ZNbaseDB deployments, you should not need to adjust the configuration flags for the write ahead log (WAL). While your data size is small and growing, the WAL files may seem to be much larger, but over time, the WAL files should reach their steady state while the data size continues to grow and become larger than the WAL files.

WAL files are per tablet and the retention policy is managed by the following two `yb-tserver` configuration flags:

- [`--log_min_segments_to_retain`](../../reference/configuration/yb-tserver/#log-min-segments-to-retain)
- [`--log_min_seconds_to_retain`](../../reference/configuration/yb-tserver/#log-min-seconds-to-retain)

Also, the following `yb-tserver` configuration flag is a factor in the size of each WAL file before it is rolled into a new one:

- [`--log_segment_size_mb`](../../reference/configuration/yb-tserver/#log-segment-size-mb) – default is `64`.
