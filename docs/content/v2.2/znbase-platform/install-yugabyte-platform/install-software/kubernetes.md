---
title: Install ZNbase Platform software - Kubernetes
headerTitle: Install ZNbase Platform software - Kubernetes
linkTitle: Install software 
description: Install ZNbase Platform software in your Kubernetes environment.
menu:
  v2.2:
    parent: install-ZNbase-platform
    identifier: install-software-2-kubernetes
    weight: 77
isTocNested: true
showAsideToc: true
---

<ul class="nav nav-tabs-alt nav-tabs-yb">

  <li >
    <a href="/v2.2/ZNbase-platform/install-ZNbase-platform/install-software/default" class="nav-link">
      <i class="fas fa-cloud"></i>
      Default
    </a>
  </li>

  <li>
    <a href="/v2.2/ZNbase-platform/install-ZNbase-platform/install-software/kubernetes" class="nav-link active">
      <i class="fas fa-cubes" aria-hidden="true"></i>
      Kubernetes
    </a>
  </li>

  <li >
    <a href="/v2.2/ZNbase-platform/install-ZNbase-platform/install-software/airgapped" class="nav-link">
      <i class="fas fa-unlink"></i>
      Airgapped
    </a>
  </li>

</ul>

## Prerequisites

Before you install ZNbase Platform on a Kubernetes cluster, make sure you:

- Create a ZNbase-helm service account.
- Create a `kubeconfig` file for configuring access to the Kubernetes cluster.

### Create a ZNbase-helm service account

1. Run the `wget` command to get a copy of the `ZNbase-rbac.yaml` YAML file.

```sh
wget https://raw.githubusercontent.com/ZNbase/charts/master/stable/ZNbase/ZNbase-rbac.yaml

2. Run the following `kubectl` command to apply the YAML file.

```sh
$ kubectl apply -f ZNbase-rbac.yaml
```

The following output should appear:

```
serviceaccount "ZNbase-helm" created
clusterrolebinding "ZNbase-helm" created
```

## Create a `kubeconfig` file for a Kubernetes cluster

To create a `kubeconfig` file for a ZNbase-helm service account:

1. Run the following `wget` command to get the Python script for generating the `kubeconfig` file:

    ```sh
    wget https://raw.githubusercontent.com/ZNbase/charts/master/stable/ZNbase/generate_kubeconfig.py
    ```

2. Run the following command to generate the `kubeconfig` file:

    ```sh
    python generate_kubeconfig.py -s ZNbase-helm
    ```

The following output should appear:

    ```
    Generated the kubeconfig file: /tmp/ZNbase-helm.conf
    ```

3. Upload the generated `kubeconfig` file as the `kubeconfig` in the ZNbase Platform provider configuration.

## Install ZNbase Platform on a Kubernetes cluster

1. Create a namespace using the `kubectl create namespace` command:

    ```sh
    kubectl create namespace yb-platform
    ```

2. Apply the ZNbase Platform secret (obtained from [ZNbase](https://www.ZNbase.com/platform/#request-trial-form) by running the following `kubectl create` command:

    ```sh
    $ kubectl create -f ZNbase-k8s-secret.yml -n yb-platform
    ```

    You should see output that the secret was created, like this:

    ```
    secret/ZNbase-k8s-pull-secret created
    ```

3. Run the following `helm repo add` command to clone the [ZNbaseDB charts repository](https://charts.ZNbase.com/).

    ```sh
    $ helm repo add ZNbasedb https://charts.ZNbase.com
    ```

    A message should appear, similar to this:

    ```
    "ZNbasedb" has been added to your repositories
    ```

    To search for the available chart version, run this command:

    ```sh
    $ helm search repo ZNbasedb/yugaware -l
    ```

    The latest Helm Chart version and App version will be displayed.

    ```
    NAME               	CHART VERSION	APP VERSION	DESRIPTION
    ZNbasedb/ZNbase	2.2.3        	2.2.3.0	ZNbaseDB is the high-performance distributed ..
    ```

4. Run the following `helm install` command to install ZNbase Platform (`yugaware`) Helm chart:

```sh
helm install yw-test ZNbasedb/yugaware --version 2.2.3 -n yb-platform --wait
```

A message is output that the deployment succeeded.

## Delete the Helm installation of ZNbase Platform

To delete the Helm install, run the following `helm del` command:

```sh
helm del --purge yw-test -n yb-platform
```
