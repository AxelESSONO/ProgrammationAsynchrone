package com.axel.programmationasynchrone;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.SystemClock;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

public class ThreadActivity extends AppCompatActivity
        implements View.OnClickListener {

    private Button buttonStart1, buttonStart2, buttonChangerActivity;
    private ProgressBar progressBar1, progressBar2;
    private TextView textViewInfo1,textViewInfo2;
    private Handler handler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thread);

        progressBar1 =  findViewById(R.id.progressBar1);
        progressBar2 = findViewById(R.id.progressBar2);
        progressBar2.setIndeterminate(false);
        textViewInfo1 =  findViewById(R.id.textView_info1);
        textViewInfo2 =  findViewById(R.id.textView_info2);
        buttonStart1 =  findViewById(R.id.button_start1);
        buttonStart2 = findViewById(R.id.button_start2);
        buttonChangerActivity = findViewById(R.id.button_change_activity);
        handler = new Handler();

        buttonStart1.setOnClickListener(this);
        buttonStart2.setOnClickListener(this);
       buttonChangerActivity.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.button_start1){
            doProgress();
        } else if (view.getId() == R.id.button_start2) {
            doProgressIndeterminate();
        }else {
            startActivity(
                    new Intent(this, AsyncTaskActivity.class)
            );
        }
    }

    private void doProgressIndeterminate() {
        progressBar2.setIndeterminate(true);
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        textViewInfo2.setText("Working ...");
                        buttonStart2.setEnabled(false);
                    }
                });

                SystemClock.sleep(5000);
                handler.post(()->{
                    progressBar2.setIndeterminate(false);
                    progressBar2.setMax(1);
                    progressBar2.setProgress(1);
                    textViewInfo2.setText("Completed!");
                    buttonStart2.setEnabled(true);
                });
            }
        });
        thread.start();
    }

    private void doProgress() {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                final int MAX = 100;
                for (int i = 0; i < MAX; i++) {
                    final int progress = i + 1;
                    SystemClock.sleep(50);
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            int percent = (progress * 100) / MAX;
                            progressBar1.setProgress(progress);
                            textViewInfo1.setText("Percent: "+ percent + "%");
                            if (progress == MAX){
                                textViewInfo1.setText("Completed!");
                            }
                        }
                    });
                }
            }
        });
        thread.start();
    }
}











































