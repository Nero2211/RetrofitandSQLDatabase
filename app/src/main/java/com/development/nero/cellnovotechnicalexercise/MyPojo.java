package com.development.nero.cellnovotechnicalexercise;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MyPojo

{
    private List<Records> records = null;
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    public List<Records> getRecords() {
        return records;
    }

    public void setRecords(List<Records> records) {
        this.records = records;
    }

    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }
}



