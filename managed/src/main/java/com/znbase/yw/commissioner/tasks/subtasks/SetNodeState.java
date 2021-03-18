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

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ZNbase.yw.commissioner.tasks.params.NodeTaskParams;
import com.ZNbase.yw.models.helpers.NodeDetails;

public class SetNodeState extends NodeTaskBase {

  public static final Logger LOG = LoggerFactory.getLogger(SetNodeState.class);

  public static class Params extends NodeTaskParams {
    public NodeDetails.NodeState state;
  }

  protected Params taskParams() {
    return (Params)taskParams;
  }

  @Override
  public String toString() {
    return super.getName() + "(" + taskParams().nodeName + ", " +
           taskParams().state.toString() + ")";
  }

  @Override
  public void run() {
    try {
      LOG.info("Updating node {} state to {} in universe {}.",
               taskParams().nodeName, taskParams().state, taskParams().universeUUID);
      setNodeState(taskParams().state);
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }
}
