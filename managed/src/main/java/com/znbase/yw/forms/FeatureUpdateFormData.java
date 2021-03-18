// Copyright (c) ZNbase, Inc.

package com.ZNbase.yw.forms;

import com.fasterxml.jackson.databind.JsonNode;
import play.data.validation.Constraints;

/**
 * This class will be used by the API and UI Form Elements to validate constraints for
 * CloudProvider
 */
public class FeatureUpdateFormData {
    @Constraints.Required()
    public JsonNode features;
}
