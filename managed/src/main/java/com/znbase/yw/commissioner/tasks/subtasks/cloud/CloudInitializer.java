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

import com.ZNbase.yw.cloud.AWSInitializer;
import com.ZNbase.yw.cloud.AbstractInitializer;
import com.ZNbase.yw.cloud.GCPInitializer;
import com.ZNbase.yw.cloud.AZUInitializer;
import com.ZNbase.yw.commissioner.Common;
import com.ZNbase.yw.commissioner.tasks.CloudTaskBase;
import com.ZNbase.yw.commissioner.tasks.params.CloudTaskParams;
import com.ZNbase.yw.models.Provider;
import play.api.Play;

public class CloudInitializer extends CloudTaskBase {
  public static class Params extends CloudTaskParams {
    public String regionCode;
  }

  @Override
  protected CloudInitializer.Params taskParams() {
    return (CloudInitializer.Params) taskParams;
  }

  @Override
  public void run() {
    Provider cloudProvider = getProvider();
    AbstractInitializer initializer;
    switch (Common.CloudType.valueOf(cloudProvider.code)) {
      case aws:
        initializer = Play.current().injector().instanceOf(AWSInitializer.class);
        break;
      case gcp:
        initializer = Play.current().injector().instanceOf(GCPInitializer.class);
        break;
      case azu:
        initializer = Play.current().injector().instanceOf(AZUInitializer.class);
        break;
      default:
        throw new RuntimeException(cloudProvider.code + " does not have an initializer.");
    }
    initializer.initialize(cloudProvider.customerUUID, cloudProvider.uuid);
  }
}
