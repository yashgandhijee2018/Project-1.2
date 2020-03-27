package com.demo.incampus.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Editable;
import android.text.InputFilter;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.demo.incampus.Activity.OTP;
import com.demo.incampus.Activity.SignUp;
import com.demo.incampus.R;

public class PhoneNumberActivity extends AppCompatActivity {

    EditText number;
    int count = 0;
    String phoneNumber;
    public void verify(View view) {
        if(number.getText().length()==14){
            phoneNumber=number.getText().toString();
            Intent intent = new Intent(this, OTP.class);
            intent.putExtra("phoneNumber",phoneNumber);
            Toast.makeText(this, phoneNumber, Toast.LENGTH_SHORT).show();
            startActivity(intent);
        }
        else{
            number.getBackground().setTint(Color.RED);
        }
    }

    public void back(View view){
        onBackPressed();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_phone_number);

        number = findViewById(R.id.enterNumber);

        //limit number of inputs
        InputFilter[] filterArray = new InputFilter[1];
        filterArray[0] = new InputFilter.LengthFilter(14);
        number.setFilters(filterArray);

        //add a space after 2 characters
        number.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) { /*Empty*/}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) { /*Empty*/ }

            @Override
            public void afterTextChanged(Editable s) {

                int inputlength = number.getText().toString().length();

                if (count <= inputlength && (inputlength == 2 || inputlength == 5 || inputlength == 8 || inputlength == 11)){
                    number.setText(number.getText().toString() + " ");

                    int pos = number.getText().length();
                    number.setSelection(pos);

                } else if (count >= inputlength && (inputlength == 2 || inputlength == 5 || inputlength == 8 || inputlength == 11)) {
                    number.setText(number.getText().toString().substring(0, number.getText().toString().length() - 1));

                    int pos = number.getText().length();
                    number.setSelection(pos);
                }
                count = number.getText().toString().length();
                if(count!=14){
                    number.getBackground().setTint(Color.RED);
                    number.setError("Phone number should be of 10 digits");
                }
                else
                    number.getBackground().setTint(Color.rgb(138,86,172));
            }
        });

    }


    @Override
    public void onBackPressed() {
        Intent intent = new Intent(this, SignUp.class).addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
        startActivity(intent);
    }


}
