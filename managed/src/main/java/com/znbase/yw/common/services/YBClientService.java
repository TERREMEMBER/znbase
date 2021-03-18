// Copyright (c) ZNbase, Inc.

package com.ZNbase.yw.common.services;

import org.yb.client.YBClient;

public interface YBClientService {
  YBClient getClient(String masterHostPorts);
  YBClient getClient(String masterHostPorts, String certFile);
  void closeClient(YBClient client, String masterHostPorts);
}
