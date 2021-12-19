package com.example.mymoney;

public class RecyclerItem {

    private String title_of_entry;
    private String description_of_entry;
    private float cost_of_entry;

    public RecyclerItem (String title, String description, float cost)
    {
        title_of_entry = title;
        description_of_entry = description;
        cost_of_entry = cost;
    }

    public void setTitle(String title)
    {
        title_of_entry = title;
    }

    public void setDescription(String description)
    {
        description_of_entry = description;
    }

    public void setCost(float cost)
    {
        cost_of_entry = cost;
    }

    public String getTitle()
    {
        return title_of_entry;
    }

    public String getDescription()
    {
        return description_of_entry;
    }

    public float getCost()
    {
        return cost_of_entry;
    }

}
