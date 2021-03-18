// Copyright (c) ZNbase, Inc.

package com.ZNbase.yw.commissioner.tasks.params;

import java.util.Map;

public class KubernetesClusterInitParams extends CloudTaskParams {
  // The config for the target cluster.
  public Map<String, String> config;
}
