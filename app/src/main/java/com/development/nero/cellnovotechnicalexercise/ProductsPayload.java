package com.development.nero.cellnovotechnicalexercise;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ProductsPayload

{
    private List<Product> records = null;
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    public List<Product> getProducts() {
        return records;
    }

    public void setProducts(List<Product> records) {
        this.records = records;
    }

    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }
}



