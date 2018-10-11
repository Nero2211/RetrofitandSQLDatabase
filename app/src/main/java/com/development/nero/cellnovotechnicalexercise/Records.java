package com.development.nero.cellnovotechnicalexercise;

public class Records
{
    private String id;
    private String price;
    private String category_name;
    private String description;
    private String name;
    private String category_id;
    private transient int position;

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public String getId ()
    {
        return id;
    }

    public void setId (String id)
    {
        this.id = id;
    }

    public String getPrice ()
    {
        return price;
    }

    public void setPrice (String price)
    {
        this.price = price;
    }

    public String getCategory_name ()
    {
        return category_name;
    }

    public void setCategory_name (String category_name)
    {
        this.category_name = category_name;
    }

    public String getDescription ()
    {
        return description;
    }

    public void setDescription (String description)
    {
        this.description = description;
    }

    public String getName ()
    {
        return name;
    }

    public void setName (String name)
    {
        this.name = name;
    }

    public String getCategory_id ()
    {
        return category_id;
    }

    public void setCategory_id (String category_id)
    {
        this.category_id = category_id;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [id = "+id+", price = "+price+", category_name = "+category_name+", description = "+description+", name = "+name+", category_id = "+category_id+"]";
    }
}