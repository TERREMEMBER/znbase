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

import com.ZNbase.yw.commissioner.tasks.UniverseDefinitionTaskBase;
import com.ZNbase.yw.common.NodeManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.databind.JsonNode;
import com.ZNbase.yw.commissioner.tasks.params.NodeTaskParams;
import com.ZNbase.yw.forms.ITaskParams;
import com.ZNbase.yw.models.Universe.UniverseUpdater;
import com.ZNbase.yw.models.helpers.NodeDetails;

import play.api.Play;
import play.libs.Json;

public abstract class NodeTaskBase extends UniverseDefinitionTaskBase {
  public static final Logger LOG = LoggerFactory.getLogger(NodeTaskBase.class);

  private NodeManager nodeManager;
  public NodeManager getNodeManager() { return nodeManager; }

  @Override
  protected NodeTaskParams taskParams() {
    return (NodeTaskParams)taskParams;
  }

  @Override
  public void initialize(ITaskParams params) {
    super.initialize(params);
    this.nodeManager = Play.current().injector().instanceOf(NodeManager.class);
  }

  @Override
  public String getName() {
    NodeTaskParams taskParams = taskParams();
    return super.getName() + "(" + taskParams.universeUUID + ", " + taskParams.nodeName + ")";
  }

  @Override
  public JsonNode getTaskDetails() {
    return Json.toJson(taskParams);
  }

  // Helper API to update the db for the current node with the given state.
  public void setNodeState(NodeDetails.NodeState state) {
    // Persist the desired node information into the DB.
    UniverseUpdater updater = nodeStateUpdater(taskParams().universeUUID,
                                               taskParams().nodeName,
                                               state);
    saveUniverseDetails(updater);
  }
}
