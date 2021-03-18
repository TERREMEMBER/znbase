// Copyright (c) ZNbase, Inc.

package com.ZNbase.yw.commissioner;

import java.util.List;
import java.util.UUID;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.assertEquals;
import com.ZNbase.yw.common.FakeDBApplication;
import com.ZNbase.yw.common.ModelFactory;
import com.ZNbase.yw.models.Alert;
import com.ZNbase.yw.models.Customer;
import com.ZNbase.yw.models.CustomerTask;

public class AbstractTaskBaseTest extends FakeDBApplication {

  private Customer defaultCustomer;

  private AbstractTaskBaseFake task;

  private static final UUID CUSTOMER_TASK_TARGET_UUID = UUID.randomUUID();

  @Before
  public void setUp() {
    defaultCustomer = ModelFactory.testCustomer();

    task = new AbstractTaskBaseFake();
    task.setUserTaskUUID(UUID.randomUUID());

    CustomerTask.create(defaultCustomer, CUSTOMER_TASK_TARGET_UUID, task.userTaskUUID,
        CustomerTask.TargetType.Universe, CustomerTask.TaskType.Update, "Test Universe");
  }

  @Test
  public void testSendNotification() {
    assertEquals(0, Alert.list(defaultCustomer.uuid).size());

    task.sendNotification();

    List<Alert> alerts = Alert.list(defaultCustomer.uuid);
    assertEquals(1, alerts.size());

    Alert alert = alerts.get(0);
    assertEquals(Alert.TargetType.UniverseType, alert.targetType);
    assertEquals(defaultCustomer.uuid, alert.customerUUID);
    assertEquals(CUSTOMER_TASK_TARGET_UUID, alert.targetUUID);
    assertEquals(AbstractTaskBase.ALERT_ERROR_CODE, alert.errCode);
    assertEquals("Error", alert.type);
  }

  private class AbstractTaskBaseFake extends AbstractTaskBase {
    @Override
    public void run() {
      // Nothing to do.
    }
  }
}
