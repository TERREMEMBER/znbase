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


import java.util.UUID;

import com.ZNbase.yw.commissioner.tasks.UniverseTaskBase;
import com.ZNbase.yw.forms.UniverseTaskParams;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ZNbase.yw.forms.UniverseDefinitionTaskParams;
import com.ZNbase.yw.forms.UniverseDefinitionTaskParams.Cluster;
import com.ZNbase.yw.models.Universe;
import com.ZNbase.yw.models.Universe.UniverseUpdater;

public class UpdateSoftwareVersion extends UniverseTaskBase {
  public static final Logger LOG = LoggerFactory.getLogger(UpdateSoftwareVersion.class);

  // Parameters for marking universe update as a success.
  public static class Params extends UniverseTaskParams {
    // The software version to which user updated the universe.
    public String softwareVersion;
  }

  protected Params taskParams() {
    return (Params)taskParams;
  }

  @Override
  public String getName() {
    return super.getName() + "(" + taskParams().universeUUID + ")";
  }

  @Override
  public void run() {
    try {
      LOG.info("Running {}", getName());

      // Create the update lambda.
      UniverseUpdater updater = new UniverseUpdater() {
        @Override
        public void run(Universe universe) {
          // If this universe is not being edited, fail the request.
          UniverseDefinitionTaskParams universeDetails = universe.getUniverseDetails();
          if (!universeDetails.updateInProgress) {
            String errMsg = "UserUniverse " + taskParams().universeUUID + " is not being edited.";
            LOG.error(errMsg);
            throw new RuntimeException(errMsg);
          }
          universeDetails.getPrimaryCluster().userIntent.ybSoftwareVersion = taskParams().softwareVersion;
          for (Cluster cluster : universeDetails.getReadOnlyClusters()) {
            cluster.userIntent.ybSoftwareVersion = taskParams().softwareVersion;
          }
          universe.setUniverseDetails(universeDetails);
        }
      };
      // Perform the update. If unsuccessful, this will throw a runtime exception which we do not
      // catch as we want to fail.
      saveUniverseDetails(updater);

    } catch (Exception e) {
      String msg = getName() + " failed with exception "  + e.getMessage();
      LOG.warn(msg, e.getMessage());
      throw new RuntimeException(msg, e);
    }
  }
}
