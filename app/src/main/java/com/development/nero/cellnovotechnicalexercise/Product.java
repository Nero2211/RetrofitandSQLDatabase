package com.development.nero.cellnovotechnicalexercise;

import java.util.HashMap;
import java.util.Map;

public class Product
{
    private String id;
    private String name;
    private String description;
    private String price;
    private String category_id;
    private String category_name;
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    public Product(){

    }

    public Product(String id, String name, String description, String price, String category_id, String category_name) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
        this.category_id = category_id;
        this.category_name = category_name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getCategoryId() {
        return category_id;
    }

    public void setCategoryId(String categoryId) {
        this.category_id = categoryId;
    }

    public String getCategoryName() {
        return category_name;
    }

    public void setCategoryName(String categoryName) {
        this.category_name = categoryName;
    }

    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }
}