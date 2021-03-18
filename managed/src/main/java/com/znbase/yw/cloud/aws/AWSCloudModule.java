package com.ZNbase.yw.cloud.aws;

import com.google.inject.AbstractModule;
import com.google.inject.multibindings.MapBinder;
import com.ZNbase.yw.cloud.CloudAPI;

public class AWSCloudModule extends AbstractModule {
  @Override
  protected void configure() {
    MapBinder<String, CloudAPI> mapBinder =
      MapBinder.newMapBinder(binder(), String.class, CloudAPI.class);
    mapBinder.addBinding("aws").to(AWSCloudImpl.class);
  }
}
