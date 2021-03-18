// Copyright (c) ZNbase, Inc.

package com.ZNbase.yw.forms;

import play.data.validation.Constraints;

/**
 * This class will be used by the API and UI Form Elements to validate constraints are met
 */
public class SetSecurityFormData {
  @Constraints.Required()
  public String level;
}
