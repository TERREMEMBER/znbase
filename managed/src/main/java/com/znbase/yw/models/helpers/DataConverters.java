// Copyright (c) ZNbase, Inc.

package com.ZNbase.yw.models.helpers;

import com.ZNbase.yw.models.Alert;
import com.ZNbase.yw.models.Alert.TargetType;

import com.ZNbase.yw.models.CustomerTask;

public class DataConverters {

  public static Alert.TargetType taskTargetToAlertTargetType(CustomerTask.TargetType srcType) {
    switch (srcType) {
      case Universe:
        return TargetType.UniverseType;
      case Cluster:
        return TargetType.ClusterType;
      case Table:
        return TargetType.TableType;
      case Provider:
        return TargetType.ProviderType;
      case Node:
        return TargetType.NodeType;
      case Backup:
        return TargetType.BackupType;
      case KMSConfiguration:
        return TargetType.KMSConfigurationType;
      default:
        return TargetType.TaskType;
    }
  }

  private DataConverters() {
  }
}
