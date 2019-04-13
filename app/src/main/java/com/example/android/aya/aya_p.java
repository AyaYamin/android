package com.example.android.aya;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

public class aya_p extends AppCompatActivity {
    ArrayList<String> arrayList;
    ImageButton imageButton9;
    TextView textView42;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_aya_p);

        Intent intent = getIntent();
        String id = intent.getStringExtra("id");
        Log.i("id=",id);
        textView42=(TextView)findViewById(R.id.textView42);
        imageButton9=(ImageButton)findViewById(R.id.imageButton9);
        imageButton9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                Toast.makeText(aya_p.this, "Back is clicked!", Toast.LENGTH_SHORT).show();
                Intent intent2=new Intent(getApplicationContext(),ParentBody.class);
                Intent intent = getIntent();
                String id = intent.getStringExtra("id");
                intent2.putExtra("id",id);
                startActivity(intent2);
                aya_p.this.finish();
            }

        });
        getJSON("http://192.168.1.6/Parent_Student/aya/getSEM1.php?id="+id);
        getSubjects("http://192.168.1.6/Parent_Student/aya/getSubjects1.php?id="+id);
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
                textView42.setText("\n"+s+"Semester"); }
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


    private void getSubjects(final String urlWebService) {
        class GetJSON extends AsyncTask<Void, Void, ArrayList<String>> {
            @Override
            protected void onPreExecute() {
                super.onPreExecute();
            }
            @Override
            protected ArrayList<String> doInBackground(Void... voids) {
                try {
                    URL url = new URL(urlWebService);
                    HttpURLConnection con = (HttpURLConnection) url.openConnection();
                    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(con.getInputStream()));
                    String json;
                    String[] array=new String[100];
                    arrayList=new ArrayList<String>();
                    while ((json = bufferedReader.readLine()) != null) {
                        Log.d("aaaaac",json+"\n");
                        array=json.split(" ");
                        for (String ss:array) {
                            arrayList.add(ss);
                        }
                        //   Log.d("963",array[0]+"\n"+array[1]);
                    }
                    for(int i=0;i<arrayList.size();i++){
                        Log.d("963",arrayList.get(i));
                    }

                    return arrayList;
                } catch (Exception e) {
                    return null; } }
            @Override
            protected void onPostExecute(final ArrayList<String> s) {
                super.onPostExecute(s);
                for(int i=0;i<s.size();i++){
                    Log.d("aya_963",s.get(i));
                }
                ListView myListView=(ListView)findViewById(R.id.list);
                ArrayAdapter<String> arrayAdapter=new ArrayAdapter<String>(aya_p.this,android.R.layout.simple_list_item_1,s);
                myListView.setAdapter(arrayAdapter);
                myListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                        //Toast.makeText(getApplicationContext(), , Toast.LENGTH_LONG).show();
                        Intent intent1=new Intent(getApplicationContext(), parent_grades.class);
                        Intent intent = getIntent();
                        final String id = intent.getStringExtra("id");
                        //  Log.i("id=",id);
                        intent1.putExtra("id",id);
                        intent1.putExtra("subject",s.get(i));
                        startActivity(intent1);
                        aya_p.this.finish();
                    }
                });
            }
        }
        GetJSON getJSON = new GetJSON();
        getJSON.execute(); }
}

