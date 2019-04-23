package com.example.android.aya;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class student extends AppCompatActivity {

   EditText id_S,pass_s;
     Button login;

    String pass_st,id_st;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student);

        id_S=(EditText)findViewById(R.id.editText10);
        pass_s=(EditText)findViewById(R.id.editText11);
        login=(Button)findViewById(R.id.button5);

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

               if( s.toString().trim().equalsIgnoreCase(pass_st)){
                    Log.i("result","aya");
                    Intent intent=new Intent(getApplicationContext(), StudentBody.class);
                    // Intent intent=new Intent(getApplicationContext(), Nav.class);
                    intent.putExtra("id",id_st);
                    startActivity(intent);
                    student.this.finish();
                }else
                {
                    //Toast.makeText(parent.this.getApplicationContext(),"Please",Toast.LENGTH_SHORT).show();}
                    pass_s.setError("Check Your Password");
                    Log.i("result","ahlamm");
                    Toast.makeText(student.this,"Please, Check Your ID & Password ^_^",Toast.LENGTH_SHORT).show();}

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

                   Log.i("result", pass_st);

                    Log.i("result", String.valueOf(sb.toString().trim().equalsIgnoreCase(pass_st)));

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

    public void ON_Login(View view) {
        id_st = id_S.getText().toString();
        pass_st = pass_s.getText().toString();
        if(id_S.getText().toString().equals("")  || pass_s.getText().toString().equals("") )
        {
            id_S.setError("Enter Your ID");
            pass_s.setError("Enter Your Password");
            Toast.makeText(student.this,"Please,Enter Your ID & Password",Toast.LENGTH_LONG).show();
        }
        else
        {
            //  new AsyncLogin().execute(id_p, pass_p);
          getJSON("http://192.168.1.6/Parent_Student/aya/connection1.php?id="+id_st+"&pass="+pass_st);
           // getJSON("http://localhost/Parent_Student/aya/connection1.php?id="+id_st+"&pass="+pass_st);
            //  getJSON("http://172.16.96.164/Parent_Student/aya/connection.php?id="+id_p+"&pass="+ pass_p);
        }
    }
}
