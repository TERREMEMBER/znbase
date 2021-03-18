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
import com.ZNbase.yw.commissioner.Common;
import com.ZNbase.yw.commissioner.tasks.CloudTaskBase;
import com.ZNbase.yw.commissioner.tasks.params.CloudTaskParams;
import com.ZNbase.yw.common.NetworkManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import play.api.Play;


public class CloudSetup extends CloudTaskBase {
  public static final Logger LOG = LoggerFactory.getLogger(CloudRegionSetup.class);

  public static class Params extends CloudTaskParams {
    public String customPayload;
  }

  @Override
  protected Params taskParams() {
    return (Params)taskParams;
  }

  @Override
  public void run() {
    NetworkManager networkManager = Play.current().injector().instanceOf(NetworkManager.class);
    // TODO(bogdan): we do not actually do anything with this response, so can NOOP if not
    // creating any elements?
    JsonNode response = networkManager.bootstrap(
        null,
        taskParams().providerUUID,
        taskParams().customPayload);
    if (response.has("error")) {
      throw new RuntimeException(response.get("error").asText());
    }
  }
}
