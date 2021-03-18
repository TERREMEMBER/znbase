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
import org.yb.client.ChangeLoadBalancerStateResponse;

import com.ZNbase.yw.commissioner.tasks.UniverseTaskBase;
import com.ZNbase.yw.common.services.YBClientService;
import com.ZNbase.yw.forms.ITaskParams;
import com.ZNbase.yw.forms.UniverseTaskParams;
import com.ZNbase.yw.models.Universe;

import org.yb.client.YBClient;
import play.api.Play;

// This class runs the task that helps modify the load balancer state maintained
// on the master leader.
public class LoadBalancerStateChange extends UniverseTaskBase {
  public static final Logger LOG = LoggerFactory.getLogger(LoadBalancerStateChange.class);

  // The YB client.
  public YBClientService ybService = null;

  // Parameters for placement info update task.
  public static class Params extends UniverseTaskParams {
    // When true, the load balancer state is enabled on the server side, else it is disabled.
    public boolean enable;
  }

  @Override
  protected Params taskParams() {
    return (Params)taskParams;
  }

  @Override
  public void initialize(ITaskParams params) {
    super.initialize(params);
    ybService = Play.current().injector().instanceOf(YBClientService.class);
  }

  @Override
  public String getName() {
    return super.getName() + "(" + taskParams().universeUUID + ", enable=" +
        taskParams().enable + ")";
  }

  @Override
  public void run() {
    ChangeLoadBalancerStateResponse resp;
    YBClient client = null;
    Universe universe = Universe.get(taskParams().universeUUID);
    String masterHostPorts = universe.getMasterAddresses();
    String certificate = universe.getCertificate();
    try {
      LOG.info("Running {}: masterHostPorts={}.", getName(), masterHostPorts);

      client = ybService.getClient(masterHostPorts, certificate);
      resp = client.changeLoadBalancerState(taskParams().enable);
    } catch (Exception e) {
      LOG.error("{} hit exception : {}", getName(), e.getMessage());
      throw new RuntimeException(e);
    } finally {
      ybService.closeClient(client, masterHostPorts);
    }

    if (resp != null && resp.hasError()) {
      String errorMsg = getName() + " failed due to error: " + resp.errorMessage();
      LOG.error(errorMsg);
      throw new RuntimeException(errorMsg);
    }
  }
}
