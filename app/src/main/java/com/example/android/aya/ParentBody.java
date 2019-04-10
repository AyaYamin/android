package com.example.android.aya;

import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.app.AppCompatDelegate;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class ParentBody extends AppCompatActivity {

 Toolbar toolbar;
 TextView name,id ,activity;
 ImageButton imageButton,imageButton2,imageButton4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
         setContentView(R.layout.activity_parent_body);
         toolbar=(Toolbar) findViewById(R.id.toolbar);
         setSupportActionBar(toolbar);

         name=(TextView)findViewById(R.id.name);
         id=(TextView)findViewById(R.id.id);

        AppCompatDelegate.setCompatVectorFromResourcesEnabled(true);

        imageButton=(ImageButton)findViewById(R.id.imageButton);
        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                Toast.makeText(ParentBody.this,
                        "Activities is clicked!", Toast.LENGTH_SHORT).show();
                Intent intent1=new Intent(getApplicationContext(), parent_act.class);
                startActivity(intent1);
                ParentBody.this.finish();

            }

    });


        imageButton2=(ImageButton)findViewById(R.id.imageButton2);
        imageButton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                Toast.makeText(ParentBody.this,
                        "Grades is clicked!", Toast.LENGTH_SHORT).show();
                Intent intent1=new Intent(getApplicationContext(),  parent_grades.class);
                startActivity(intent1);
                ParentBody.this.finish();

            }

        });


        imageButton4=(ImageButton)findViewById(R.id.imageButton4);
        imageButton4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                Toast.makeText(ParentBody.this,
                        "Attendance is clicked!", Toast.LENGTH_SHORT).show();
                Intent intent1=new Intent(getApplicationContext(),   parent_att.class);
                startActivity(intent1);
                ParentBody.this.finish();

            }

        });




        Intent intent = getIntent();
        String str = intent.getStringExtra("id");
        Log.i("id=",str);

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
               msg="setting";
                Intent intent1=new Intent(getApplicationContext(), ParentProfile.class);
                startActivity(intent1);
                ParentBody.this.finish();
                break;
            case R.id.Messages:
                msg="Messages";
                break;
            case R.id.signout:
               msg="sign out";
                Intent intent=new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
                ParentBody.this.finish();
                break;
            default: break;

        }
        Toast.makeText(ParentBody.this,msg,Toast.LENGTH_LONG).show();
        return super.onOptionsItemSelected(item);
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


            }

            //in this method we are fetching the json string
            @Override
            protected String doInBackground(Void... voids) {
                try {
                    //creating a URL
                    URL url = new URL(urlWebService);

                    //Opening the URL using HttpURLConnection
                    HttpURLConnection con = (HttpURLConnection) url.openConnection();

                    //StringBuilder object to read the string from the service
                    StringBuilder sb = new StringBuilder();

                    //We will use a buffered reader to read the string from service
                    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(con.getInputStream()));

                    //A simple string to read values from each line
                    String json;

                    //reading until we don't find null
                    while ((json = bufferedReader.readLine()) != null) {

                        //appending it to string builder
                        sb.append(json + "\n");
                    }



                    return sb.toString().trim();


                } catch (Exception e) {
                    return null;
                }

            }





        }

        //creating asynctask object and executing it
        GetJSON getJSON = new GetJSON();
        getJSON.execute();
    }


}


