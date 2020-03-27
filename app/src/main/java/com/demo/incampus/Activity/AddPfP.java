package com.demo.incampus.Activity;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.demo.incampus.R;

public class AddPfP extends AppCompatActivity {
    private boolean flag = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addpfp);

        Button addpfp = findViewById(R.id.addpfpbtn);
        addpfp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    if (checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_DENIED) {
                        String[] permissions = {Manifest.permission.READ_EXTERNAL_STORAGE};
                        requestPermissions(permissions, 1001);
                    } else {
                        pickImagefromGallery();
                    }
                } else {
                    pickImagefromGallery();
                }
            }
        });

        Button done = findViewById(R.id.done);
        done.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!flag) {
                    Toast.makeText(getApplicationContext(), "You did not select any profile picture!", Toast.LENGTH_SHORT).show();
                }
                startActivity(new Intent(getApplicationContext(), Walkthrough.class));
            }
        });
    }

    private void pickImagefromGallery() {
        Intent Int = new Intent(Intent.ACTION_PICK);
        Int.setType("image/*");
        startActivityForResult(Int, 1000);
    }

    @Override
    public void onRequestPermissionsResult(
            int requestCode,
            @NonNull String[]  permissions,
            @NonNull int[] grantResults) {
                switch (requestCode){
                    case 1001: {
                            if(grantResults.length>0 && grantResults[0] ==PackageManager.PERMISSION_GRANTED){
                                pickImagefromGallery();
                            }
                            else{
                                Toast.makeText(this,"Permission was denied!",Toast.LENGTH_SHORT).show();
                            }
                    }
                }
    }

    @Override
    public void onActivityResult(int requestCode , int resultCode , Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode == Activity.RESULT_OK && requestCode == 1000){
            ImageView imageView=findViewById(R.id.imageView);
            imageView.setImageURI(data.getData());
            flag=true;
        }
    }
}
