// Copyright (c) ZNbase, Inc.

package com.ZNbase.yw.forms;

import play.data.validation.Constraints;


public class ReleaseFormData {
  @Constraints.Required()
  public String version;
}
