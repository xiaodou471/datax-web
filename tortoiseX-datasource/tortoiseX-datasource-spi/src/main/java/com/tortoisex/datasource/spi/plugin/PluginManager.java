package com.tortoisex.datasource.spi.plugin;

import com.tortoisex.datasource.spi.api.DatasourcePlugin;

import java.util.Map;

/**
 * PluginManager
 */
public interface PluginManager {

    DatasourcePlugin findOne(String name);

    Map<String, DatasourcePlugin> findAll();

    void addPlugin(DatasourcePlugin plugin);
}
