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



        public void onClick_Login(View view) {
            final String id_p = Parent_id.getText().toString();
            final String pass_p = Parent_pass.getText().toString();
            new AsyncLogin().execute(id_p, pass_p);
        }


        private class AsyncLogin extends AsyncTask<String, String, String> {
            ProgressDialog pdLoading = new ProgressDialog(parent.this);
            HttpURLConnection conn;
            URL url = null;

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                //this method will be running on UI thread
                pdLoading.setMessage("\tLoading...");
                pdLoading.setCancelable(false);
                pdLoading.show();

            }

            @Override
            protected String doInBackground(String... params) {
                     try{
                         url = new URL("http://192.168.1.6/Parent_Student/aya/connection.php?id="+id_p+"&pass="+ pass_p);
                         conn = (HttpURLConnection) url.openConnection();
                         conn.setRequestMethod("POST");
                         conn.setDoInput(true);
                         conn.setDoOutput(true);
                         Uri.Builder builder = new Uri.Builder().appendQueryParameter("id", params[0]).appendQueryParameter("pass", params[1]);
                         String query = builder.build().getEncodedQuery();
                         Log.i("msg",query);
                         OutputStream os = conn.getOutputStream();
                         BufferedWriter writer = new BufferedWriter( new OutputStreamWriter(os, "UTF-8"));
                         writer.write(query);
                         writer.flush();
                         writer.close();
                         os.close();
                         conn.connect();
                         int response_code = conn.getResponseCode();
                         Log.i("response", String.valueOf(response_code));
                         if (response_code == HttpURLConnection.HTTP_OK) {
                             InputStream input = conn.getInputStream();
                             BufferedReader reader = new BufferedReader(new InputStreamReader(input));
                             StringBuilder result = new StringBuilder();
                             //String line;
                           //  while ((line = reader.readLine()) != null) {
                           //      result.append(line);
                           //  }
                           //  return(result.toString());
                             onPostExecute(result);
                             return String.valueOf((result));
                         }
                         else{
                             Toast.makeText(getApplicationContext(),"Please,Check Your Connection",Toast.LENGTH_LONG).show();
                         }

                     } catch (MalformedURLException e) {
                         e.printStackTrace();
                     } catch (IOException e) {
                         e.printStackTrace();
                     }

                return null;
            }

           //@Override
            protected void onPostExecute(StringBuilder result) {
                pdLoading.dismiss();
               // Log.i("aya 22","aya");
                Log.i("aya result", result.toString());
              // if(result==true)
               if(result.toString()=="true")
              // if(result.equalsIgnoreCase("true"))
                {
                   // Log.i("aya 22","ahlaaam");
                  //  Intent intent=new Intent(getApplicationContext(), ParentBody.class);
                  //  startActivity(intent);
                  //  parent.this.finish();
                }
                /*else if (result.equalsIgnoreCase("false")){

                    // If username and password does not match display a error message
                    Toast.makeText(parent.this, "Invalid ID or Password", Toast.LENGTH_LONG).show();

                } else if (result.equalsIgnoreCase("exception") || result.equalsIgnoreCase("unsuccessful")) {

                    Toast.makeText(parent.this, "OOPs! Something went wrong. Connection Problem.", Toast.LENGTH_LONG).show();

                }*/
            }

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
