/*
 * Copyright 2019 ZNbase, Inc. and Contributors
 *
 * Licensed under the Polyform Free Trial License 1.0.0 (the "License"); you
 * may not use this file except in compliance with the License. You
 * may obtain a copy of the License at
 *
 *     https://github.com/ZNbase/ZNbase-db/blob/master/licenses/POLYFORM-FREE-TRIAL-LICENSE-1.0.0.txt
 */

package com.ZNbase.yw.common.kms.util;

import io.ebean.annotation.EnumValue;
import com.ZNbase.yw.common.kms.algorithms.SupportedAlgorithmInterface;
import com.ZNbase.yw.common.kms.services.*;

/**
 * A list of third party encryption key providers that YB currently supports and the
 * corresponding service impl and any already instantiated classes
 * (such that each impl is a singleton)
 */
public enum KeyProvider {
    @EnumValue("AWS")
    AWS(AwsEARService.class),

    @EnumValue("SMARTKEY")
    SMARTKEY(SmartKeyEARService.class);

    private final Class<?> providerService;

    private EncryptionAtRestService<?> instance;

    public <T extends EncryptionAtRestService<? extends SupportedAlgorithmInterface>> Class<T> getProviderService() {
        return (Class<T>) this.providerService;
    }

    public <T extends EncryptionAtRestService<? extends SupportedAlgorithmInterface>> T getServiceInstance() {
      return (T) this.instance;
    }

    public <T extends EncryptionAtRestService<? extends SupportedAlgorithmInterface>> void setServiceInstance(T instance) {
        this.instance = instance;
    }

    <T extends EncryptionAtRestService<? extends SupportedAlgorithmInterface>> KeyProvider(Class<T> providerService) {
        this.providerService = providerService;
        this.instance = null;
    }
}
