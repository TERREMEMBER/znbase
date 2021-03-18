// Copyright (c) ZNbase, Inc.

package com.ZNbase.yw.commissioner.tasks;

import org.junit.Test;
import java.util.UUID;

import com.ZNbase.yw.commissioner.Commissioner;
import com.ZNbase.yw.commissioner.tasks.subtasks.ManipulateDnsRecordTask;
import com.ZNbase.yw.common.DnsManager;
import com.ZNbase.yw.common.ModelFactory;
import com.ZNbase.yw.common.ShellProcessHandler;
import com.ZNbase.yw.common.ShellResponse;
import com.ZNbase.yw.models.CustomerTask;
import com.ZNbase.yw.models.TaskInfo;
import com.ZNbase.yw.models.Universe;
import com.ZNbase.yw.models.helpers.TaskType;

import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import static org.junit.Assert.assertNull;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyString;
import static org.mockito.Matchers.anyList;
import static org.mockito.Matchers.anyMap;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class ManipulateDnsRecordTaskTest extends CommissionerBaseTest {
  @InjectMocks
  Commissioner commissioner;

  private TaskInfo submitTask() {
      Universe u = ModelFactory.createUniverse("test_universe", defaultCustomer.getCustomerId());
      ManipulateDnsRecordTask task = new ManipulateDnsRecordTask();
      ManipulateDnsRecordTask.Params params = new ManipulateDnsRecordTask.Params();
      params.universeUUID = u.universeUUID;
      params.type = DnsManager.DnsCommandType.Create;
      params.providerUUID = UUID.randomUUID();
      params.hostedZoneId = "";
      params.domainNamePrefix = "";
    try {
      UUID taskUUID = commissioner.submit(TaskType.ManipulateDnsRecordTask, params);
      CustomerTask.create(
          defaultCustomer, u.universeUUID, taskUUID, CustomerTask.TargetType.Universe,
          CustomerTask.TaskType.Create, "Create Universe");
      waitForTask(taskUUID);
    } catch (InterruptedException e) {
      assertNull(e.getMessage());
    }
    return null;
  }

  @Test
  public void testSimpleTaskSuccess() {
    ShellResponse response = new ShellResponse();
    response.code = 0;
    response.message = "";
    when(mockDnsManager.manipulateDnsRecord(any(), any(), anyString(), anyString(), anyString())).thenReturn(response);

    submitTask();

    verify(mockDnsManager, times(1)).manipulateDnsRecord(any(), any(), anyString(), anyString(), anyString());
  }
}
