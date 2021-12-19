package com.example.mymoney;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class ChangeBudgetActivity extends Activity {

    Button goBack;
    Button applyChange;
    EditText ET_NewBudget;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.set_budget);

        goBack = (Button) findViewById(R.id.go_back_from_change_budget_button);
        applyChange = (Button) findViewById(R.id.apply_change_budget_button);
        ET_NewBudget = (EditText) findViewById(R.id.set_new_budget_field);

    }

    public void goBackFromSetBudget(View view)
    {
        finish();
    }

    public void applyNewBudget(View view)
    {
        final EditText ET_NewBudget = (EditText) findViewById(R.id.set_new_budget_field);

        if(ET_NewBudget.length() >= 1 ) {
            Intent intent = new Intent();
            intent.putExtra("newBudget", Float.valueOf(ET_NewBudget.getText().toString()));

            setResult(228, intent);
        }


        finish();
    }

}
