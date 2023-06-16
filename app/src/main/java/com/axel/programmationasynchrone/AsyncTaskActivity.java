package com.axel.programmationasynchrone;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

public class AsyncTaskActivity extends AppCompatActivity {

    private TextView tvHello;
    private Button btnTraitementLong;
    private ProgressBar pbEvolution;

    private CustomAsyncTask customAsyncTask;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_async_task);

        tvHello = findViewById(R.id.tv_hello);
        btnTraitementLong = findViewById(R.id.btn_traitement_long);
        pbEvolution = findViewById(R.id.pb_evolution);

        customAsyncTask = new CustomAsyncTask(
                AsyncTaskActivity.this,
                pbEvolution,
                tvHello
        );

        btnTraitementLong.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                customAsyncTask.execute("Enfin ....");
            }
        });
    }
}