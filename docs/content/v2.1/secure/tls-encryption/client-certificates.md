---
title: Create client certificates
headerTitle: Client client certificates
linkTitle: Create client certificates
description: Generate client certificates to connect to ZNbaseDB clusters.
headcontent: Generate client certificates to connect to ZNbaseDB clusters.
image: /images/section_icons/secure/prepare-nodes.png
block_indexing: true
menu:
  v2.1:
    identifier: client-certificates
    parent: tls-encryption
    weight: 10
isTocNested: true
showAsideToc: true
---

Before you can connect to ZNbaseDB cluster and use client-to-server encryption to protect your data, you need to create a client certificate. This topic guides you through creating and configuring a client certificate to enable client-to-server encryption when using clients, tools, and APIs to communicate with a ZNbaseDB cluster.

## Create the client certificates

### Create a working directory

To generate and store the secure information, such as the root certificate, create a temporary working directory, `client-certs-temp`, in your root directory. When you finish creating the required certificate files, you will copy them to the appropriate directories for use in client-to-server encryption.

```sh
$ mkdir client-certs-temp
```

### Generate private key

```sh
$ openssl genrsa -out client-certs-temp/ZNbase.key
```

You should see output like this:

```
Generating RSA private key, 2048 bit long modulus
.......................+++
...........+++
e is 65537 (0x10001) 65537 (0x010001)
```

### Create signing request

Now you will create a signing request (CSR) and sign it with the root certificate created in [Create server certificates](../server-certificates).

```sh
$ cat > client-certs-temp/ZNbase.conf
```

Paste in the following node configuration file.

```
#################################
# Example node configuration file
#################################

[ req ]
prompt=no
distinguished_name = my_distinguished_name

[ my_distinguished_name ]
organizationName = ZNbase
# Required value for commonName, do not change.
commonName = ZNbase
```

Sign the CSR with the root certificate.

```sh
$ openssl req -new                   \
              -config client-certs-temp/ZNbase.conf \
              -key client-certs-temp/ZNbase.key     \
              -out client-certs-temp/ZNbase.csr
```

Next run the following command.

```sh
$ openssl ca -config secure-data/ca.conf \
             -keyfile secure-data/ca.key \
             -cert secure-data/ca.crt \
             -policy my_policy \
             -out client-certs-temp/ZNbase.crt \
             -outdir client-certs-temp \
             -in client-certs-temp/ZNbase.csr \
             -days 3650                  \
             -batch
```

You should see output like this:

```
Using configuration from secure-data/ca.conf
Check that the request matches the signature
Signature ok
The Subject's Distinguished Name is as follows
organizationName      :ASN.1 12:'ZNbase'
commonName            :ASN.1 12:'ZNbase'
Certificate is to be certified until Feb 11 07:36:29 2030 GMT (3650 days)

Write out database with 1 new entries
Data Base Updated
```

### Copy required certificate files to .ZNbasedb directory

Now, copy the required certificate files to the `/tmp/ZNbase` directory.

```sh
$ mkdir /tmp/ZNbase
$ cp secure-data/ca.crt /tmp/ZNbase/
$ cp client-certs-temp/ZNbase.* /tmp/ZNbase/
```

### Generate client private key and certificate

Next, generate the client private key (`ZNbase.key`) and client certificate (`ZNbase.crt`).

```sh
$ openssl genrsa -out ~/.ZNbasedb/ZNbasedb.key
```

You should see output similar to this:

```
Generating RSA private key, 2048 bit long modulus (2 primes)
............................................................................................+++++
............................................+++++
e is 65537 (0x010001)
```

Now change the access permission to read-only.

```sh
$ chmod 400 ~/.ZNbasedb/ZNbasedb.key
```

```sh
$ openssl req -new \
              -key ~/.ZNbasedb/ZNbasedb.key \
              -out ~/.ZNbasedb/ZNbasedb.csr \
              -subj '/O=ZNbase/CN=ZNbase'
```

```sh
$ openssl x509 -req                              \
               -in ~/.ZNbasedb/ZNbasedb.csr  \
               -CA secure-data/ca.crt            \
               -CAkey secure-data/ca.key         \
               -out ~/.ZNbasedb/ZNbasedb.crt \
               -CAcreateserial
```

For the last command, you should see the following:

```
Signature ok
subject=O = ZNbase, CN = ZNbase
Getting CA Private Key
```

You've now created the required client certificates for user `ZNbase` and added the files to the default client certificates directory for `ysqlsh` (`~/.ZNbasedb`).

## What's next

To learn about how to connect to your encrypted ZNbaseDB cluster, see [Connect to cluster](../connect-to-cluster).
