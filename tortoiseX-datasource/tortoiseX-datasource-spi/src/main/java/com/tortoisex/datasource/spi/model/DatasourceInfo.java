package com.tortoisex.datasource.spi.model;

import java.util.HashMap;
import java.util.Map;

/**
 * AlertInfo
 */
public class DatasourceInfo {

    private Map<String, Object> alertProps;

    private Datasource alertData;

    public DatasourceInfo() {
        this.alertProps = new HashMap<>();
    }

    public Map<String, Object> getAlertProps() {
        return alertProps;
    }

    public DatasourceInfo setAlertProps(Map<String, Object> alertProps) {
        this.alertProps = alertProps;
        return this;
    }

    public DatasourceInfo addProp(String key, Object value) {
        this.alertProps.put(key, value);
        return this;
    }

    public Object getProp(String key) {
        return this.alertProps.get(key);
    }

    public Datasource getAlertData() {
        return alertData;
    }

    public DatasourceInfo setAlertData(Datasource alertData) {
        this.alertData = alertData;
        return this;
    }
}
