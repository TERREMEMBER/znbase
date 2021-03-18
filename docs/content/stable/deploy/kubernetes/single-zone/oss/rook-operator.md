---
title: Deploy on Kubernetes clusters using Rook operator
headerTitle: Open source Kubernetes
linkTitle: Open source Kubernetes
description: Deploy ZNbaseDB on OSS Kubernetes clusters using the Rook ZNbaseDB operator.
menu:
  stable:
    parent: deploy-kubernetes-sz
    name: Open Source
    identifier: k8s-oss-4
    weight: 621
type: page
isTocNested: true
showAsideToc: true
---


<ul class="nav nav-tabs-alt nav-tabs-yb">
  <li >
    <a href="{{< relref "./helm-chart.md" >}}" class="nav-link">
      <i class="fas fa-cubes" aria-hidden="true"></i>
      Helm chart
    </a>
  </li>
  <li >
    <a href="{{< relref "./ZNbase-operator.md" >}}" class="nav-link">
      <i class="fas fa-cubes" aria-hidden="true"></i>
      ZNbaseDB operator
    </a>
  </li>
  <li >
    <a href="{{< relref "./operator-hub.md" >}}" class="nav-link">
      <i class="fas fa-cubes" aria-hidden="true"></i>
      Operator Hub
    </a>
  </li>
  <li>
    <a href="{{< relref "./rook-operator.md" >}}" class="nav-link active">
      <i class="fas fa-cubes" aria-hidden="true"></i>
      Rook operator
    </a>
  </li>
</ul>



[Rook](https://rook.io) is an open source, cloud-native storage orchestrator for Kubernetes, providing the platform, framework, and support that can turn ZNbaseDB clusters into self-managing, self-scaling, and self-healing storage services. Rook automates storage-layer tasks, including deployment, bootstrapping, configuration, provisioning, scaling, upgrading, migration, disaster recovery, monitoring, and resource management.

The [Rook ZNbaseDB operator](https://rook.io/docs/rook/v1.1/ZNbasedb.html) is a custom controller that uses Custom Resource Definition (CRD) to extend the Kubernetes API and automate deploying, scaling, and managing ZNbaseDB clusters.  Based on the  _desired state_ that you specified in the CRD, the Rook operator observes (watching for changes in state and health), analyzes (comparing current to desired state), and acts (applying changes to the cluster) to maintain the desired state. For details, see [ZNbaseDB Cluster CRD](https://rook.io/docs/rook/v1.1/ZNbasedb-cluster-crd.html).

## Before you begin

Verify that your Kubernetes cluster is ready for Rook by reviewing the [Kubernetes cluster prerequisites for using the Rook operator](https://github.com/rook/rook/blob/master/Documentation/k8s-pre-reqs.md).

Rook must be installed — see the [Rook GitHub Repository](https://github.com/rook/rook). You can install Rook by running the following command:

```sh
git clone git@github.com:rook/rook.git
```

## Deploy the Rook ZNbaseDB operator

To deploy the ZNbaseDB operator:

1. Change your directory to the Rook directory containing the ZNbaseDB example files.

    ```sh
    cd cluster/examples/kubernetes/ZNbasedb
    ```

2. Create the Rook ZNbaseDB operator by running the following command:

    ```sh
    kubectl create -f operator.yaml
    ```

3. Observe the Rook operator by running the following command:

    ```sh
    kubectl -n rook-ZNbasedb-system get pods
    ```

## Create the ZNbaseDB Cluster CRD

When using the Rook ZNbaseDB operator, your ZNbaseDB clusters are controlled using the custom resource object (`ybclusters.ZNbasedb.rook.io`). The Custom Resource Definition (CRD), used to create this object, is specified in the `cluster.yaml` file.  

A sample Custom Resource Definition (CRD) file, called `cluster.yaml`, is located in the following Rook directory:

```sh
cluster/examples/kubernetes/ZNbasedb
```

Make a copy of the sample CRD file (`cluster.yaml`)  and modify it as needed. For details on the configuration options, see [ZNbaseDB CRD](https://rook.io/docs/rook/v1.1/ZNbasedb-cluster-crd.html).

## Create a simple ZNbaseDB cluster

1. Create your ZNbaseDB cluster by running the following command:

    ```sh
    kubectl create -f cluster.yaml
    ```

2. Verify that the custom resource object was created by using the following command:

    ```sh
    kubectl -n rook-ZNbasedb get ybclusters.ZNbasedb.rook.io
    ```

3. Verify that the number of YB-Master and YB- TServer services that are running match the number you specified in the `cluster.yaml` file by running the following command:

    ```sh
    kubectl -n rook-ZNbasedb get pods
    ```

## Use ZNbaseDB

When all of the pods in ZNbaseDB cluster are running, you can use the YSQL shell to access the YSQL API, which is PostgreSQL-compliant.

```console
kubectl exec -it yb-tserver-rook-ZNbasedb-0 -- ysqlsh  -h yb-tserver-rook-ZNbasedb-0  --echo-queries
```

For details on the YSQL API, see:

- [Explore YSQL](../../../../../quick-start/explore-ysql/)
- [YSQL Reference](../../../../../api/ysql/) 

## Cleanup

Run the commands below to clean up all resources created above.

**NOTE:** This will destroy your database and delete all of its data.

```console
kubectl delete -f cluster.yaml
kubectl delete -f operator.yaml
```

Manually delete any Persistent Volumes that were created for this ZNbaseDB cluster.

## Troubleshooting

### Review the operator logs

If the cluster does not start, run following command to take a look at operator logs.

```sh
kubectl -n rook-ZNbasedb-system logs -l app=rook-ZNbasedb-operator
```

### Review the ZNbaseDB logs

If everything is OK in the operator logs, check the ZNbaseDB logs for YB-Master and YB-TServer.

```sh
kubectl -n rook-ZNbasedb logs -l app=yb-master-rook-ZNbasedb
kubectl -n rook-ZNbasedb logs -l app=yb-tserver-rook-ZNbasedb
```
