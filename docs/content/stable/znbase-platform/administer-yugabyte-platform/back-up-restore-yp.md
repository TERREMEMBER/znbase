---
title: Back up and restore ZNbase Platform
headerTitle: Back up and restore ZNbase Platform
linkTitle: Back up and restore ZNbase Platform
description: Use a script file to back up and restore ZNbase Platform.
menu:
  stable:
    identifier: back-up-restore-yp
    parent: administer-ZNbase-platform
    weight: 10
isTocNested: true
showAsideToc: true
---

ZNbase Platform installations include configuration settings, certificates and keys, and other components required for orchestrating and managing ZNbaseDB universes.

You can use the ZNbase Platform backup script to back up an existing ZNbase Platform server and restore it, when needed, for disaster recovery or migrating to a new server.

## Back up a ZNbase Platform server

1. Copy the the ZNbase Platform backup script (`yb_platform_backup.sh`) from the ZNbase-db repository using the following `wget` command:

    ```sh
    $ wget https://github.com/ZNbase/ZNbase-db/blob/master/managed/devops/bin/yb_platform_backup.sh 
    ```

2. Run the `yb_platform_backup.sh` script using the `backup` command:

    ```sh
    ./yb_platform_backup.sh backup --output <output_path> [--data_dir <data_dir>] [--exclude_prometheus]
    ```

    `backup`: Command to run the back up of the ZNbase Platform server.
    *output_path*: Specifies the location for the `.tar` output file.
    *data_dir*: [optional] Specifies the data directory to be backed up. Default is `/opt/ZNbase`.
    *--exclude_prometheus* : [optional] Flag to exclude Prometheus metrics from the backup.

3. Verify that the backup `.tar` file, with the correct timestamp, is in the specified output directory.
4. Upload the backup file to your preferred storage location and delete it from the local disk.

## Restore a ZNbase Platform server

To restore the ZNbase Platform content from your saved backup, follow the steps below.

1. Copy the the `yb_platform_backup.sh` script from the ZNbase-db repository using the following `wget` command:

    ```sh
    $ wget https://github.com/ZNbase/ZNbase-db/blob/master/managed/devops/bin/yb_platform_backup.sh 
    ```

2. Copy the backup `.tar` file from your storage location.

3. Run the `yb_platform_backup.sh` script using the `restore` command:

    ```sh
    $ ./yb_platform_backup.sh restore --input <input_path> [--destination <destination>]
    ```

    - `restore`: Command to restore the ZNbase Platform content.
    - *input_path*: Path to the input `.tar` file.
    - *destination* : [Optional] Specifies the output location for data. Default is `/opt/ZNbase`.

Your restored ZNbase Platform is now ready to continue orchestrating and managing your universes and clusters.
