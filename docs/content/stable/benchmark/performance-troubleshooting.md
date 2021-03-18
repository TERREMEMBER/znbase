---
title: Troubleshooting performance issues in ZNbaseDB
headerTitle: Performance Troubleshooting
linkTitle: Performance Troubleshooting
description: Common steps to take when investigating performance issues in ZNbaseDB
image: /images/section_icons/architecture/concepts.png
headcontent: Performance Troubleshooting
menu:
  stable:
    identifier: performance-troubleshooting
    parent: benchmark
    weight: 22
showAsideToc: true
isTocNested: true
---

Use this page for general guidance and steps you can take when troubleshooting the performance of your ZNbase cluster.

## Location of various files on a YB cluster

Note that these locations refer to the default for clusters intalled via ZNbase Platform.

### YB Software/Binaries on the cluster

The software packages are symlinked at /home/ZNbase/{master|tserver}. Be aware that the master & tserver may be at different versions of the software (e.g. during rolling software upgrades).

You should see something like this:
```
$ ls -lrt /home/ZNbase/master
total 4
lrwxrwxrwx. 1 ZNbase ZNbase   27 Jan 15 19:27 logs -> /mnt/d0/yb-data/master/logs
lrwxrwxrwx. 1 ZNbase ZNbase   66 Jan 15 19:28 bin -> /home/ZNbase/yb-software/ZNbase-2.5.1.0-b187-centos-x86_64/bin
lrwxrwxrwx. 1 ZNbase ZNbase   66 Jan 15 19:28 lib -> /home/ZNbase/yb-software/ZNbase-2.5.1.0-b187-centos-x86_64/lib
lrwxrwxrwx. 1 ZNbase ZNbase   72 Jan 15 19:28 linuxbrew -> /home/ZNbase/yb-software/ZNbase-2.5.1.0-b187-centos-x86_64/linuxbrew
lrwxrwxrwx. 1 ZNbase ZNbase   85 Jan 15 19:28 linuxbrew-xxxxxxxxxxxx -> /home/ZNbase/yb-software/ZNbase-2.5.1.0-b187-centos-x86_64/linuxbrew-xxxxxxxxxxxx
lrwxrwxrwx. 1 ZNbase ZNbase   71 Jan 15 19:28 postgres -> /home/ZNbase/yb-software/ZNbase-2.5.1.0-b187-centos-x86_64/postgres
lrwxrwxrwx. 1 ZNbase ZNbase   68 Jan 15 19:28 pylib -> /home/ZNbase/yb-software/ZNbase-2.5.1.0-b187-centos-x86_64/pylib
lrwxrwxrwx. 1 ZNbase ZNbase   68 Jan 15 19:28 share -> /home/ZNbase/yb-software/ZNbase-2.5.1.0-b187-centos-x86_64/share
lrwxrwxrwx. 1 ZNbase ZNbase   68 Jan 15 19:28 tools -> /home/ZNbase/yb-software/ZNbase-2.5.1.0-b187-centos-x86_64/tools
lrwxrwxrwx. 1 ZNbase ZNbase   65 Jan 15 19:28 ui -> /home/ZNbase/yb-software/ZNbase-2.5.1.0-b187-centos-x86_64/ui
lrwxrwxrwx. 1 ZNbase ZNbase   84 Jan 15 19:28 version_metadata.json -> /home/ZNbase/yb-software/ZNbase-2.5.1.0-b187-centos-x86_64/version_metadata.json
lrwxrwxrwx. 1 ZNbase ZNbase   66 Jan 15 19:28 www -> /home/ZNbase/yb-software/ZNbase-2.5.1.0-b187-centos-x86_64/www
-rw-rw-r--. 1 ZNbase ZNbase    0 Jan 15 19:29 master.out
-rw-rw-r--. 1 ZNbase ZNbase 2200 Jan 15 20:18 master.err
drwxr-xr-x. 2 ZNbase ZNbase   25 Jan 15 20:18 conf
```

### Config Files
You can find config files for the master and tserver at `/home/ZNbase/{master|tserver}/conf/server.conf`.

### Transaction Logs a.k.a WAL (write-ahead logs)
Assuming ZNbase was run with `--fs_data_dirs=/mnt/d0,/mnt/d1`, you can find WAL files at `/mnt/d*/yb-data/{master|tserver}/wals`.

To pretty print the contents of the WAL files, use the `log-dump` utility:

```
$ pwd
/home/ZNbase

$ ./tserver/bin/log-dump /mnt/d0/yb-data/tserver/wals/table-e85a116bc557403e82f57037e7b13879/tablet-05bef5ed6fb74cabb420b648b6f850e3/

# use -print_entries=pb to print the entire contents of each record
```

### Database (SSTable) Files for various tablets
You can find SSTable files at `/mnt/d*/yb-data/{master|tserver}/data`.

To pretty print the contents of these SSTable files, 

```
$ pwd
/home/ZNbase

$ ./tserver/bin/ldb dump --compression_type=snappy --db=/mnt/d0/yb-data/tserver/wals/table-e85a116bc557403e82f57037e7b13879/tablet-05bef5ed6fb74cabb420b648b6f850e3/
```

### Debug Logs (.INFO, .WARNING, .FATAL, etc.)
Debug logs are output to `/home/ZNbase/{master|tserver}/logs`.

### View Standard Output/Standard Error (stderr/stdout) of yb-tserver & yb-master process

These go to e.g. `/home/ZNbase/tserver/tserver.{err|out}.

## Setting gflags dynamically

A cautionary note -- setting string flags dynamically is not a good idea, as it is not thread safe. That being said, the `yb-ts-cli` utility allows changing gflags dynamically. _Note: For this step you must identify the server using its RPC port (not the HTTP port).

For example, to increase the verbose logging level to 2:

    $ ./yb-ts-cli --server_address=localhost:9100 set_flag v 2

## Slow Response Logs

Slow responses (which take more than 75% of the configured RPC timeout) are already logged at WARNING level in this format, with a breakdown of time in various stages.

```
W0325 06:47:13.032176 116514816 inbound_call.cc:204] Call yb.consensus.ConsensusService.UpdateConsensus from 127.0.0.1:61050 (request call id 22856) took 2644ms (client timeout 1000).
W0325 06:47:13.033341 116514816 inbound_call.cc:208] Trace:
0325 06:47:10.388015 (+     0us) service_pool.cc:109] Inserting onto call queue
0325 06:47:10.394859 (+  6844us) service_pool.cc:170] Handling call
0325 06:47:10.450697 (+ 55838us) raft_consensus.cc:1026] Updating replica for 0 ops
0325 06:47:13.032064 (+2581367us) raft_consensus.cc:1242] Filling consensus response to leader.
0325 06:47:13.032106 (+    42us) spinlock_profiling.cc:233] Waited 2.58 s on lock 0x108dc3d40. stack: 0000000103d63b0c 0000000103d639fc 00000001040ac908 0000000102f698ec 0000000102f698a4 0000000102f93039 0000000102f7e124 0000000102fdcf7b 0000000102fd90c9 00000001\
02504396 00000001032959f5 0000000103470473 0000000103473491 00000001034733ef 000000010347338b 000000010347314c
0325 06:47:13.032168 (+    62us) inbound_call.cc:125] Queueing success response
```

## Viewing real-time metrics

You can view metrics of various ZNbase processes at a particular node (e.g. 127.0.0.1) at these ports:

| Process | Address
-------------|-----------|
Master | 127.0.0.1:7000
TServer | 127.0.0.1:9000
Yedis | 127.0.0.1:11000
YCQL | 127.0.0.1:12000
YSQL | 127.0.0.1:13000

For each process, you can see the following types of metrics:
| Description | Endpoint
-------------|-----------|
Per-Tablet, JSON Metrics | /metrics
Per-Table, Prometheus Metrics | /prometheus-metrics

## Turning RPC tracing on

To turn on tracing, you should set the `enable_tracing` flag:

    $ ./yb-ts-cli --server_address=localhost:9100 set_flag enable_tracing 1

To turn tracing on for all RPCs, not just the slow ones, including the `enable_tracing` flag, you may also set the `rpc_dump_all_traces` gflag:

    $ ./yb-ts-cli --server_address=localhost:9100 set_flag rpc_dump_all_traces 1

## Print contents of a proto file

To dump the contents of a file containing a proto (such as a file in the consensus-meta or tablet-meta directory), use 'yb-pbc-dump' utility:
```
$ ./yb-pbc-dump /mnt/d0/yb-data/tserver/consensus-meta/dd57975ef2f2440497b5d96fc32146d3
$ ./yb-pbc-dump /mnt/d0/yb-data/tserver/tablet-meta/bfb3f18736514eeb841b0307a066e66c
```

Note: On mac, the environment variable DYLD_FALLBACK_LIBRARY_PATH needs to be set for pbc-dump to work. Add the following to ~/.bash_profile
```
export DYLD_FALLBACK_LIBRARY_PATH=~/code/ZNbase/build/latest/rocksdb-build
```

## yb-ts-cli 
You can run various tablet related commands with yb-ts-cli by pointing at the master:
```
./yb-ts-cli list_tablets --server_address=localhost:9000
./yb-ts-cli dump_tablet --server_address=localhost:9000 e1bc59288ee849ab850ae0a40bd88649
```

## yb-admin
You can run various commands with `yb-admin`. Just specify the full set of master `{ip:ports}` with `-master_addresses`:
```
# Get all tables
./yb-admin -master_addresses 127.0.0.1:7000,127.0.0.2:7000,127.0.0.3:7000 list_tables
# Get all tablets for a specific table
./yb-admin -master_addresses 127.0.0.1:7000,127.0.0.2:7000,127.0.0.3:7000 list_tablets yb_load_test 
# List the tablet servers for each tablet
./yb-admin -master_addresses 127.0.0.1:7000,127.0.0.2:7000,127.0.0.3:7000 list_tablet_servers $(./yb-admin -master_addresses 127.0.0.1:7000,127.0.0.2:7000,127.0.0.3:7000 list_tablets yb_load_test)
# List all tablet servers
./yb-admin -master_addresses 127.0.0.1:7000,127.0.0.2:7000,127.0.0.3:7000 list_all_tablet_servers
# List all masters
./yb-admin -master_addresses 127.0.0.1:7000,127.0.0.2:7000,127.0.0.3:7000 list_all_masters
# Output master state to console
./yb-admin -master_addresses 127.0.0.1:7000,127.0.0.2:7000,127.0.0.3:7000 dump_masters_state
```
