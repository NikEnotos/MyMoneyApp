package com.example.mymoney;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class CreateEntryActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.create_entry);
    }

    public void cancelCreate(View view){
       // Intent intent = new Intent(this, Expenses.class);
       // startActivity(intent);

        finish();
    }

    public void CreateEntry(View view){

        final EditText title = (EditText) findViewById(R.id.edit_title_of_entry);
        final EditText description = (EditText) findViewById(R.id.edit_description_of_entry);
        final EditText cost = (EditText) findViewById(R.id.edit_cost_of_entry);

        if(title.length() >= 1 && description.length() >= 1 && cost.length() >= 1) {
            Intent intent = new Intent();
            intent.putExtra("title", title.getText().toString());
            intent.putExtra("description", description.getText().toString());
            intent.putExtra("cost", Float.valueOf(cost.getText().toString()));

            setResult(420, intent);
        }


        finish();

    }

}