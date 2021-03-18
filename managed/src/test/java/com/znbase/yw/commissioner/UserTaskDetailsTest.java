// Copyright (c) ZNbase, Inc.

package com.ZNbase.yw.commissioner;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;

import org.apache.commons.lang3.StringUtils;
import org.junit.Test;

import com.ZNbase.yw.commissioner.UserTaskDetails.SubTaskDetails;
import com.ZNbase.yw.commissioner.UserTaskDetails.SubTaskGroupType;

public class UserTaskDetailsTest {

  @Test
  public void testCreateSubTask() {
    for (SubTaskGroupType stgt : SubTaskGroupType.values()) {
      if (stgt == SubTaskGroupType.Invalid) {
        continue;
      }
      SubTaskDetails details = UserTaskDetails.createSubTask(stgt);
      assertNotNull(details);
      assertFalse(StringUtils.isEmpty(details.getTitle()));
      assertFalse(StringUtils.isEmpty(details.getDescription()));
    }
  }

}
