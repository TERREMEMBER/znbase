// Copyright (c) ZNbase, Inc.

package com.ZNbase.yw.forms;

public class AbstractTaskParams implements ITaskParams {

  public String errorString = null;

  @Override
  public void setErrorString(String errorString) {
    this.errorString = errorString;
  }

  @Override
  public String getErrorString() {
    return this.errorString;
  }
}
