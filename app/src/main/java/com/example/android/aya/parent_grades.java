package com.example.android.aya;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class parent_grades extends AppCompatActivity {

    public void onBack(View view){
        Intent intent1=new Intent(getApplicationContext(), ParentBody.class);
        startActivity(intent1);
        parent_grades.this.finish();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_parent_grades);
    }
}
