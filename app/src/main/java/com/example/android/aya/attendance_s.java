package com.example.android.aya;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class attendance_s extends AppCompatActivity {
TextView textView31;
ImageButton imageButton31;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_attendance_s);

        textView31=(TextView)findViewById(R.id.textView31);

        Intent intent = getIntent();
        final String id = intent.getStringExtra("id");
        Log.i("id=",id);

        imageButton31=(ImageButton)findViewById(R.id.imageButton13);
        imageButton31.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                Toast.makeText(attendance_s.this, "Back is clicked!", Toast.LENGTH_SHORT).show();
                Intent intent2=new Intent(getApplicationContext(),StudentBody.class);
                intent2.putExtra("id", id);
                startActivity(intent2);
                attendance_s.this.finish();
            }

        });

        getJSON("http://192.168.1.6/Parent_Student/aya/getNum.php?id="+id);

    }

    //this method is actually fetching the json string
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
                textView31.setText(s); }
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
                    Log.i("result of activity ", sb.toString());
                    return sb.toString().trim();

                } catch (Exception e) {
                    return null;
                } }}
        GetJSON getJSON = new GetJSON();
        getJSON.execute();
    }


}
