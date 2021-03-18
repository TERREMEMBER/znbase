// Copyright (c) ZNbase, Inc.

package com.ZNbase.yw.forms;

import play.data.validation.Constraints;

import java.util.List;

/**
 * This class will be used by the API validate constraints for NodeInstance data.
 */
public class NodeInstanceFormData {

  @Constraints.Required
  public List<NodeInstanceData> nodes;

  public static class NodeInstanceData {
    @Constraints.Required()
    public String ip;

    @Constraints.Required()
    public String sshUser;

    @Constraints.Required()
    public String region;

    @Constraints.Required()
    public String zone;

    @Constraints.Required()
    public String instanceType;

    @Constraints.Required()
    public String instanceName;

    public String nodeName;
  }
}
