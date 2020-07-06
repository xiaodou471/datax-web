package com.tortoisex.datasource.spi.model;

/**
 * PluginName
 */
public class PluginName {

    private String chinese;

    private String english;

    public String getChinese() {
        return chinese;
    }

    public PluginName setChinese(String chinese) {
        this.chinese = chinese;
        return this;
    }

    public String getEnglish() {
        return english;
    }

    public PluginName setEnglish(String english) {
        this.english = english;
        return this;
    }
}
