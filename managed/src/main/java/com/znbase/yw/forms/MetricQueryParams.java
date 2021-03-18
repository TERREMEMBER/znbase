// Copyright (c) ZNbase, Inc.

package com.ZNbase.yw.forms;

import play.data.validation.Constraints;

import java.util.List;


public class MetricQueryParams {
  @Constraints.Required()
  public List<String> metrics;

  @Constraints.Required()
  public Long start;

  public Long end;

  public String nodePrefix;

  public String nodeName;
}
