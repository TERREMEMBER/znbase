// Copyright (c) ZNbase, Inc.
package com.ZNbase.yw.common;

import com.ZNbase.yw.common.CustomerTaskManager;
import com.ZNbase.yw.common.ModelFactory;
import com.ZNbase.yw.models.Customer;
import com.ZNbase.yw.models.CustomerTask;
import static com.ZNbase.yw.models.CustomerTask.TaskType.Create;
import com.ZNbase.yw.models.TaskInfo;
import com.ZNbase.yw.models.helpers.TaskType;
import com.ZNbase.yw.models.Universe;
import java.util.List;
import java.util.UUID;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.assertTrue;
import org.junit.runner.RunWith;
import static org.mockito.Mockito.spy;
import org.mockito.runners.MockitoJUnitRunner;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import play.api.Play;
import play.libs.Json;

@RunWith(MockitoJUnitRunner.class)
public class CustomerTaskManagerTest extends FakeDBApplication {
  Customer customer;
  Universe universe;
  CustomerTaskManager taskManager;

  private CustomerTask createTask(CustomerTask.TargetType targetType, UUID targetUUID,
                                  CustomerTask.TaskType taskType) {
    TaskInfo taskInfo = new TaskInfo(TaskType.CreateUniverse);
    UUID taskUUID = UUID.randomUUID();
    taskInfo.setTaskUUID(taskUUID);
    taskInfo.setTaskDetails(Json.newObject());
    taskInfo.setOwner("");
    taskInfo.save();
    return CustomerTask.create(customer, targetUUID, taskInfo.getTaskUUID(),
      targetType, taskType, "Foo");
  }

  @Before
  public void setup() {
    customer = ModelFactory.testCustomer();
  }

  @Test
  public void testFailPendingTasksNoneExist() throws Exception {
    universe = ModelFactory.createUniverse(customer.getCustomerId());
    taskManager = spy(Play.current().injector().instanceOf(CustomerTaskManager.class));
    for (CustomerTask.TargetType targetType : CustomerTask.TargetType.values()) {
      UUID targetUUID = UUID.randomUUID();
      if (targetType.equals(CustomerTask.TargetType.Universe)) targetUUID = universe.universeUUID;
      CustomerTask th = createTask(targetType, targetUUID, Create);
      th.markAsCompleted();
    }

    taskManager.failAllPendingTasks();
    // failPendingTask should never be called since all tasks are already completed
    verify(taskManager, times(0)).failPendingTask(any(), any());
  }

  @Test
  public void testFailPendingTasks() throws Exception {
    universe = ModelFactory.createUniverse(customer.getCustomerId());
    taskManager = spy(Play.current().injector().instanceOf(CustomerTaskManager.class));
    for (CustomerTask.TargetType targetType : CustomerTask.TargetType.values()) {
      UUID targetUUID = UUID.randomUUID();
      if (targetType.equals(CustomerTask.TargetType.Universe)) targetUUID = universe.universeUUID;
      CustomerTask th = createTask(targetType, targetUUID, Create);
    }

    taskManager.failAllPendingTasks();
    verify(taskManager, times(CustomerTask.TargetType.values().length))
      .failPendingTask(any(), any());

    List<CustomerTask> customerTasks = CustomerTask.find.query().where()
      .eq("customer_uuid", customer.uuid)
      .findList();

    // Verify tasks have been marked as failure properly
    for (CustomerTask task : customerTasks) {
      assertTrue(task.getCompletionTime() != null);
      TaskInfo taskInfo = TaskInfo.get(task.getTaskUUID());
      assertTrue(taskInfo.getTaskState().equals(TaskInfo.State.Failure));
    }
  }
}
