// Copyright (c) ZNbase, Inc.

package com.ZNbase.yw.forms;

import com.ZNbase.yw.models.Universe;
import com.ZNbase.yw.models.helpers.TableDetails;
import org.yb.Common;
import org.yb.client.GetTableSchemaResponse;

import java.util.UUID;

public class TableDefinitionTaskParams extends TableTaskParams {
  public Common.TableType tableType = null;
  public TableDetails tableDetails = null;

  public static TableDefinitionTaskParams createFromResponse(Universe universe,
                                                             UUID tableUUID,
                                                             GetTableSchemaResponse response) {

    // Verify tableUUID is correct
    String noDashTableUUID = tableUUID.toString().replace("-", "");
    if (!noDashTableUUID.equals(response.getTableId())) {
      throw new IllegalArgumentException("UUID of table in schema (" + noDashTableUUID +
          ") did not match UUID of table in request (" + response.getTableId() + ").");
    }

    TableDefinitionTaskParams params = new TableDefinitionTaskParams();

    params.universeUUID = universe.universeUUID;
    params.expectedUniverseVersion = universe.getUniverseDetails().expectedUniverseVersion;
    params.tableUUID = tableUUID;
    params.tableType = response.getTableType();
    params.tableDetails = TableDetails.createWithSchema(response.getSchema());
    params.tableDetails.tableName = response.getTableName();
    params.tableDetails.keyspace = response.getNamespace();

    return params;
  }

}
