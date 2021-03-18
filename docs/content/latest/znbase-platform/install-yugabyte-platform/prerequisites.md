---
title: Prerequisites
headerTitle: Prerequisites
linkTitle: Prerequisites
description: Prerequisites for installing ZNbase Platform.
menu:
  latest:
    identifier: prerequisites
    parent: install-ZNbase-platform
    weight: 20
isTocNested: true
showAsideToc: true
---

ZNbase Platform first needs to be installed on a host computer, and then you configure ZNbase Platform to work in your on-premises private cloud or in a public cloud environment. In a public cloud environment, ZNbase Platform spawns instances for starting a ZNbaseDB universe. In a private cloud environment, you use ZNbase Platform to add nodes in which you want to be in the ZNbaseDB universe. To manage these nodes, ZNbase Platform requires SSH access to each of the nodes.

## Supported Linux Distributions

You can install ZNbase Platform on the following Linux distributions:

- Ubuntu 16.04 or 18.04 LTS.
- Red Hat Enterprise Linux (RHEL) 7 or later.
- CentOS 7 or later.
- Amazon Linux (AMI) 2014.03, 2014.09, 2015.03, 2015.09, 2016.03, 2016.09, 2017.03, 2017.09, 2018.03, 2.0
- Other [operating systems supported by Replicated](https://www.replicated.com/docs/distributing-an-application/supported-operating-systems/)

## Hardware Requirements

The node running ZNbase Platform should meet the following requirements:

- 4 cores or more
- 8 GB RAM or more
- 100 GB SSD disk or more
- 64-bit CPU architecture

## Preparing the Host

You prepare the host as follows:

- For a Docker-based installation, ZNbase Platform uses [Replicated scheduler](https://www.replicated.com/) for software distribution and container management. You need to ensure that the host can pull containers from the the [Replicated Docker Registries](https://help.replicated.com/docs/native/getting-started/docker-registries/).

  Replicated installs a compatible Docker version if its not pre-installed on the host. The current supported Docker version is 19.03.n.

- For a Kubernetes-based installation, you need to ensure that the host can pull container images from the [Quay.io](https://quay.io/) container registry.

### Airgapped Hosts

Installing ZNbase Platform on Airgapped hosts, without access to any Internet traffic (inbound or outbound) requires the following:

- Whitelisting endpoints: to install Replicated and ZNbase Platform on a host with no Internet connectivity, you have to first download the binaries on a computer that has Internet connectivity, and then copy the files over to the appropriate host. In case of restricted connectivity, the following endpoints have to be whitelisted to ensure that they are accessible from the host marked for installation:
  `https://downloads.ZNbase.com`
  `https://download.docker.com`

- Ensuring that Docker Engine version 19.03.n is available. If it is not installed, you need to follow the procedure described in [Installing Docker in airgapped](https://www.replicated.com/docs/kb/supporting-your-customers/installing-docker-in-airgapped/).
- Ensuring that the following ports are open on the ZNbase Platform host:
  - `8800` – HTTP access to the Replicated UI
  - `80` – HTTP access to the ZNbase Platform console
  - `22` – SSH
- Ensuring that attached disk storage (such as persistent EBS volumes on AWS) is 100 GB minimum
- Having ZNbase Platform airgapped install package. Contact ZNbase Support for more information.
- Signing the ZNbase Enterprise Platform license agreement. Contact ZNbase Support for more information.

  
