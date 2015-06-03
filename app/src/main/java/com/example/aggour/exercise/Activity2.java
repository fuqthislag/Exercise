package com.example.aggour.exercise;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;


public class Activity2 extends Activity {

    //Declare the layout elements
    EditText editText, editText2, editText3;
    RadioButton radioButton, radioButton2;
    Button button;

    //Declare all the variables and put initial values
    private String name = "", name2 = "", age = "";
    private boolean male = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity2);

        //Initialise the layout elements
        editText = (EditText) findViewById(R.id.editText);
        editText2 = (EditText) findViewById(R.id.editText2);
        editText3 = (EditText) findViewById(R.id.editText3);
        radioButton = (RadioButton) findViewById(R.id.radioButton);
        radioButton2 = (RadioButton) findViewById(R.id.radioButton2);
        button = (Button) findViewById(R.id.button);

        //Set setOnClickListener on the only (huge) button
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Getting values from all the fields to variables
                name = editText.getText().toString();
                name2 = editText2.getText().toString();
                age = editText3.getText().toString();
                if (radioButton.isChecked()) male = true;
                else male = false;

                //putting all the variables into a bundle
                Bundle bundle = new Bundle();
                bundle.putString("name", name);
                bundle.putString("name2", name2);
                bundle.putString("age", age);
                bundle.putBoolean("male", male);
                //Set a new intent to go to the next activity
                Intent intent = new Intent(Activity2.this, Activity3.class);
                //Declare the bundle of variables as extras and send it to the next activity
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });
    }
}