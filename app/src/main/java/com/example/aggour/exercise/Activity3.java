package com.example.aggour.exercise;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;


public class Activity3 extends Activity {

    //Declare the layout elements
    TextView textView4, textView5, textView6, textView7,
            textView8, textView9, textView10, textView11, textView12;
    Button button2, button3;

    //Declare all the variables and put initial values
    Boolean check = true, check2 = true, check3 = true;
    int count = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity3);

        //Initialise the layout elements
        textView4 = (TextView) findViewById(R.id.textView4);
        textView5 = (TextView) findViewById(R.id.textView5);
        textView6 = (TextView) findViewById(R.id.textView6);
        textView7 = (TextView) findViewById(R.id.textView7);

        textView8 = (TextView) findViewById(R.id.textView8);
        textView9 = (TextView) findViewById(R.id.textView9);
        textView10 = (TextView) findViewById(R.id.textView10);
        textView11 = (TextView) findViewById(R.id.textView11);
        textView12 = (TextView) findViewById(R.id.textView12);
        button2 = (Button) findViewById(R.id.button2);
        button3 = (Button) findViewById(R.id.button3);

        //Make a bundle to get the extras from the previous activity
        Bundle bundle = getIntent().getExtras();

        //Set the bundle contents to the corresponding fields
        textView4.setText(bundle.getString("name"));
        textView5.setText(bundle.getString("name2"));
        textView6.setText(bundle.getString("age"));
        if (bundle.getBoolean("male")) textView7.setText("M");
        else textView7.setText("F");

        //I call the random to function on this field to put a random number, from 1-50, in it
        textView8.setText(new java.util.Random().nextInt(50) + 1 + "");

        //When "start" is clicked...
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (check2) {
                    //Check 2 is to prevent the thread from running multiple times
                    check2 = false;
                    //Check 3 will make the counter stop at the EXACT value of the "stop" press
                    check3 = true;
                    //Check will finish the thread
                    check = true;
                    //I initialize the thread and execute from the spot
                    new AsyncTask<Void, Integer, Void>() {
                                @Override
                                protected void onProgressUpdate(Integer... values) {
                                    //Used this method to update the text with the current count number
                                    textView9.setText("" + values[0]);
                                }

                                @Override
                                protected Void doInBackground(Void... params) {
                                    try {
                                while (check) {
                                    Thread.sleep(100);
                                    if (check3) {
                                        //I send the current count value to progress
                                        publishProgress(count);
                                        //then I increase the count
                                        count++;
                                    }
                                }
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                            return null;
                        }
                    }.execute();
                }
            }
        });

        //When "stop" is pressed...
        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //End the thread
                check = false;
                //Make "start" clickable again
                check2 = true;
                //Prevent the next value of count from showing
                check3 = false;
                //Reset the count
                count = 0;

                //This if statement will check the value of the random field and count field
                //If the values match it shows a toast with message and the average hit score
                if (Integer.parseInt(textView8.getText().toString()) ==
                        Integer.parseInt(textView9.getText().toString())) {
                    String toast = "Congrats!!! Average hit: " +
                            (Float.parseFloat(textView10.getText().toString()) +
                                    Float.parseFloat(textView11.getText().toString()) +
                                    Float.parseFloat(textView12.getText().toString())) / 3;
                    Toast.makeText(Activity3.this, toast, Toast.LENGTH_LONG).show();
                //If not it will update 3 text fields in a row sequentially with the value of the miss
                } else {
                    textView12.setText(textView11.getText());
                    textView11.setText(textView10.getText());
                    textView10.setText(textView9.getText());
                }
            }
        });
    }
}