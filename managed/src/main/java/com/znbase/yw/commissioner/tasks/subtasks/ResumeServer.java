/*
 * Copyright 2021 ZNbase, Inc. and Contributors
 *
 * Licensed under the Polyform Free Trial License 1.0.0 (the "License"); you
 * may not use this file except in compliance with the License. You
 * may obtain a copy of the License at
 *
 *     https://github.com/ZNbase/ZNbase-db/blob/master/licenses/POLYFORM-FREE-TRIAL-LICENSE-1.0.0.txt
 */

package com.ZNbase.yw.commissioner.tasks.subtasks;

import com.ZNbase.yw.commissioner.tasks.params.NodeTaskParams;
import com.ZNbase.yw.common.NodeManager;
import com.ZNbase.yw.common.ShellResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ZNbase.yw.models.Universe;
import com.ZNbase.yw.models.helpers.NodeDetails;

public class ResumeServer extends NodeTaskBase {

  public static class Params extends NodeTaskParams {
    // IP of node to be resumed.
    public String nodeIP = null;
  }

  @Override
  protected ResumeServer.Params taskParams() {
    return (ResumeServer.Params)taskParams;
  }

  public static final Logger LOG = LoggerFactory.getLogger(ResumeServer.class);

  private void resumeUniverse(final String nodeName) {
    Universe u = Universe.get(taskParams().universeUUID);
    if (u.getNode(nodeName) == null) {
      LOG.error("No node in universe with name " + nodeName);
      return;
    }
    LOG.info("Resumed the node " + nodeName + " from universe " + taskParams().universeUUID);
  }

  @Override
  public void run() {
    try {
      ShellResponse response = getNodeManager()
          .nodeCommand(NodeManager.NodeCommandType.Resume, taskParams());
      processShellResponse(response);
      setNodeState(NodeDetails.NodeState.Live);
      resumeUniverse(taskParams().nodeName);
    } catch (Exception e) {
      throw e;
    }

  }
}
