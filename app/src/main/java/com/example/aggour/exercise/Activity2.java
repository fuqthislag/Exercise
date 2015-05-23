package com.example.aggour.exercise;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;


public class Activity2 extends ActionBarActivity {

    EditText editText, editText2, editText3;
    RadioButton radioButton, radioButton2;
    Button button;

    private String name = "", name2 = "", age = "";
    private boolean male = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity2);

        editText = (EditText) findViewById(R.id.editText);
        editText2 = (EditText) findViewById(R.id.editText2);
        editText3 = (EditText) findViewById(R.id.editText3);
        radioButton = (RadioButton) findViewById(R.id.radioButton);
        radioButton2 = (RadioButton) findViewById(R.id.radioButton2);
        button = (Button) findViewById(R.id.button);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                name = editText.getText().toString();
                name2 = editText2.getText().toString();
                age = editText3.getText().toString();
                if (radioButton.isChecked()) male = true;
                else male = false;

                Intent intent = new Intent(Activity2.this, Activity3.class);
                Bundle bundle = new Bundle();
                bundle.putString("name", name);
                bundle.putString("name2", name2);
                bundle.putString("age", age);
                bundle.putBoolean("male", male);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });
    }
}