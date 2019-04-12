package com.example.android.aya;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class StudentBody extends AppCompatActivity {
    Toolbar toolbar2;
    TextView textView29,textView34,textView35,textView37,textView38;
    ImageButton imageButton10,imageButton11,imageButton12;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_body);
        toolbar2=(Toolbar) findViewById(R.id.toolbar2);
        setSupportActionBar(toolbar2);
        Intent intent = getIntent();
        final String id = intent.getStringExtra("id");
        Log.i("id",id);
        textView29=(TextView)findViewById(R.id.textView29);
        textView34=(TextView)findViewById(R.id.textView34);
        textView35=(TextView)findViewById(R.id.textView35);
        textView37=(TextView)findViewById(R.id.textView37);
        textView38=(TextView)findViewById(R.id.textView38);
        getSName("http://192.168.1.6/Parent_Student/aya/getSName.php?id="+id);
        textView34.setText("ID : "+id);
        getSID("http://192.168.1.6/Parent_Student/aya/getSID.php?id="+id);
        getCID("http://192.168.1.6/Parent_Student/aya/getCID.php?id="+id);
        getdate("http://192.168.1.6/Parent_Student/aya/getdate.php?id="+id);


        imageButton10=(ImageButton)findViewById(R.id.imageButton10);
        imageButton10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                Toast.makeText(StudentBody.this, "Absence is clicked!", Toast.LENGTH_SHORT).show();
                Intent intent = getIntent();
                String id = intent.getStringExtra("id");
                Log.i("id=",id);
                Intent intent1=new Intent(getApplicationContext(), attendance_s.class);
                intent1.putExtra("id", id);
                startActivity(intent1);
                StudentBody.this.finish();

            }

        });
        imageButton11=(ImageButton)findViewById(R.id.imageButton11);
        imageButton11.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                Toast.makeText(StudentBody.this, "Grades is clicked!", Toast.LENGTH_SHORT).show();
                Intent intent = getIntent();
                String id = intent.getStringExtra("id");
                Log.i("id=",id);
                Intent intent1=new Intent(getApplicationContext(),  grades_s.class);
                intent1.putExtra("id", id);
                startActivity(intent1);
                StudentBody.this.finish();

            }

        });
        imageButton12=(ImageButton)findViewById(R.id.imageButton12);
        imageButton12.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                Toast.makeText(StudentBody.this, "Activity is clicked!", Toast.LENGTH_SHORT).show();
                Intent intent = getIntent();
                String id = intent.getStringExtra("id");
                Log.i("id=",id);
                Intent intent1=new Intent(getApplicationContext(), activity_S.class);
                intent1.putExtra("id", id);
                startActivity(intent1);
                StudentBody.this.finish();

            }

        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        String msg="";
        switch (item.getItemId()) {
            case R.id.settings:
                msg="Edit Profile";
                Intent intent = getIntent();
                String id = intent.getStringExtra("id");
                Intent intent1=new Intent(getApplicationContext(), StudentProfile.class);
                intent1.putExtra("id", id);
                Log.i("id=", id);
                startActivity(intent1);
                StudentBody.this.finish();
                break;
            case R.id.Messages:
                msg="Messages";
                break;
            case R.id.signout:
                msg="sign out";
                Intent intent3=new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent3);
                StudentBody.this.finish();
                break;
            default: break;

        }
        Toast.makeText(StudentBody.this,msg,Toast.LENGTH_LONG).show();
        return super.onOptionsItemSelected(item);
    }




    //this method is actually fetching the json string
    private void getSName(final String urlWebService) {
        class GetJSON extends AsyncTask<Void, Void, String> {
            @Override
            protected void onPreExecute() {
                super.onPreExecute();
            }
            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                textView29.setText("Name : "+s);
            }
            @Override
            public String doInBackground(Void... voids) {
                try {
                    URL url = new URL(urlWebService);
                    HttpURLConnection con = (HttpURLConnection) url.openConnection();
                    StringBuilder sb = new StringBuilder();
                    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(con.getInputStream()));
                    String json;
                    while ((json = bufferedReader.readLine()) != null) { sb.append(json + "\n"); }
                    return sb.toString().trim();
                } catch (Exception e) {
                    Log.i("msg","null");
                }

                return null;
            }}
        GetJSON getJSON = new GetJSON();
        getJSON.execute();
    }



    //this method is actually fetching the json string
    private void getSID(final String urlWebService) {
        class GetJSON extends AsyncTask<Void, Void, String> {
            @Override
            protected void onPreExecute() {
                super.onPreExecute();
            }
            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                textView35.setText("Level : "+s);
            }
            @Override
            public String doInBackground(Void... voids) {
                try {
                    URL url = new URL(urlWebService);
                    HttpURLConnection con = (HttpURLConnection) url.openConnection();
                    StringBuilder sb = new StringBuilder();
                    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(con.getInputStream()));
                    String json;
                    while ((json = bufferedReader.readLine()) != null) { sb.append(json + "\n"); }
                    return sb.toString().trim();
                } catch (Exception e) {
                    Log.i("msg","null");
                }
                return null;
            }}
        GetJSON getJSON = new GetJSON();
        getJSON.execute();
    }







    //this method is actually fetching the json string
    private void getCID(final String urlWebService) {
        class GetJSON extends AsyncTask<Void, Void, String> {
            @Override
            protected void onPreExecute() {
                super.onPreExecute();
            }
            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                textView37.setText("Section : "+s);
            }
            @Override
            public String doInBackground(Void... voids) {
                try {
                    URL url = new URL(urlWebService);
                    HttpURLConnection con = (HttpURLConnection) url.openConnection();
                    StringBuilder sb = new StringBuilder();
                    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(con.getInputStream()));
                    String json;
                    while ((json = bufferedReader.readLine()) != null) {
                        sb.append(json + "\n");
                    }
                    return sb.toString().trim();
                } catch (Exception e) {
                    Log.i("msg","null");
                }
                return null;
            }}
        GetJSON getJSON = new GetJSON();
        getJSON.execute();
    }


    //this method is actually fetching the json string
    private void getdate(final String urlWebService) {
        class GetJSON extends AsyncTask<Void, Void, String> {
            @Override
            protected void onPreExecute() {
                super.onPreExecute();
            }
            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                textView38.setText("DateOfBirth : "+s);
            }
            @Override
            public String doInBackground(Void... voids) {
                try {
                    URL url = new URL(urlWebService);
                    HttpURLConnection con = (HttpURLConnection) url.openConnection();
                    StringBuilder sb = new StringBuilder();
                    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(con.getInputStream()));
                    String json;
                    while ((json = bufferedReader.readLine()) != null) {
                        sb.append(json + "\n");
                    }
                    return sb.toString().trim();
                    } catch (Exception e) {
                    Log.i("msg","null");
                }return null;
            }}
        GetJSON getJSON = new GetJSON();
        getJSON.execute();
    }


}
