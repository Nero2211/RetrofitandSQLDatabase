package com.development.nero.cellnovotechnicalexercise;

public class MyPojo
{
    private Records[] records;
    private transient int position;

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public Records[] getRecords ()
    {
        return records;
    }

    public void setRecords (Records[] records)
    {
        this.records = records;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [records = "+records+"]";
    }
}



