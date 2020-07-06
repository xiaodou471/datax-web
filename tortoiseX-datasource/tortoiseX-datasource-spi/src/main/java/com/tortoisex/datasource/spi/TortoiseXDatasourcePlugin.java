package com.tortoisex.datasource.spi;

import com.tortoisex.datasource.spi.api.DatasourcePlugin;


/**
 * TortoiseX datasource plugin interface
 */
public interface TortoiseXDatasourcePlugin {

    /**
     * create an datasource plugin
     *
     * @return an datasource plugin
     */
    DatasourcePlugin createPlugin();
}
