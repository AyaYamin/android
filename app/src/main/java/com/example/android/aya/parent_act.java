package com.example.android.aya;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

public class parent_act extends AppCompatActivity {

     ImageButton imageButton;

    public void Back_act(View view){
        Intent intent1=new Intent(getApplicationContext(), ParentBody.class);
        startActivity(intent1);
        parent_act.this.finish();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_parent_act);

      //  imageButton=(ImageButton)findViewById(R.id.imageButton3);
    }
}
