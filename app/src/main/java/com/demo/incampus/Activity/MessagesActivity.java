package com.demo.incampus.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import com.demo.incampus.Adapter.MessagesAdapter;
import com.demo.incampus.Model.Messages;
import com.demo.incampus.R;

import java.util.ArrayList;
import java.util.List;

public class MessagesActivity extends AppCompatActivity {

    //initialize variables
    RecyclerView recyclerView;
    MessagesAdapter adapter;
    List<Messages> messagesList;

    //move to home activity
    public void home (View view) {
        Intent intent = new Intent(this,HomeActivity.class);
        startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        //Hide title bar
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_messages);

        //Recycler View Code
        messagesList = new ArrayList<>();
        recyclerView = findViewById(R.id.messageRecyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        messagesList.add(new Messages("sashvat","Welcome to Book Reading","9 hrs",R.drawable.scene));
        messagesList.add(new Messages("vaibhav","Wassup Guys?","10 hrs",R.drawable.scene));
        messagesList.add(new Messages("shivam","Nice pic","10 hrs",R.drawable.scene));
        messagesList.add(new Messages("mrigank","Hi","12 hrs",R.drawable.scene));
        messagesList.add(new Messages("yash","Welcome onboard","Aug 1",R.drawable.scene));
        messagesList.add(new Messages("Aman","Hello Everyone","Aug 19",R.drawable.scene));
        messagesList.add(new Messages("sashvat","Welcome to Book Reading","9 hrs",R.drawable.scene));
        messagesList.add(new Messages("vaibhav","Wassup Guys?","10 hrs",R.drawable.scene));
        messagesList.add(new Messages("shivam","Nice pic","10 hrs",R.drawable.scene));
        messagesList.add(new Messages("mrigank","Hi","12 hrs",R.drawable.scene));
        messagesList.add(new Messages("yash","Welcome onboard","Aug 1",R.drawable.scene));
        messagesList.add(new Messages("Aman","Hello Everyone","Aug 19",R.drawable.scene));


        adapter = new MessagesAdapter(this,messagesList);
        recyclerView.setAdapter(adapter);

    }
}
