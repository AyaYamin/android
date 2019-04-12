package com.example.android.aya;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class StudentProfile extends AppCompatActivity {
    ImageButton imageButton99,imageButton8;
    EditText editText31,editText32,editText33,editText34;
    String ID,pass,New,REnew;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_profile);

        editText31=(EditText)findViewById(R.id.editText31);//id
        editText32=(EditText)findViewById(R.id.editText32);//pass
        editText33=(EditText)findViewById(R.id.editText33);// neww pass
        editText34=(EditText)findViewById(R.id.editText34);//re-pass

        Intent intent = getIntent();
        final String id = intent.getStringExtra("id");
        Log.i("id",id);

        imageButton8=(ImageButton)findViewById(R.id.imageButton8);
        imageButton8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                Toast.makeText(StudentProfile.this, "Back is clicked!", Toast.LENGTH_SHORT).show();
                Intent intent2=new Intent(getApplicationContext(),StudentBody.class);
                intent2.putExtra("id", id);
                startActivity(intent2);
                StudentProfile.this.finish();

            }

        });

        editText31.setText(id);

        imageButton99=(ImageButton)findViewById(R.id.imageButton99);
        imageButton99.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                pass=editText32.getText().toString();
                REnew=editText34.getText().toString();
                New=editText33.getText().toString();
                if( editText32.getText().toString().equals("") || editText34.getText().toString().equals("") || editText33.getText().toString().equals(""))
                {

                    editText32.setError("Enter Your Password");
                    editText34.setError(" RE-Enter Your New Password");
                    editText33.setError("Enter New Password ");
                }
                else{
                    Toast.makeText(StudentProfile.this, "Update is clicked!", Toast.LENGTH_SHORT).show();
                    getJSON("http://192.168.1.6/Parent_Student/aya/updateS.php?id="+id);
                }
            }

        });


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
                // Toast.makeText(getApplicationContext(), s, Toast.LENGTH_SHORT).show();
                if(s.toString().trim().equalsIgnoreCase(pass)){
                    if(REnew.equalsIgnoreCase(New)){
                        // Toast.makeText(getApplicationContext(), "Ayaaaa", Toast.LENGTH_SHORT).show();
                        Log.i("res",s.toString().trim());
                        Log.i("new",New);
                        Intent intent = getIntent();
                        final String id = intent.getStringExtra("id");
                        Log.i("id",id);
                        UPDATE("http://192.168.1.6/Parent_Student/aya/update1S.php?id="+id+"&new="+New);
                    }else{
                        editText33.setError("Incompatible Password");
                        editText34.setError("Incompatible Password");
                    }
                }
                else{
                    editText32.setError("Error Password");
                }

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

                    Log.i("result", sb.toString());


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




    //this method is actually fetching the json string
    private void UPDATE(final String urlWebService) {
        class GetJSON extends AsyncTask<Void, Void, String> {

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
            }
            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                if(s.toString().trim().equalsIgnoreCase("true")) {
                    editText32.setText("");
                    editText33.setText("");
                    editText34.setText("");
                    Toast.makeText(getApplicationContext(), "Updated Successfully ^_^", Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(getApplicationContext(), "ERROR ^_^", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public String doInBackground(Void... voids) {
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

                    // Log.i("result", sb.toString());
                    return sb.toString().trim();


                } catch (Exception e) {
                    //return null;
                    Log.i("msg","null");
                }

                return null;
            }





        }

        //creating asynctask object and executing it
        GetJSON getJSON = new GetJSON();
        getJSON.execute();
    }

}
