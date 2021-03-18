// Copyright (c) ZNbase, Inc.

package com.ZNbase.yw.commissioner.tasks.subtasks;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ZNbase.yw.commissioner.tasks.UniverseTaskBase;
import com.ZNbase.yw.common.AlertDefinitionTemplate;
import com.ZNbase.yw.forms.UniverseTaskParams;
import com.ZNbase.yw.models.AlertDefinition;
import com.ZNbase.yw.models.Customer;
import com.ZNbase.yw.models.Universe;

public class CreateAlertDefinitions extends UniverseTaskBase {
  public static final Logger LOG = LoggerFactory.getLogger(CreateAlertDefinitions.class);

  protected UniverseTaskParams taskParams() {
    return (UniverseTaskParams) taskParams;
  }

  @Override
  public String getName() {
    return super.getName() + "(" + taskParams().universeUUID + ")";
  }

  @Override
  public void run() {
    try {
      LOG.info("Running {}", getName());
      Universe universe = Universe.get(taskParams().universeUUID);
      Customer customer = Customer.get(universe.customerId);
      String nodePrefix = universe.getUniverseDetails().nodePrefix;

      for (AlertDefinitionTemplate definition : AlertDefinitionTemplate.values()) {
        if (definition.isCreateForNewUniverse()) {
          AlertDefinition.create(customer.uuid, universe.universeUUID, definition.getName(),
              definition.buildTemplate(nodePrefix), true);
        }
      }

    } catch (Exception e) {
      String msg = getName() + " failed with exception " + e.getMessage();
      LOG.warn(msg, e.getMessage());
      throw new RuntimeException(msg, e);
    }
  }
}
