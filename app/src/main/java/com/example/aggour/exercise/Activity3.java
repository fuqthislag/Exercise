package com.example.aggour.exercise;

import android.os.Handler;
import android.os.Message;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;


public class Activity3 extends ActionBarActivity {

    TextView textView4, textView5, textView6, textView7,
            textView8, textView9, textView10, textView11, textView12;
    Button button2, button3;

    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            int message = msg.getData().getInt("msg");
            textView9.setText("" + message);
        }
    };

    Thread thread;
    Boolean check = true, check2 = true, check3 = true;
    int count = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity3);

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

        Bundle bundle = getIntent().getExtras();

        textView4.setText(bundle.getString("name"));
        textView5.setText(bundle.getString("name2"));
        textView6.setText(bundle.getString("age"));
        if (bundle.getBoolean("male")) textView7.setText("M");
        else textView7.setText("F");

        textView8.setText(new java.util.Random().nextInt(50) + 1 + "");

        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (check2) {
                    check2 = false;
                    check3 = true;
                    thread = new Thread(new Runnable() {
                        @Override
                        public void run() {
                            try {
                                while (check) {
                                    Thread.sleep(100);
                                    if (check3) {
                                        Message message = new Message();
                                        Bundle bundle = new Bundle();
                                        bundle.putInt("msg", count);
                                        message.setData(bundle);
                                        handler.sendMessage(message);
                                        count++;
                                    }
                                }
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }
                    });
                    check = true;
                    thread.start();
                }
            }
        });

        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                check = false;
                check2 = true;
                check3 = false;
                count = 0;

                if (Integer.parseInt(textView8.getText().toString()) ==
                        Integer.parseInt(textView9.getText().toString())) {
                    String toast = "Congrats!!! Average hit: " +
                            (Float.parseFloat(textView10.getText().toString()) +
                                    Float.parseFloat(textView11.getText().toString()) +
                                    Float.parseFloat(textView12.getText().toString())) / 3;
                    Toast.makeText(Activity3.this, toast, Toast.LENGTH_LONG).show();
                } else {
                    textView12.setText(textView11.getText());
                    textView11.setText(textView10.getText());
                    textView10.setText(textView9.getText());
                }
            }
        });
    }
}