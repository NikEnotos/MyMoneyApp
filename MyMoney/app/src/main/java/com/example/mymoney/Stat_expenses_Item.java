package com.example.mymoney;

import java.io.Serializable;

public class Stat_expenses_Item implements Serializable {

    private String name_of_expenses_Item;
    private String percent_of_expenses_Item;

    public Stat_expenses_Item(String name, String percent)
    {
        name_of_expenses_Item = name;
        percent_of_expenses_Item = percent;
    }

    public void setNameOfExpansesItem(String name)
    {
        name_of_expenses_Item = name;
    }

    public String getNameOfExpensesItem()
    {
        return name_of_expenses_Item;
    }

    public void setPercentOfExpensesItem(String percent)
    {
        percent_of_expenses_Item = percent;
    }

    public String getPercentOfExpensesItem()
    {
        return percent_of_expenses_Item;
    }

}
