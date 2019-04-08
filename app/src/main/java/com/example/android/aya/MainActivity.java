package com.example.android.aya;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    Button student;
    Button Parent;

    public void onStudentClick(View view){
        Intent intent=new Intent(getApplicationContext(),student.class);
        startActivity(intent);
    }

    public void  onParentClick(View view){
        Intent intent=new Intent(getApplicationContext(), parent.class);
        startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        student=(Button)findViewById(R.id.button3);
        Parent=(Button)findViewById(R.id.button4);
    }



}
