package com.demo.incampus.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import com.demo.incampus.Adapter.HomeAdapter;
import com.demo.incampus.Model.Home;
import com.demo.incampus.R;

import java.util.ArrayList;
import java.util.List;

public class ExploreActivity extends AppCompatActivity {

    //initialize variables
    RecyclerView contentRecyclerView;
    HomeAdapter adapter;
    List<Home> homeList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        //Hide title bar
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_explore);

        //Recycler View Code
        homeList = new ArrayList<>();
        contentRecyclerView = findViewById(R.id.contentRecyclerview);
        contentRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        homeList.add(new Home("Basketball", "bond_007","15 min ago",
                "Anyone up for the basketball game? I will be at the complex around 5pm. Acknowledge if you are coming.",
                "05","06",R.drawable.scene
        ));

        homeList.add(new Home("Cricket", "abc_123","1 hour ago",
                "Anyone up for the cricket game? I will be at the complex around 5pm. Acknowledge if you are coming.",
                "10","12",R.drawable.scene
        ));

        homeList.add(new Home("Football", "xyz_420","2 hours ago",
                "Anyone up for the football game? I will be at the complex around 5pm. Acknowledge if you are coming.",
                "12","14",R.drawable.scene
        ));

        homeList.add(new Home("Badminton", "pqr_abc","2 hours ago",
                "Anyone up for the badminton game? I will be at the complex around 5pm. Acknowledge if you are coming.",
                "15","17",R.drawable.scene
        ));

        homeList.add(new Home("Basketball", "bond_007","3 hours ago",
                "Anyone up for the basketball game? I will be at the complex around 5pm. Acknowledge if you are coming.",
                "25","65",R.drawable.scene
        ));

        homeList.add(new Home("Cricket", "abc_123","5 hours ago",
                "Anyone up for the cricket game? I will be at the complex around 5pm. Acknowledge if you are coming.",
                "50","124",R.drawable.scene
        ));

        homeList.add(new Home("Football", "pqr_abc","6 hours ago",
                "Anyone up for the football game? I will be at the complex around 5pm. Acknowledge if you are coming.",
                "24","140",R.drawable.scene
        ));

        homeList.add(new Home("Badminton", "xyz_420","7 hours ago",
                "Anyone up for the badminton game? I will be at the complex around 5pm. Acknowledge if you are coming.",
                "51","176",R.drawable.scene
        ));

        adapter = new HomeAdapter(this,homeList);
        contentRecyclerView.setAdapter(adapter);
    }

    //move to home activity
    public void home (View view) {
        Intent intent = new Intent(this,HomeActivity.class);
        startActivity(intent);
    }

    //move to messages activity
    public void messages (View view) {
        Intent intent = new Intent(getApplicationContext(),MessagesActivity.class);
        startActivity(intent);
    }
}
