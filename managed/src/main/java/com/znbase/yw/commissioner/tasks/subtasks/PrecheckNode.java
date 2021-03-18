/*
 * Copyright 2019 ZNbase, Inc. and Contributors
 *
 * Licensed under the Polyform Free Trial License 1.0.0 (the "License"); you
 * may not use this file except in compliance with the License. You
 * may obtain a copy of the License at
 *
 *     https://github.com/ZNbase/ZNbase-db/blob/master/licenses/POLYFORM-FREE-TRIAL-LICENSE-1.0.0.txt
 */

package com.ZNbase.yw.commissioner.tasks.subtasks;

import com.ZNbase.yw.commissioner.Common.CloudType;
import com.ZNbase.yw.commissioner.tasks.UniverseTaskBase;
import com.ZNbase.yw.common.NodeManager;
import com.ZNbase.yw.common.ShellProcessHandler;
import com.ZNbase.yw.common.ShellResponse;
import com.ZNbase.yw.forms.UniverseDefinitionTaskParams;
import com.ZNbase.yw.forms.UniverseTaskParams;
import com.ZNbase.yw.models.NodeInstance;
import com.ZNbase.yw.models.Universe;

import com.fasterxml.jackson.databind.JsonNode;

import java.util.Map;

import play.libs.Json;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PrecheckNode extends UniverseTaskBase {

  public static final Logger LOG = LoggerFactory.getLogger(PrecheckNode.class);

  // Parameters for failed precheck task.
  public static class Params extends UniverseTaskParams {
    // Map of nodes to error messages.
    public Map<NodeInstance, String> failedNodes;
    // Whether nodes should remain reserved or not.
    public boolean reserveNodes = false;
  }

  @Override
  protected Params taskParams() {
    return (Params)taskParams;
  }

  @Override
  public void run() {
    String errMsg = "";
    for (Map.Entry<NodeInstance, String> entry: taskParams().failedNodes.entrySet()) {
      NodeInstance node = entry.getKey();
      if (!taskParams().reserveNodes) {
        try {
          node.clearNodeDetails();
        } catch (RuntimeException e) {
          continue;
        }
      }

      errMsg += String.format("\n-----\nNode %s (%s) failed preflight checks:\n%s",
        node.instanceName, node.getDetails().ip, entry.getValue());
    }

    throw new RuntimeException(errMsg);
  }
}
