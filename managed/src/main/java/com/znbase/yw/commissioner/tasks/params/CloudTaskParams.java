// Copyright (c) ZNbase, Inc.

package com.ZNbase.yw.commissioner.tasks.params;

import com.ZNbase.yw.forms.AbstractTaskParams;

import java.util.UUID;

public class CloudTaskParams extends AbstractTaskParams {
  // The Provider for which to bootstrap the network.
  public UUID providerUUID;
}
