/*
 * Copyright 2021 ZNbase, Inc. and Contributors
 *
 * Licensed under the Polyform Free Trial License 1.0.0 (the "License"); you
 * may not use this file except in compliance with the License. You
 * may obtain a copy of the License at
 *
 * http://github.com/ZNbase/ZNbase-db/blob/master/licenses/POLYFORM-FREE-TRIAL-LICENSE-1.0.0.txt
 */

package com.ZNbase.yw.common.config;

import com.google.inject.Inject;
import com.typesafe.config.Config;
import com.ZNbase.yw.models.Customer;
import com.ZNbase.yw.models.Provider;
import com.ZNbase.yw.models.Universe;

/**
 * Always returns to application config. This should be used for older branches where there
 * is no real runtime config impl and database schemas.
 */
public class DummyRuntimeConfigFactoryImpl implements RuntimeConfigFactory {
  private final Config appConfig;

  @Inject
  public DummyRuntimeConfigFactoryImpl(Config appConfig) {
    this.appConfig = appConfig;
  }

  @Override
  public Config forCustomer(Customer customer) {
    return appConfig;
  }

  @Override
  public Config forUniverse(Universe universe) {
    return appConfig;
  }

  @Override
  public Config forProvider(Provider provider) {
    return appConfig;
  }

  @Override
  public Config globalRuntimeConf() {
    return appConfig;
  }

  @Override
  public Config staticApplicationConf() {
    return appConfig;
  }
}
