package com.example.mymoney;

import java.io.Serializable;

public class Stat_diagram_Item implements Serializable {

    private String name_of_diagram_Item;
    private Integer percent_of_diagram_Item;
    private Integer budget;


    public Stat_diagram_Item(String name, Integer percent, Integer budg)
    {
        name_of_diagram_Item = name;
        percent_of_diagram_Item = percent;
        budget = budg;
    }

    public void setNameOfDiagramItem(String name)
    {
        name_of_diagram_Item = name;
    }

    public String getNameOfDiagramItem()
    {
        return name_of_diagram_Item;
    }

    public void setPercentOfDiagramItem(Integer percent)
    {
        percent_of_diagram_Item = percent;
    }

    public Integer getPercentOfDiagramItem()
    {
        return percent_of_diagram_Item;
    }

    public Integer getItemBudget() {return budget;}

}
