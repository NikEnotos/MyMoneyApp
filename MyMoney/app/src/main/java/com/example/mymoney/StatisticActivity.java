package com.example.mymoney;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

public class StatisticActivity extends Activity {

    private RecyclerView ExpensesItemsRecyclerView;
    private Stats_Expenses_Adapter ExpensesAdapter;
    private List<Stat_expenses_Item> ExpensesListItems;
    private ArrayList<String> expensesItems;
    private ArrayList<String> expensesPercents;

    private RecyclerView DiagramItemsRecycleView;
    private Stats_Diagrams_Adapter DiagramsAdapter;
    private List<Stat_diagram_Item> DiagramsListItems;
    private ArrayList<String> DiagramNameItems;
    private ArrayList<Integer> DiagramPercents;
    private Integer budget;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.statistic);

        ExpensesListItems = new ArrayList<>();
        expensesItems = new ArrayList<String>();
        expensesPercents = new ArrayList<String>();

        ExpensesItemsRecyclerView = (RecyclerView) findViewById(R.id.container_for_expenses_items);
        ExpensesItemsRecyclerView.setHasFixedSize(true);
        ExpensesItemsRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        Intent intent = getIntent();
        expensesItems = intent.getExtras().getStringArrayList("items");
        expensesPercents = intent.getExtras().getStringArrayList("percents");

        for(int i = 0; i < expensesItems.size(); i++)
        {
            ExpensesListItems.add(new Stat_expenses_Item(expensesItems.get(i), expensesPercents.get(i)));
        }

        ExpensesAdapter = new Stats_Expenses_Adapter(ExpensesListItems, this);
        ExpensesItemsRecyclerView.setAdapter(ExpensesAdapter);
        ExpensesAdapter.setList(ExpensesListItems);

//////////////////////////////////////////////////////////////////////////////////////////////////////////////////

        DiagramsListItems = new ArrayList<>();
        DiagramNameItems = new ArrayList<String>();
        DiagramPercents = new ArrayList<Integer>();

        DiagramItemsRecycleView = (RecyclerView) findViewById(R.id.container_for_diagram_items);
        DiagramItemsRecycleView.setHasFixedSize(true);
        DiagramItemsRecycleView.setLayoutManager(new LinearLayoutManager(this));

        budget = intent.getIntExtra("Budget", 0);
        DiagramNameItems = intent.getExtras().getStringArrayList("diagramNames");;
        DiagramPercents = intent.getExtras().getIntegerArrayList("diagramPercents");

        for(int i = 0; i < DiagramNameItems.size(); i++)
        {
            DiagramsListItems.add(new Stat_diagram_Item(DiagramNameItems.get(i), DiagramPercents.get(i), budget));
        }

        DiagramsAdapter = new Stats_Diagrams_Adapter(DiagramsListItems, this);
        DiagramItemsRecycleView.setAdapter(DiagramsAdapter);
        DiagramsAdapter.setList(DiagramsListItems);

    }

    public void goBackToExpenses(View view){
        finish();
    }

}