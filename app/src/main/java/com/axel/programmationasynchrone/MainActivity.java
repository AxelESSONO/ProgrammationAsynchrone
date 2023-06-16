package com.axel.programmationasynchrone;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void afficherAlertDialog(View view) {
        showAlertDialog();
    }

    private void showAlertDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(
                MainActivity.this);

        View view = getLayoutInflater().inflate(R.layout.custom_alert_dialog, null);
        EditText nameEdit = view.findViewById(R.id.textField);

        builder.setView(view);
        builder.setPositiveButton("Valider", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(
                        MainActivity.this,
                        nameEdit.getText().toString(),
                        Toast.LENGTH_SHORT).show();
            }
        });

        builder.setNegativeButton("NON", null);
        builder.setNeutralButton("Annuler", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                startActivity(
                        new Intent(getApplicationContext(), ThreadActivity.class)
                );
            }
        });
        builder.create().show();
    }
}

















































