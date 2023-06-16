package com.axel.programmationasynchrone;

import android.content.Context;
import android.os.AsyncTask;
import android.os.SystemClock;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

/**
 * AsyncTask <A, B, C>
 *     A : Type des données passées au traitement long : fonction doInBackground()
 *     B : Type des données envoyées depuis le traitement long au thread UI via onProgressUpdate()
 *     C : Type de la donnée retournée par le traitement long : fonction doInBackground()
 */
public class CustomAsyncTask extends AsyncTask<String, Integer, String> {


    private Context context;
    private ProgressBar progressBar;
    private TextView textView;

    public CustomAsyncTask(Context context, ProgressBar progressBar, TextView textView) {
        this.context = context;
        this.progressBar = progressBar;
        this.textView = textView;
    }

    // Fonction exécutée avant le traitement long sur le thread UI
    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        Toast.makeText(context, "Début du traitement", Toast.LENGTH_SHORT).show();
    }

    /**
     * Fonction exécutant un traitement Long sur un nouveau Thread
     */
    @Override
    protected String doInBackground(String... strings) {

        final int MAX = 100;
        for (int i = 0; i < MAX; i++) {
            final int progress = i + 1;
            SystemClock.sleep(45);
            publishProgress(progress);
        }
        return "Fin du traitement : " + strings[0];
    }

    /**
     * Fonction exécutée pendant le traitement long sur le thread UI
     * cette fonction est appelée à l'appel de la fonction de publishProgress()
     */
    @Override
    protected void onProgressUpdate(Integer... values) {
        super.onProgressUpdate(values);

        progressBar.setProgress(values[0]);
        textView.setText("Percent : "+ values[0] + " %");
    }

    /**
     * Fonction exécutée après le traitement long sur le thread UI
     */
    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
        Toast.makeText(context, s, Toast.LENGTH_SHORT).show();
    }
}



















































