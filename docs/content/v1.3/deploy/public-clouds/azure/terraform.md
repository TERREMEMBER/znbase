
## Prerequisites

1. Download and install [terraform](https://www.terraform.io/downloads.html). 


2. Verify by the `terraform` command, it should print a help message that looks similar to that shown below.

```sh
$ terraform
```

```
Usage: terraform [--version] [--help] <command> [args]
...
Common commands:
    apply              Builds or changes infrastructure
    console            Interactive console for Terraform interpolations
    destroy            Destroy Terraform-managed infrastructure
    env                Workspace management
    fmt                Rewrites config files to canonical format
```


## 1. Create a terraform config file

Create a terraform config file called `ZNbase-db-config.tf` and add following details to it. The terraform module can be found in the [terraform-azure-ZNbase github repository](https://github.com/ZNbase/terraform-azure-ZNbase).

```sh
provider "azurerm" {
  # Provide your Azure Creadentilals 
    subscription_id = "AZURE_SUBSCRIPTION_ID"
    client_id       = "AZURE_CLIENT_ID"
    client_secret   = "AZURE_CLIENT_SECRET"
    tenant_id       = "AZURE_TENANT_ID"
}

module "ZNbase-db-cluster" {
  # The source module used for creating AWS clusters.
  source = "github.com/ZNbase/terraform-azure-ZNbase"

  # The name of the cluster to be created, change as per need.
  cluster_name = "test-cluster"

  # key pair.
  ssh_private_key = "SSH_PRIVATE_KEY_HERE"
  ssh_public_key = "SSH_PUBLIC_KEY_HERE"
  ssh_user = "SSH_USER_NAME_HERE"

  # The region name where the nodes should be spawned.
  region_name = "YOUR VPC REGION"

  # The name of resource  group in which all Azure resource will be created. 
  resource_group = "test-ZNbase"

  # Replication factor.
  replication_factor = "3"

  # The number of nodes in the cluster, this cannot be lower than the replication factor.
  node_count = "3"
}
```

**NOTE:** To insatll terraform and configure it for Azure, follow steps given [here](https://docs.microsoft.com/en-gb/azure/virtual-machines/linux/terraform-install-configure)

## 2. Create a cluster

Init terraform first if you have not already done so.

```sh
$ terraform init
```

Now run the following to create the instances and bring up the cluster.

```sh
$ terraform apply
```

Once the cluster is created, you can go to the URL `http://<node ip or dns name>:7000` to view the UI. You can find the node's ip or dns by running the following:

```sh
$ terraform state show azurerm_virtual_machine.ZNbase-Node[0]
```

You can access the cluster UI by going to any of the following URLs.

You can check the state of the nodes at any point by running the following command.

```sh
$ terraform show
```


## 3. Verify resources created

The following resources are created by this module:

- `module.azure-ZNbase.azurerm_virtual_machine.ZNbase-Node` The Azure VM instances.

For cluster named `test-cluster`, the instances will be named `ZNbase-test-cluster-node-1`, `ZNbase-test-cluster-node-2`, `ZNbase-test-cluster-node-3`.

- `module.azure-ZNbase.azurerm_network_security_group.ZNbase-SG` The security group that allows the various clients to access the ZNbaseDB cluster.

For cluster named `test-cluster`, this security group will be named `ZNbase-test-cluster-SG` with the ports 7000, 9000, 9042, 7100, 9200 and 6379 open to all other instances in the same security group.

- `module.azure-ZNbase.null_resource.create_ZNbase_universe` A local script that configures the newly created instances to form a new ZNbaseDB universe.
- `module.azure-ZNbase.azurerm_network_interface.ZNbase-NIC` The Azure network interface for VM instance. 
  
For cluster named `test-cluster`, the network interface will be named `ZNbase-test-cluster-NIC-1`, `ZNbase-test-cluster-NIC-2`, `ZNbase-test-cluster-NIC-3`.

## 4. Destroy the cluster (optional)

To destroy what we just created, you can run the following command.

```sh
$ terraform destroy
```
