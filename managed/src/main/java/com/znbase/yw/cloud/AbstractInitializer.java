/*
 * Copyright 2019 ZNbase, Inc. and Contributors
 *
 * Licensed under the Polyform Free Trial License 1.0.0 (the "License"); you
 * may not use this file except in compliance with the License. You
 * may obtain a copy of the License at
 *
 *     https://github.com/ZNbase/ZNbase-db/blob/master/licenses/POLYFORM-FREE-TRIAL-LICENSE-1.0.0.txt
 */

package com.ZNbase.yw.cloud;

import com.google.inject.Inject;
import com.ZNbase.yw.common.ApiHelper;
import com.ZNbase.yw.common.CloudQueryHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import play.mvc.Result;

import java.util.UUID;

public abstract class AbstractInitializer {
  public static final Logger LOG = LoggerFactory.getLogger(AbstractInitializer.class);

  @Inject
  ApiHelper apiHelper;

  @Inject
  CloudQueryHelper cloudQueryHelper;

  public abstract Result initialize(UUID customerUUID, UUID providerUUID);
}
