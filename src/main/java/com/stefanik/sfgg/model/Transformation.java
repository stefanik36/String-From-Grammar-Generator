package com.stefanik.sfgg.model;

import java.util.List;
import java.util.Map;

public class Transformation {
    private String key;
    private List<String> values;

    public Transformation(String key, List<String> values) {
        this.key = key;
        this.values = values;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public List<String> getValues() {
        return values;
    }

    public void setValues(List<String> values) {
        this.values = values;
    }
}
