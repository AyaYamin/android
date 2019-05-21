package com.example.android.aya;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class parent_grades extends AppCompatActivity {
ImageButton imageButton6;
TextView textView7,textView11,textView19,textView23,textView25,textView33;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_parent_grades);

        textView7=(TextView)findViewById(R.id.textView7);
        textView11=(TextView)findViewById(R.id.textView11);
        textView19=(TextView)findViewById(R.id.textView19);
        textView23=(TextView)findViewById(R.id.textView23);
        textView25=(TextView)findViewById(R.id.textView25);
        textView33=(TextView)findViewById(R.id.textView33);

        Intent intent = getIntent();
        final String id = intent.getStringExtra("id");
        final String subject = intent.getStringExtra("subject");
        Log.i("id=",id);

        textView33.setText(subject);
       imageButton6=(ImageButton)findViewById(R.id.imageButton6);
        imageButton6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
               Toast.makeText(parent_grades.this, "Back is clicked!", Toast.LENGTH_SHORT).show();
                Intent intent2=new Intent(getApplicationContext(),ParentBody.class);
                intent2.putExtra("id", id);
                startActivity(intent2);
                parent_grades.this.finish();
            }

        });


        getJSON("http://192.168.1.6/Parent_Student/aya/getlevel.php?id="+id);
        getid("http://192.168.1.6/Parent_Student/aya/getSection.php?id="+id);
        getQuiz("http://192.168.1.6/Parent_Student/aya/getQuiz.php?id="+id);
        getMid("http://192.168.1.6/Parent_Student/aya/getMid.php?id="+id);
        getFinal("http://192.168.1.6/Parent_Student/aya/getFinal.php?id="+id);
    }

    private void getJSON(final String urlWebService) {
        class GetJSON extends AsyncTask<Void, Void, String> {
            @Override
            protected void onPreExecute() {
                super.onPreExecute();
            }
            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                Toast.makeText(getApplicationContext(), s, Toast.LENGTH_SHORT).show();
                textView7.setText(s);
            }
            @Override
            protected String doInBackground(Void... voids) {
                try {
                    URL url = new URL(urlWebService);
                    HttpURLConnection con = (HttpURLConnection) url.openConnection();
                    StringBuilder sb = new StringBuilder();
                    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(con.getInputStream()));
                    String json;
                    while ((json = bufferedReader.readLine()) != null) {
                        sb.append(json + "\n");
                    }
                    Log.i("result", sb.toString());
                    return sb.toString().trim();
                } catch (Exception e) {
                    return null;
                }}
        }
        GetJSON getJSON = new GetJSON();
        getJSON.execute();
    }


    private void getid(final String urlWebService) {
        class GetJSON extends AsyncTask<Void, Void, String> {
            @Override
            protected void onPreExecute() {
                super.onPreExecute();
            }
            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                Toast.makeText(getApplicationContext(), s, Toast.LENGTH_SHORT).show();
                textView11.setText(s);
            }
            @Override
            protected String doInBackground(Void... voids) {
                try {
                    URL url = new URL(urlWebService);
                    HttpURLConnection con = (HttpURLConnection) url.openConnection();
                    StringBuilder sb = new StringBuilder();
                    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(con.getInputStream()));
                    String json;
                    while ((json = bufferedReader.readLine()) != null) { sb.append(json + "\n"); }
                    Log.i("result", sb.toString());
                    return sb.toString().trim();
                } catch (Exception e) {
                    return null;
                } }
        }
        GetJSON getJSON = new GetJSON();
        getJSON.execute();
    }





    private void getQuiz(final String urlWebService) {
        class GetJSON extends AsyncTask<Void, Void, String> {
            @Override
            protected void onPreExecute() {
                super.onPreExecute();
            }
            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
              //  Toast.makeText(getApplicationContext(), s, Toast.LENGTH_SHORT).show();
                textView19.setText(s+"points"+"     "+"10%");
            }
            @Override
            protected String doInBackground(Void... voids) {
                try {
                    URL url = new URL(urlWebService);
                    HttpURLConnection con = (HttpURLConnection) url.openConnection();
                    StringBuilder sb = new StringBuilder();
                    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(con.getInputStream()));
                    String json;
                    while ((json = bufferedReader.readLine()) != null) { sb.append(json + "\n"); }
                    Log.i("result", sb.toString());
                    return sb.toString().trim();
                } catch (Exception e) {
                    return null;
                } }
        }
        GetJSON getJSON = new GetJSON();
        getJSON.execute();
    }

    private void getMid(final String urlWebService) {
        class GetJSON extends AsyncTask<Void, Void, String> {
            @Override
            protected void onPreExecute() {
                super.onPreExecute();
            }
            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                Toast.makeText(getApplicationContext(), s, Toast.LENGTH_SHORT).show();
                textView23.setText(s+"points"+"     "+"30%");
            }
            @Override
            protected String doInBackground(Void... voids) {
                try {
                    URL url = new URL(urlWebService);
                    HttpURLConnection con = (HttpURLConnection) url.openConnection();
                    StringBuilder sb = new StringBuilder();
                    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(con.getInputStream()));
                    String json;
                    while ((json = bufferedReader.readLine()) != null) { sb.append(json + "\n"); }
                    Log.i("result", sb.toString());
                    return sb.toString().trim();
                } catch (Exception e) {
                    return null;
                } }
        }
        GetJSON getJSON = new GetJSON();
        getJSON.execute();
    }





    private void getFinal(final String urlWebService) {
        class GetJSON extends AsyncTask<Void, Void, String> {
            @Override
            protected void onPreExecute() {
                super.onPreExecute();
            }
            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                Toast.makeText(getApplicationContext(), s, Toast.LENGTH_SHORT).show();
                textView25.setText(s+"points"+"     "+"50%");
            }
            @Override
            protected String doInBackground(Void... voids) {
                try {
                    URL url = new URL(urlWebService);
                    HttpURLConnection con = (HttpURLConnection) url.openConnection();
                    StringBuilder sb = new StringBuilder();
                    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(con.getInputStream()));
                    String json;
                    while ((json = bufferedReader.readLine()) != null) { sb.append(json + "\n"); }
                    Log.i("result", sb.toString());
                    return sb.toString().trim();
                } catch (Exception e) {
                    return null;
                } }
        }
        GetJSON getJSON = new GetJSON();
        getJSON.execute();
    }





}
