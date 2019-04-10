    package com.example.android.aya;

    import android.app.ProgressDialog;
    import android.content.Intent;
    import android.net.Uri;
    import android.os.AsyncTask;
    import android.support.v7.app.AppCompatActivity;
    import android.os.Bundle;
    import android.util.Log;
    import android.view.View;
    import android.widget.Button;
    import android.widget.EditText;
    import android.widget.Toast;

    import org.json.JSONException;
    import org.json.JSONObject;

    import java.io.BufferedReader;
    import java.io.BufferedWriter;
    import java.io.IOException;

    import java.io.InputStream;
    import java.io.InputStreamReader;
    import java.io.OutputStream;
    import java.io.OutputStreamWriter;
    import java.net.HttpURLConnection;
    import java.net.MalformedURLException;
    import java.net.URL;

    public class parent extends AppCompatActivity {
         EditText Parent_id,Parent_pass;
         Button login;
         String id_p ;
         String pass_p;
         String id;


        public void onClick_Login(View view) {
             id_p = Parent_id.getText().toString();
             pass_p = Parent_pass.getText().toString();
            if(Parent_id.getText().toString().equals("")  || Parent_pass.getText().toString().equals("") )
                {
                    Parent_id.setError("Enter Your ID");
                    Parent_pass.setError("Enter Your Password");
                    Toast.makeText(parent.this,"Please,Enter Your ID & Password",Toast.LENGTH_LONG).show();
                }
            else
                {
                  //  new AsyncLogin().execute(id_p, pass_p);
                    getJSON("http://192.168.1.6/Parent_Student/aya/connection.php?id="+id_p+"&pass="+ pass_p);
                  //  getJSON("http://172.16.96.164/Parent_Student/aya/connection.php?id="+id_p+"&pass="+ pass_p);
                }
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

                      if( s.toString().trim().equalsIgnoreCase(pass_p)){
                        Log.i("result","aya");
                        Intent intent=new Intent(getApplicationContext(), ParentBody.class);
                         // Intent intent=new Intent(getApplicationContext(), Nav.class);
                          intent.putExtra("id",id_p);
                        startActivity(intent);
                        parent.this.finish();
                    }else
                    {
                        //Toast.makeText(parent.this.getApplicationContext(),"Please",Toast.LENGTH_SHORT).show();}
                        Parent_pass.setError("Enter Your Password");
                        Log.i("result","ahlamm");
                        Toast.makeText(parent.this,"Please, Check Your ID & Password ^_^",Toast.LENGTH_SHORT).show();}

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

                        Log.i("result", pass_p);

                        Log.i("result", String.valueOf(sb.toString().trim().equalsIgnoreCase(pass_p)));

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



            @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_parent);
            Parent_id=(EditText)findViewById(R.id.editText);
            Parent_pass=(EditText)findViewById(R.id.editText2);
            login=(Button)findViewById(R.id.button);

        }
    }
