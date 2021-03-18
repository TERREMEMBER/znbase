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

import com.typesafe.config.Config;
import com.ZNbase.yw.models.Customer;
import com.ZNbase.yw.models.Provider;
import com.ZNbase.yw.models.Universe;

public interface RuntimeConfigFactory {
  Config forCustomer(Customer customer);

  Config forUniverse(Universe universe);

  Config forProvider(Provider provider);

  Config globalRuntimeConf();

  Config staticApplicationConf();
}
