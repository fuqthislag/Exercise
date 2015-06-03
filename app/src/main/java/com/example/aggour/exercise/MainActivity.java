package com.example.aggour.exercise;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends Activity {

    //Declare the text
    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //I'm making a thread and putting it to sleep for 3 seconds as simple splash screen
        new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(Void... params) {
                try {
                    //Put thread to sleep for 3000ms
                    Thread.sleep(3000);
                    //After sleep it goes to the next activity
                    startActivity(new Intent(MainActivity.this, Activity2.class));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                return null;
            }
        }.execute();

        //Initialise the text
        textView = (TextView) findViewById(R.id.asdf);

        //I secretly made the text clickable in case the user goes back to the splash screen
        //which results in the thread to not initialise again and clicking the text will go
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, Activity2.class));
            }
        });
    }
}
