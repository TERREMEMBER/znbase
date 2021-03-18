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

import com.fasterxml.jackson.databind.JsonNode;
import com.ZNbase.yw.commissioner.tasks.CloudTaskBase;
import com.ZNbase.yw.commissioner.tasks.params.CloudTaskParams;
import com.ZNbase.yw.common.NetworkManager;
import com.ZNbase.yw.models.Region;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import play.api.Play;

public class CloudRegionCleanup extends CloudTaskBase {
  public static final Logger LOG = LoggerFactory.getLogger(CloudRegionCleanup.class);

  public static class Params extends CloudTaskParams {
    public String regionCode;
  }

  @Override
  protected CloudRegionCleanup.Params taskParams() {
    return (CloudRegionCleanup.Params)taskParams;
  }

  @Override
  public void run() {
    String regionCode = taskParams().regionCode;
    Region region = Region.getByCode(getProvider(), regionCode);
    if (region == null) {
      throw new RuntimeException("Region " +  regionCode + " doesn't exists.");
    }
    NetworkManager networkManager = Play.current().injector().instanceOf(NetworkManager.class);

    JsonNode vpcInfo = networkManager.cleanup(region.uuid);
    if (vpcInfo.has("error") || !vpcInfo.has(regionCode)) {
      throw new RuntimeException("Region cleanup failed for: " + regionCode);
    }
    region.delete();
  }
}
