// Copyright (c) ZNbase, Inc.

package com.ZNbase.yw.forms;

import play.data.validation.Constraints;


public class DatabaseUserFormData {

  public String ycqlAdminUsername;
  public String ycqlAdminPassword;

  public String ysqlAdminUsername;
  public String ysqlAdminPassword;
  public String dbName;

  @Constraints.Required()
  public String username;

  @Constraints.Required()
  public String password;
}
