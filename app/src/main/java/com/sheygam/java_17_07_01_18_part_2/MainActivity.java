package com.sheygam.java_17_07_01_18_part_2;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private ProgressBar myProgress;
    private Button startBtn;
    private TextView countTxt;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        myProgress = findViewById(R.id.my_progress);
        startBtn = findViewById(R.id.start_btn);
        countTxt = findViewById(R.id.countTxt);
        startBtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.start_btn){
            new MyTask().execute(10,500);
        }
    }

    class MyTask extends AsyncTask<Integer,Integer,String>{

        @Override
        protected void onPreExecute() {
            myProgress.setVisibility(View.VISIBLE);
            startBtn.setEnabled(false);
            super.onPreExecute();
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            String str = String.valueOf(values[0]);
            countTxt.setText(str);
            super.onProgressUpdate(values);
        }

        @Override
        protected String doInBackground(Integer... integers) {
            int size = integers[0];
            int sleep = integers[1];
            for (int i = 0; i < size; i++) {
                try {
                    Thread.sleep(sleep);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                Log.d("MY_TAG", "doInBackground: " + i);
                publishProgress(i);
            }

            return "Done";
        }

        @Override
        protected void onPostExecute(String str) {

            myProgress.setVisibility(View.INVISIBLE);
            startBtn.setEnabled(true);
            countTxt.setText(str);
            super.onPostExecute(str);
        }
    }
}
