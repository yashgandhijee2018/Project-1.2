package com.demo.incampus.Activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.demo.incampus.Model.Phone;
import com.demo.incampus.R;
import com.demo.incampus.Support.RetrofitClient;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import org.json.JSONException;
import org.json.JSONObject;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class OTP extends AppCompatActivity {
    private EditText[] et = new EditText[6];

    String phoneNumber = "";
    String OTP = "";
    String serverAccessToken = "";
    String sessionID = "";
    GoogleSignInClient mGoogleSignInClient;
    private String jwt_token;

    Button mnext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //if the system api is below marshmallow, set status bar to default black
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M) {
            getWindow().setStatusBarColor(Color.BLACK);
        }
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_otp);

        Intent i = getIntent();
        phoneNumber = i.getExtras().getString("phoneNumber");
        Log.i("Phone", phoneNumber);

        SharedPreferences preferences = getSharedPreferences("prefs", Context.MODE_PRIVATE);
        mnext=findViewById(R.id.next);

        et[0] = findViewById(R.id.etone);
        et[1] = findViewById(R.id.ettwo);
        et[2] = findViewById(R.id.etthree);
        et[3] = findViewById(R.id.etfour);
        et[4] = findViewById(R.id.etfive);
        et[5] = findViewById(R.id.etsix);

        et[0].addTextChangedListener(new TextWatcher() {

            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            public void afterTextChanged(Editable s) {
                if (s.length() == 1) {
                    et[1].requestFocus();
                }
            }
        });
        et[1].addTextChangedListener(new TextWatcher() {
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            public void afterTextChanged(Editable s) {
                if (s.length() == 1) {
                    et[2].requestFocus();
                } else {
                    et[0].requestFocus();
                }
            }
        });
        et[2].addTextChangedListener(new TextWatcher() {

            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            public void afterTextChanged(Editable s) {
                if (s.length() == 1) {
                    et[3].requestFocus();
                } else {
                    et[1].requestFocus();
                }
            }
        });
        et[3].addTextChangedListener(new TextWatcher() {

            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            public void afterTextChanged(Editable s) {
                if (s.length() == 1) {
                    et[4].requestFocus();
                } else {
                    et[2].requestFocus();
                }
            }
        });
        et[4].addTextChangedListener(new TextWatcher() {

            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            public void afterTextChanged(Editable s) {
                if (s.length() == 1) {
                    et[5].requestFocus();
                } else {
                    et[3].requestFocus();
                }
            }
        });
        et[5].addTextChangedListener(new TextWatcher() {

            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            public void afterTextChanged(Editable s) {
                if (s.length() != 1)
                    et[4].requestFocus();
            }
        });


        API_POST_receive_OTP(phoneNumber);//RECEIVE SessionID and OTP

        jwt_token = preferences.getString("JWT", "expires");


        mnext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (vibe_check()) {
                    for (int i = 0; i < 6; i++) {
                        OTP += et[i].getText().toString();
                    }

                    if (!jwt_token.equals("expire")) {
                        API_POST_verify_OTP(OTP, sessionID);

                    } else {
                        Toast.makeText(OTP.this, "can not retrieve jwt token", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });


    }

    protected boolean vibe_check() {
        for (int i = 0; i < 6; i++) {
            if (et[i].getText().length() != 1)
                return false;
        }
        return true;
    }


    public void API_POST_receive_OTP(String phoneNumber) {
        //API CALL RECEIVE OTP
        Toast.makeText(this, jwt_token, Toast.LENGTH_SHORT).show();
        String token = "Bearer " + jwt_token;
        Call<Phone> otpReceive = RetrofitClient.getInstance().getApi().otpReceive(token, phoneNumber);
        otpReceive.enqueue(new Callback<Phone>() {
            @Override
            public void onResponse(Call<Phone> call, Response<Phone> response) {
                try {

                    if (response.isSuccessful()) {
                        Phone phone = response.body();

                        Toast.makeText(OTP.this, phone.getDetails(), Toast.LENGTH_SHORT).show();
                        sessionID = phone.getDetails();

                    } else {
                        Toast.makeText(OTP.this, "Response is not sucesful", Toast.LENGTH_SHORT).show();
                    }


                    /*
                    String s = response.body().string();
                    Log.i("response", s);

                    // Toast.makeText(OTPJava.this, "JOKER", Toast.LENGTH_SHORT).show();

                    JSONObject jsonObject = new JSONObject();
                    sessionID = jsonObject.getString("Details");

                    Intent intent = new Intent(OTPJava.this , Walkthrough.class);
                    startActivity(intent);*/

                    Log.i("sessionID", sessionID);
                } catch (Exception e) {
                    if (e instanceof NullPointerException) {
                        Toast.makeText(OTP.this, "Excenption = >  " + e.getMessage(), Toast.LENGTH_SHORT).show();

                    } else {


                    }
                }

            }

            @Override
            public void onFailure(Call<Phone> call, Throwable t) {
                Toast.makeText(OTP.this, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void API_POST_verify_OTP(String OTP, String sessionID) {
        //API CALL VERIFY OTP

        Call<ResponseBody> verifyotp = RetrofitClient.getInstance().getApi().verifyotp(OTP, sessionID);
        verifyotp.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                try {
                    //String s = response.body().string();
                    //  Log.i("verification", s);

                    Intent intent = new Intent(OTP.this, Walkthrough.class);
                    startActivity(intent);

                } catch (Exception e) {

                    if (e instanceof NullPointerException) {

                        Toast.makeText(OTP.this, "WrongOTP", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(OTP.this, "Time Out", Toast.LENGTH_SHORT).show();
                    }
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Toast.makeText(OTP.this, "Error", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void API_POST_login_user(String personGivenName, String personId) {
        //API POST LOGIN USER

        SharedPreferences preferences = getSharedPreferences("prefs", Context.MODE_PRIVATE);
/*
      Call<ResponseBody> login = RetrofitClient.getInstance().getApi().otpReceive(preferences.getString("JWT" , ""), personId);
        login.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {


                Toast.makeText(OTPJava.this, response.toString(), Toast.LENGTH_SHORT).show();
                  Intent intent = new Intent(OTPJava.this , Walkthrough.class);
                  startActivity(intent);


            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Toast.makeText(OTPJava.this, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });*/
    }

    private JsonObject ApiJsonMap() {

        JsonObject gsonObject = new JsonObject();
        try {
            JSONObject jsonObj_ = new JSONObject();
            jsonObj_.put("key", "value");
            jsonObj_.put("key", "value");
            jsonObj_.put("key", "value");


            JsonParser jsonParser = new JsonParser();
            gsonObject = (JsonObject) jsonParser.parse(jsonObj_.toString());

            //print parameter
            Log.e("MY gson.JSON:  ", "AS PARAMETER  " + gsonObject);

        } catch (JSONException e) {
            e.printStackTrace();
        }

        return gsonObject;
    }


}
