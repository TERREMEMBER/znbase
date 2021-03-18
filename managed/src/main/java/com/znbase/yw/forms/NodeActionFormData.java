package com.ZNbase.yw.forms;

import com.ZNbase.yw.common.NodeActionType;
import play.data.validation.Constraints;

public class NodeActionFormData {


    @Constraints.Required()
    public NodeActionType nodeAction;
}
