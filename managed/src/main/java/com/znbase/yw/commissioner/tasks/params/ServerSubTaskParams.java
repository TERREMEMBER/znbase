package com.ZNbase.yw.commissioner.tasks.params;

import com.ZNbase.yw.commissioner.tasks.UniverseDefinitionTaskBase.ServerType;

import com.ZNbase.yw.forms.UniverseTaskParams;

public class ServerSubTaskParams extends UniverseTaskParams {
  // The name of the node which contains the server process.
    public String nodeName;

    // Server type running on the above node for which we will wait.
    public ServerType serverType;
}
