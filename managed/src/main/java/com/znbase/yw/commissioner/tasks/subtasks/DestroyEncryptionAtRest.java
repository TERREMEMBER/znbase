/*
 * Copyright 2019 ZNbase, Inc. and Contributors
 *
 * Licensed under the Polyform Free Trial License 1.0.0 (the "License"); you
 * may not use this file except in compliance with the License. You
 * may obtain a copy of the License at
 *
 *     https://github.com/ZNbase/ZNbase-db/blob/master/licenses/POLYFORM-FREE-TRIAL-LICENSE-1.0.0.txt
 */
package com.ZNbase.yw.commissioner.tasks.subtasks;

import com.ZNbase.yw.commissioner.AbstractTaskBase;
import com.ZNbase.yw.common.kms.EncryptionAtRestManager;
import com.ZNbase.yw.common.kms.util.EncryptionAtRestUtil;
import com.ZNbase.yw.forms.ITaskParams;
import com.ZNbase.yw.forms.EncryptionAtRestKeyParams;
import com.ZNbase.yw.forms.UniverseTaskParams;
import com.ZNbase.yw.models.Customer;
import com.ZNbase.yw.models.Universe;
import java.util.UUID;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import play.api.Play;

public class DestroyEncryptionAtRest extends AbstractTaskBase {

    public static final Logger LOG = LoggerFactory.getLogger(DestroyEncryptionAtRest.class);

    public EncryptionAtRestManager keyManager = null;

    // Timeout for failing to respond to pings.
    private static final long TIMEOUT_SERVER_WAIT_MS = 120000;

    public static class Params extends EncryptionAtRestKeyParams {
        public UUID customerUUID = null;
    }

    @Override
    protected Params taskParams() {
        return (Params)taskParams;
    }

    @Override
    public void initialize(ITaskParams params) {
        super.initialize(params);
        keyManager = Play.current().injector().instanceOf(EncryptionAtRestManager.class);
    }

    @Override
    public void run() {
        try {
            Universe u = Universe.get(taskParams().universeUUID);
            Customer c = Customer.get(u.customerId);
            if (EncryptionAtRestUtil.getNumKeyRotations(taskParams().universeUUID) > 0) {
                keyManager.cleanupEncryptionAtRest(c.uuid, taskParams().universeUUID);
            }
        } catch (Exception e) {
            final String errMsg = String.format(
                    "Error caught cleaning up encryption at rest for universe %s",
                    taskParams().universeUUID
            );
            LOG.error(errMsg, e);
        }
    }
}
