/*
 * Copyright 2019 ZNbase, Inc. and Contributors
 *
 * Licensed under the Polyform Free Trial License 1.0.0 (the "License"); you
 * may not use this file except in compliance with the License. You
 * may obtain a copy of the License at
 *
 *     https://github.com/ZNbase/ZNbase-db/blob/master/licenses/POLYFORM-FREE-TRIAL-LICENSE-1.0.0.txt
 */

package com.ZNbase.yw.commissioner.tasks.subtasks.cloud;

import com.ZNbase.yw.commissioner.tasks.CloudTaskBase;
import com.ZNbase.yw.commissioner.tasks.params.CloudTaskParams;
import com.ZNbase.yw.common.AccessManager;
import com.ZNbase.yw.models.Region;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import play.api.Play;

public class CloudAccessKeyCleanup extends CloudTaskBase {
  public static final Logger LOG = LoggerFactory.getLogger(CloudAccessKeyCleanup.class);

  public static class Params extends CloudTaskParams {
    public String regionCode;
  }

  @Override
  protected CloudAccessKeyCleanup.Params taskParams() {
    return (CloudAccessKeyCleanup.Params) taskParams;
  }

  @Override
  public void run() {
    String accessKeyCode = String.format("yb-%s-key", getProvider().name).toLowerCase();
    String regionCode = taskParams().regionCode;
    Region region = Region.getByCode(getProvider(), regionCode);
    if (region == null) {
      throw new RuntimeException("Region " +  regionCode + " not setup.");
    }

    AccessManager accessManager = Play.current().injector().instanceOf(AccessManager.class);
    accessManager.deleteKey(region.uuid, accessKeyCode);
  }
}
