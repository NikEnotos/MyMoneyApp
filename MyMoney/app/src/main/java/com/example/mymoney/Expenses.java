package com.example.mymoney;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.text.Layout;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class Expenses extends Activity {

    private RecyclerView recyclerView;
    private EntryAdapter entryAdapter;
    private List<RecyclerItem> listItems;

    private ArrayList<String> expensesItems;
    private ArrayList<String> expensesPercents;

    private ArrayList<String> DiagramItems;
    private ArrayList<Integer> DiagramPercents;

    private ProgressBar diagram;
    private TextView TVbudget;
    private TextView TVcurrent_spent_percent;

    private float budget;
    private float current_expenses;

    private View NewEntryButton;
    private View currency;
    private View percentSpent;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.expencec);

        recyclerView = (RecyclerView) findViewById(R.id.container_for_entry);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        listItems = new ArrayList<>();
        expensesItems = new ArrayList<>();
        expensesPercents = new ArrayList<>();

        DiagramItems = new ArrayList<>();
        DiagramPercents = new ArrayList<>();

        entryAdapter = new EntryAdapter(listItems, this);
        recyclerView.setAdapter(entryAdapter);

        diagram = (ProgressBar) findViewById(R.id.circle_diagram);
        TVbudget = (TextView) findViewById(R.id.budget);
        TVcurrent_spent_percent = (TextView) findViewById(R.id.spent_percent);


        budget = 0;
        TVbudget.setText("TAP HERE TO SET BUDGET");
        diagram.setMax(Math.round(budget));

        NewEntryButton = findViewById(R.id.AddNewEntryLayout);
        NewEntryButton.setVisibility(View.GONE);
        currency = findViewById(R.id.currency);
        currency.setVisibility(View.INVISIBLE);
        percentSpent = findViewById(R.id.percentSpent);
        percentSpent.setVisibility(View.INVISIBLE);
        diagram.setVisibility(View.INVISIBLE);
        TVcurrent_spent_percent.setVisibility(View.INVISIBLE);

        current_expenses = 0;
        updateDiagram();
    }

    public void goToChangeBudget(View view)
    {
        Intent intent = new Intent(this, ChangeBudgetActivity.class);
        startActivityForResult(intent, 228);
    }

    public void goToStats(View view){
        Intent intent = new Intent(this, StatisticActivity.class);
        intent.putStringArrayListExtra("items", expensesItems);
        intent.putStringArrayListExtra("percents", expensesPercents);

        intent.putStringArrayListExtra("diagramNames", DiagramItems);
        intent.putIntegerArrayListExtra("diagramPercents", DiagramPercents);

        intent.putExtra("Budget", Math.round(budget));

        startActivity(intent);

    }

    public void goToAddNewEntry(View view){
        Intent intent = new Intent(this, CreateEntryActivity.class);
        startActivityForResult(intent, 420);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(resultCode == 420)
        {
            String title = data.getStringExtra("title");
            String description = data.getStringExtra("description");
            float cost = data.getFloatExtra("cost", 0);

                listItems.add(new RecyclerItem(title, description, cost));
                entryAdapter.setList(listItems);

                current_expenses += cost;
            updateDiagram();

            expensesItems.add(title);
            expensesPercents.add(Float.toString(cost/budget*100));

            boolean added = false;
            for (int i = 0; i < DiagramItems.size(); i++){

                if(DiagramItems.get(i).equals(title)){
                    DiagramPercents.set(i, Math.round(cost)+DiagramPercents.get(i));
                    added = true;
                }
            }

            if(!added){
                DiagramItems.add(title);
                DiagramPercents.add(Math.round(cost));
            }

        }
        else if(resultCode == 228)
        {
            float newBudget = data.getFloatExtra("newBudget", 100);

            setNewBudget(newBudget);
        }

    }

    public void setNewBudget(float newBudget)
    {
        budget = newBudget;
        TVbudget.setText(Float.toString(budget));
        diagram.setMax(Math.round(budget));
        NewEntryButton.setVisibility(View.VISIBLE);
        currency.setVisibility(View.VISIBLE);
        percentSpent.setVisibility(View.VISIBLE);
        diagram.setVisibility(View.VISIBLE);
        TVcurrent_spent_percent.setVisibility(View.VISIBLE);
        updateDiagram();
    }

    public void updateDiagram()
    {
        TVcurrent_spent_percent.setText(Float.toString(current_expenses/budget*100));
        diagram.setProgress(Math.round(current_expenses));
    }

}