package com.tortoisex.datasource.spi.api;

import com.tortoisex.datasource.spi.model.DatasourceInfo;
import com.tortoisex.datasource.spi.model.PluginName;

import java.util.Map;

/**
 * Plugin
 */
public interface DatasourcePlugin {

    /**
     * Get datasource plugin id
     *
     * @return datasource plugin id, which should be unique
     */
    String getId();

    /**
     * Get datasource plugin name, which will show in front end portal
     *
     * @return plugin name
     */
    PluginName getName();

    Map<String, Object> process(DatasourceInfo info);

}
