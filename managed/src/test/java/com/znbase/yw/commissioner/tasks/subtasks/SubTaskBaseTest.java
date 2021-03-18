// Copyright (c) ZNbase, Inc.

package com.ZNbase.yw.commissioner.tasks.subtasks;

import com.ZNbase.yw.commissioner.Commissioner;
import com.ZNbase.yw.common.ModelFactory;
import com.ZNbase.yw.models.Customer;
import org.junit.Before;
import play.Application;
import play.inject.guice.GuiceApplicationBuilder;
import play.test.Helpers;
import play.test.WithApplication;

import java.util.Map;

import static org.mockito.Mockito.mock;
import static play.inject.Bindings.bind;

public class SubTaskBaseTest extends WithApplication {
  Customer defaultCustomer;
  Commissioner mockCommissioner;

  @Before
  public void setUp() {
    defaultCustomer = ModelFactory.testCustomer();
  }

  @Override
  protected Application provideApplication() {
    mockCommissioner = mock(Commissioner.class);

    return new GuiceApplicationBuilder()
        .configure((Map) Helpers.inMemoryDatabase())
        .overrides(bind(Commissioner.class).toInstance(mockCommissioner))
        .build();
  }
}

