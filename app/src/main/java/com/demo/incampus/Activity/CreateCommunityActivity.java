package com.demo.incampus.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.demo.incampus.R;

public class CreateCommunityActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_community);
    }

    public void cont (View view) {
        startActivity(new Intent(this, SuccessGroupCreate.class));
    }
}
