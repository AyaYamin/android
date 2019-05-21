    package com.example.android.aya;
    import android.app.NotificationChannel;
    import android.app.NotificationManager;
    import android.app.PendingIntent;
    import android.content.Context;
    import android.content.Intent;
    import android.content.SharedPreferences;
    import android.media.RingtoneManager;
    import android.net.Uri;
    import android.os.AsyncTask;
    import android.os.Build;
    import android.support.annotation.RequiresApi;
    import android.support.v4.app.NotificationCompat;
    import android.support.v4.app.NotificationManagerCompat;
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
    import java.util.ArrayList;

    public class ParentBody extends AppCompatActivity {

        private boolean firstTime = true;
        Toolbar toolbar;
        TextView name,id ,activity,parent;
        ImageButton imageButton,imageButton2,imageButton4;
        ArrayList<String> arrayList;
        private static final String CHANNEL_ID ="simplified_coding";
        private static final String CHANNEL_NAME ="simplified_coding";
        private static final String CHANNEL_DESC ="simplified_coding Notifications";

        @RequiresApi(api = Build.VERSION_CODES.O)
        @Override
        protected void onCreate(Bundle savedInstanceState) {

            super.onCreate(savedInstanceState);
             setContentView(R.layout.activity_parent_body);
             toolbar=(Toolbar) findViewById(R.id.toolbar);
             setSupportActionBar(toolbar);
             name=(TextView)findViewById(R.id.name);
             id=(TextView)findViewById(R.id.id);
             parent=(TextView)findViewById(R.id.parent);
             AppCompatDelegate.setCompatVectorFromResourcesEnabled(true);

            imageButton=(ImageButton)findViewById(R.id.imageButton);
            imageButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View arg0) {
                    Toast.makeText(ParentBody.this, "Activities is clicked!", Toast.LENGTH_SHORT).show();
                    Intent intent = getIntent();
                    String id = intent.getStringExtra("id");
                    Log.i("id=",id);
                    Intent intent1=new Intent(getApplicationContext(),parent_act.class);
                    intent1.putExtra("id", id);
                    startActivity(intent1);
                    ParentBody.this.finish();
                }

        });


            imageButton2=(ImageButton)findViewById(R.id.imageButton2);
            imageButton2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View arg0) {
                    Toast.makeText(ParentBody.this, "Grades is clicked!", Toast.LENGTH_SHORT).show();
                    Intent intent = getIntent();
                    String id = intent.getStringExtra("id");
                    Log.i("id=",id);
                    Intent intent1=new Intent(getApplicationContext(), aya_p.class);
                    intent1.putExtra("id", id);
                    startActivity(intent1);
                    ParentBody.this.finish();

                }

            });


            imageButton4=(ImageButton)findViewById(R.id.imageButton4);
            imageButton4.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View arg0) {
                    Toast.makeText(ParentBody.this, "Attendance is clicked!", Toast.LENGTH_SHORT).show();
                    Intent intent = getIntent();
                    String id = intent.getStringExtra("id");
                    Log.i("id=",id);
                    Intent intent1=new Intent(getApplicationContext(),   parent_att.class);
                    intent1.putExtra("id", id);
                    startActivity(intent1);
                    ParentBody.this.finish();

                }

            });




            Intent intent = getIntent();
            String id = intent.getStringExtra("id");
            Log.i("id=",id);
            getJSON("http://192.168.1.6/Parent_Student/aya/getNID.php?id="+id);
            getName("http://192.168.1.6/Parent_Student/aya/getname.php?id="+id);
            getParentt("http://192.168.1.6/Parent_Student/aya/getParentt.php?id="+id);


  /*  if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
        NotificationChannel channel = new NotificationChannel(CHANNEL_ID,CHANNEL_NAME,NotificationManager.IMPORTANCE_DEFAULT);
        channel.setDescription(CHANNEL_DESC);
        NotificationManager manager=getSystemService(NotificationManager.class);
        manager.createNotificationChannel(channel);

        }*/

    //  display();


          //  SharedPreferences sharedpreferences = getSharedPreferences("MyPrefs", Context.MODE_PRIVATE);
          //  SharedPreferences pref = getApplicationContext().getSharedPreferences("MyPref", 0); // 0 - for private mode
          //  Editor editor = pref.edit();
        }


       // private void  getNewNot(){
      //     Intent intent = getIntent();
      //     String id = intent.getStringExtra("id");
     //       Log.i("id=",id);
     //      while (firstTime==false){
      //   getNEW("http://192.168.1.6/Parent_Student/aya/getNEW.php?id="+id);
     //       }
     //   }





   /*     private void  getNEW(final String urlWebService) {
            class GetJSON extends AsyncTask<Void, Void, ArrayList<String>> {
                @Override
                protected void onPreExecute() {
                    super.onPreExecute();
                }


                @Override
                public ArrayList<String> doInBackground(Void... voids) {
                    try {
                        URL url = new URL(urlWebService);
                        HttpURLConnection con = (HttpURLConnection) url.openConnection();
                        StringBuilder sb = new StringBuilder();
                        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(con.getInputStream()));
                        String json;
                        String[] array=new String[100];
                        arrayList = new ArrayList<String>();
                        // while ((json = bufferedReader.readLine()) != null) { sb.append(json + "\n"); }
                        while ((json = bufferedReader.readLine()) != null) {
                            Log.d("aaaaac",json+"\n");
                            array=json.split(",");
                            for (String ss:array) {
                                arrayList.add(ss);
                            }
                            //  Log.d("963",array[0]+"\n"+array[1]);
                        }
                        // return sb.toString().trim();
                        for(int i=0;i<arrayList.size();i++){
                            Log.d("963",arrayList.get(i));
                        }
                        return arrayList;
                    } catch (Exception e) {
                        Log.i("msg","null");
                    }
                    return null;
                }
                @Override
                protected void onPostExecute(final ArrayList<String> s) {
                    super.onPostExecute(s);
                    int count=101;
                    NotificationCompat.Builder mBuilder=new NotificationCompat.Builder(ParentBody.this, CHANNEL_ID);
                    int x=s.size()-4;
                    for(int i=0;i<x;i++){
                        Log.d("Not",s.get(i));
                        Intent i1=new Intent(ParentBody.this,ParentBody.class);
                        PendingIntent pendingIntent=PendingIntent.getActivity(ParentBody.this,0,i1,0);
                      //  if(firstTime) {
                            mBuilder.setContentTitle("SBTS")
                                    .setContentText(s.get(i))
                                    .setSmallIcon(R.drawable.ic_drawer)
                                    // .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                                    .setContentIntent(pendingIntent)
                                    .setPriority(2)
                                    .setOnlyAlertOnce(false);
                      //  }
                        Uri alarmSound = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
                        mBuilder.setSound(alarmSound);
                        NotificationManagerCompat notificationManagerCompat=NotificationManagerCompat.from(ParentBody.this);
                        notificationManagerCompat.notify(count,mBuilder.build());
                        count++;


                    }
                  //  firstTime=false;
                }
            }
            GetJSON getJSON = new GetJSON();
            getJSON.execute();
        }

    */


        private void display(){
            Intent intent = getIntent();
            String id = intent.getStringExtra("id");
            Log.i("id=",id);
            getMSG("http://192.168.1.6/Parent_Student/aya/getMSG.php?id="+id);


        }



        private void getMSG(final String urlWebService) {
            class GetJSON extends AsyncTask<Void, Void, ArrayList<String>> {
                @Override
                protected void onPreExecute() {
                    super.onPreExecute();
                }
                @Override
                public ArrayList<String> doInBackground(Void... voids) {
                    try {
                        URL url = new URL(urlWebService);
                        HttpURLConnection con = (HttpURLConnection) url.openConnection();
                        StringBuilder sb = new StringBuilder();
                        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(con.getInputStream()));
                        String json;
                        String[] array=new String[100];
                         arrayList = new ArrayList<String>();
                        while ((json = bufferedReader.readLine()) != null) {
                            Log.d("aaaaac",json+"\n");
                            array=json.split(",");
                            for (String ss:array) {
                                arrayList.add(ss);
                            }
                        }
                       // return sb.toString().trim();
                        for(int i=0;i<arrayList.size();i++){
                            Log.d("963",arrayList.get(i));
                        }
                        return arrayList;
                    } catch (Exception e) {
                        Log.i("msg","null");
                    }
                    return null;
                }
                @Override
                protected void onPostExecute(final ArrayList<String> s) {
                    super.onPostExecute(s);
                    int count=101;
//                   Log.i("size", String.valueOf(s.size()));
                  NotificationCompat.Builder mBuilder=new NotificationCompat.Builder(ParentBody.this, CHANNEL_ID);
                   int x=s.size()-4;
                    for(int i=0;i<x;i++){
                        Log.d("Not",s.get(i));
                        Intent i1=new Intent(ParentBody.this,ParentBody.class);
                        PendingIntent pendingIntent=PendingIntent.getActivity(ParentBody.this,0,i1,0);
                        if(firstTime) {
                            mBuilder.setContentTitle("SBTS")
                                    .setContentText(s.get(i))
                                    .setSmallIcon(R.drawable.ic_drawer)
                                    .setContentIntent(pendingIntent)
                                    .setPriority(2)
                                    .setOnlyAlertOnce(false);
                        }
                        Uri alarmSound = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
                        mBuilder.setSound(alarmSound);
                        NotificationManagerCompat notificationManagerCompat=NotificationManagerCompat.from(ParentBody.this);
                       // notificationManagerCompat.setLatestEventInfo(getApplicationContext(), subject, body,pending);

                      //  notificationManagerCompat.getEventType();
                        notificationManagerCompat.notify(count,mBuilder.build());
                        count++;
                    }
                    firstTime=false;
                }
            }
            GetJSON getJSON = new GetJSON();
            getJSON.execute();
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
                    Intent intent1=new Intent(getApplicationContext(), ParentProfile.class);
                    intent1.putExtra("id", id);
                    Log.i("id=", id);
                    startActivity(intent1);
                    ParentBody.this.finish();
                    break;
                case R.id.Messages:
                    msg="Messages";
                    break;
                case R.id.signout:
                   msg="sign out";
                    Intent intent3=new Intent(getApplicationContext(), MainActivity.class);
                    startActivity(intent3);
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
                   // Toast.makeText(getApplicationContext(), s, Toast.LENGTH_SHORT).show();
                   id.setText(s);

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


                        Log.i("result is :",sb.toString().trim());
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
        private void getName(final String urlWebService) {
            class GetJSON extends AsyncTask<Void, Void, String> {

                @Override
                protected void onPreExecute() {
                    super.onPreExecute();
                }
                @Override
                protected void onPostExecute(String s) {
                    super.onPostExecute(s);
                    // Toast.makeText(getApplicationContext(), s, Toast.LENGTH_SHORT).show();
                    name.setText(s);

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


                        Log.i("result is :",sb.toString().trim());
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
        private void getParentt(final String urlWebService) {
            class GetJSON extends AsyncTask<Void, Void, String> {

                @Override
                protected void onPreExecute() {
                    super.onPreExecute();
                }
                @Override
                protected void onPostExecute(String s) {
                    super.onPostExecute(s);
                    // Toast.makeText(getApplicationContext(), s, Toast.LENGTH_SHORT).show();
                   parent.setText(s);

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


                        Log.i("result is :",sb.toString().trim());
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


