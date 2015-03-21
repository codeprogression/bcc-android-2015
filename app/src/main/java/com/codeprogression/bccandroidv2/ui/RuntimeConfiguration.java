package com.codeprogression.bccandroidv2.ui;

import com.codeprogression.bccandroidv2.api.models.Configuration;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class RuntimeConfiguration {
    private Configuration configuration;

    @Inject
    public RuntimeConfiguration(){}

    public Configuration get() {
        return configuration;
    }

    void set(Configuration configuration) {
        this.configuration = configuration;
    }
}
