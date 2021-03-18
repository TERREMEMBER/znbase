/*
 * Copyright 2019 ZNbase, Inc. and Contributors
 *
 * Licensed under the Polyform Free Trial License 1.0.0 (the "License"); you
 * may not use this file except in compliance with the License. You
 * may obtain a copy of the License at
 *
 *     https://github.com/ZNbase/ZNbase-db/blob/master/licenses/POLYFORM-FREE-TRIAL-LICENSE-1.0.0.txt
 */

package com.ZNbase.yw.commissioner.tasks;

import com.fasterxml.jackson.databind.JsonNode;
import com.ZNbase.yw.commissioner.AbstractTaskBase;
import com.ZNbase.yw.common.ShellProcessHandler;
import com.ZNbase.yw.common.ShellResponse;
import com.ZNbase.yw.common.TableManager;
import com.ZNbase.yw.forms.AbstractTaskParams;
import com.ZNbase.yw.forms.BackupTableParams;
import com.ZNbase.yw.forms.ITaskParams;
import com.ZNbase.yw.models.Backup;
import com.ZNbase.yw.models.Universe;
import play.api.Play;
import play.libs.Json;

import java.util.UUID;


public class DeleteBackup extends AbstractTaskBase {

  public static class Params extends AbstractTaskParams {
    public UUID customerUUID;
    public UUID backupUUID;
  }

  public Params params() {
    return (Params)taskParams;
  }
  private TableManager tableManager;

  @Override
  public void initialize(ITaskParams params) {
    super.initialize(params);
    tableManager = Play.current().injector().instanceOf(TableManager.class);
  }

  @Override
  public void run() {
    Backup backup = null;
    try {
      backup = Backup.get(params().customerUUID, params().backupUUID);
      if (backup.state != Backup.BackupState.Completed) {
        LOG.error("Cannot delete backup in any other state other than completed.");
        throw new RuntimeException("Backup cannot be deleted");
      }
      backup.transitionState(Backup.BackupState.Deleted);
      BackupTableParams backupParams = Json.fromJson(backup.backupInfo, BackupTableParams.class);
      if (backupParams.backupList != null) {
        for (BackupTableParams childBackupParams : backupParams.backupList) {
          childBackupParams.actionType = BackupTableParams.ActionType.DELETE;
          ShellResponse response = tableManager.deleteBackup(childBackupParams);
          JsonNode jsonNode = Json.parse(response.message);
          if (response.code != 0 || jsonNode.has("error")) {
            // Revert state to completed since it couldn't get deleted.
            backup.transitionState(Backup.BackupState.Completed);
            LOG.error("Delete Backup failed for {}. Response code={}, hasError={}.",
                      childBackupParams.storageLocation, response.code, jsonNode.has("error"));
            throw new RuntimeException(response.message);
          } else {
            LOG.info("[" + getName() + "] STDOUT: " + response.message);
          }
        }
      } else {
        backupParams.actionType = BackupTableParams.ActionType.DELETE;
        ShellResponse response = tableManager.deleteBackup(backupParams);
        JsonNode jsonNode = Json.parse(response.message);
        if (response.code != 0 || jsonNode.has("error")) {
          // Revert state to completed since it couldn't get deleted.
          backup.transitionState(Backup.BackupState.Completed);
          LOG.error("Delete Backup failed for {}. Response code={}, hasError={}.",
                    backupParams.storageLocation, response.code, jsonNode.has("error"));
          throw new RuntimeException(response.message);
        } else {
          LOG.info("[" + getName() + "] STDOUT: " + response.message);
        }
      }
    } catch (Exception e) {
      LOG.error("Errored out with: " + e);
      if (backup != null) {
        // Revert state to completed since it couldn't get deleted.
        backup.transitionState(Backup.BackupState.Completed);
      }
      throw new RuntimeException(e);
    }
  }
}
