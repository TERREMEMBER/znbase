// Copyright (c) ZNbase, Inc.

package com.ZNbase.yw.controllers;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.google.common.collect.ImmutableList;
import com.ZNbase.yw.common.FakeApiHelper;
import com.ZNbase.yw.common.FakeDBApplication;
import com.ZNbase.yw.common.ModelFactory;
import com.ZNbase.yw.forms.BackupTableParams;
import com.ZNbase.yw.models.Backup;
import com.ZNbase.yw.models.Schedule;
import com.ZNbase.yw.models.Customer;
import com.ZNbase.yw.models.CustomerConfig;
import com.ZNbase.yw.models.CustomerTask;
import com.ZNbase.yw.models.Universe;
import com.ZNbase.yw.models.Users;
import com.ZNbase.yw.models.helpers.TaskType;
import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import play.libs.Json;
import play.mvc.Result;

import java.util.UUID;

import static com.ZNbase.yw.common.AssertHelper.assertErrorNodeValue;
import static com.ZNbase.yw.common.AssertHelper.assertOk;
import static com.ZNbase.yw.common.AssertHelper.assertValue;
import static com.ZNbase.yw.common.AssertHelper.assertValues;
import static com.ZNbase.yw.common.AssertHelper.assertBadRequest;
import static com.ZNbase.yw.common.AssertHelper.assertAuditEntry;
import static com.ZNbase.yw.models.CustomerTask.TaskType.Restore;
import static org.junit.Assert.*;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static play.mvc.Http.Status.BAD_REQUEST;
import static play.mvc.Http.Status.FORBIDDEN;
import static play.test.Helpers.contentAsString;

public class ScheduleControllerTest extends FakeDBApplication {

  private Universe defaultUniverse;
  private Customer defaultCustomer;
  private Users defaultUser;
  private Schedule defaultSchedule;

  @Before
  public void setUp() {
    defaultCustomer = ModelFactory.testCustomer();
    defaultUser = ModelFactory.testUser(defaultCustomer);
    defaultUniverse = ModelFactory.createUniverse(defaultCustomer.getCustomerId());

    BackupTableParams backupTableParams = new BackupTableParams();
    backupTableParams.universeUUID = defaultUniverse.universeUUID;
    CustomerConfig customerConfig = ModelFactory.createS3StorageConfig(defaultCustomer);
    backupTableParams.storageConfigUUID = customerConfig.configUUID;
    defaultSchedule = Schedule.create(defaultCustomer.uuid, backupTableParams, TaskType.BackupUniverse, 1000);
  }

  private Result listSchedules(UUID customerUUID) {
    String authToken = defaultUser.createAuthToken();
    String method = "GET";
    String url = "/api/customers/" + customerUUID + "/schedules";

    return FakeApiHelper.doRequestWithAuthToken(method, url, authToken);
  }

  private Result deleteSchedule(UUID scheduleUUID, UUID customerUUID) {
    String authToken = defaultUser.createAuthToken();
    String method = "DELETE";
    String url = "/api/customers/" + customerUUID + "/schedules/" + scheduleUUID;

    return FakeApiHelper.doRequestWithAuthToken(method, url, authToken);
  }

  @Test
  public void testListWithValidCustomer() {
    Result r = listSchedules(defaultCustomer.uuid);
    assertOk(r);
    JsonNode resultJson = Json.parse(contentAsString(r));
    assertEquals(1, resultJson.size());
    assertEquals(resultJson.get(0).get("scheduleUUID").asText(),
                 defaultSchedule.scheduleUUID.toString());
    assertAuditEntry(0, defaultCustomer.uuid);
  }

  @Test
  public void testListWithInvalidCustomer() {
    UUID invalidCustomerUUID = UUID.randomUUID();
    Result r = listSchedules(invalidCustomerUUID);
    assertEquals(FORBIDDEN, r.status());
    String resultString = contentAsString(r);
    assertEquals(resultString, "Unable To Authenticate User");
    assertAuditEntry(0, defaultCustomer.uuid);
  }

  @Test
  public void testDeleteValid() {
    JsonNode resultJson = Json.parse(contentAsString(listSchedules(defaultCustomer.uuid)));
    assertEquals(1, resultJson.size());
    Result r = deleteSchedule(defaultSchedule.scheduleUUID, defaultCustomer.uuid);
    assertOk(r);
    resultJson = Json.parse(contentAsString(listSchedules(defaultCustomer.uuid)));
    assertEquals(0, resultJson.size());
    assertAuditEntry(1, defaultCustomer.uuid);
  }

  @Test
  public void testDeleteInvalidCustomerUUID() {
    UUID invalidCustomerUUID = UUID.randomUUID();
    JsonNode resultJson = Json.parse(contentAsString(listSchedules(defaultCustomer.uuid)));
    assertEquals(1, resultJson.size());
    Result r = deleteSchedule(defaultSchedule.scheduleUUID, invalidCustomerUUID);
    assertEquals(FORBIDDEN, r.status());
    String resultString = contentAsString(r);
    assertEquals(resultString, "Unable To Authenticate User");
    resultJson = Json.parse(contentAsString(listSchedules(defaultCustomer.uuid)));
    assertEquals(1, resultJson.size());
    assertAuditEntry(0, defaultCustomer.uuid);
  }

  @Test
  public void testDeleteInvalidScheduleUUID() {
    UUID invalidScheduleUUID = UUID.randomUUID();
    JsonNode resultJson = Json.parse(contentAsString(listSchedules(defaultCustomer.uuid)));
    assertEquals(1, resultJson.size());
    Result r = deleteSchedule(invalidScheduleUUID, defaultCustomer.uuid);
    assertBadRequest(r, "Invalid Schedule UUID: " + invalidScheduleUUID);
    resultJson = Json.parse(contentAsString(listSchedules(defaultCustomer.uuid)));
    assertEquals(1, resultJson.size());
    assertAuditEntry(0, defaultCustomer.uuid);
  }
}
