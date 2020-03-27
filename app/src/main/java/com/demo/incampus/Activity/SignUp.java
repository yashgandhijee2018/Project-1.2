package com.demo.incampus.Activity;

import android.app.ActivityOptions;
import android.content.Context;
import android.content.Intent;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.demo.incampus.Model.Register;
import com.demo.incampus.R;
import com.demo.incampus.Support.RetrofitClient;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.Scopes;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.Scope;
import com.google.android.gms.tasks.Task;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class SignUp extends AppCompatActivity implements GoogleApiClient.OnConnectionFailedListener {
    Boolean pwd_vis=false;

    GoogleSignInClient mGoogleSignInClient;
    GoogleApiClient mGoogleApiClient;
    SignInButton signInButtonGoogle;
    String authCode;

    int RC_SIGN_IN=0,RC_GET_AUTH_CODE=0;

    EditText email,password,username;

    String serverClientId="812077473460-pvbqdjirafrelcard0ni1ao02r3dde8r.apps.googleusercontent.com";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        final Boolean[] flag= {false,false,false};

        email=findViewById(R.id.email);
        password=findViewById(R.id.pwd);
        username=findViewById(R.id.username);

        Button signin2= findViewById(R.id.signin2);

        final SharedPreferences preferences = getSharedPreferences("prefs", Context.MODE_PRIVATE);

        signin2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent Int;
                Int = new Intent(getApplicationContext(), SignIn.class);
                //prevents looping of intents
                Int.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);

                startActivity(Int, ActivityOptions.makeSceneTransitionAnimation(SignUp.this).toBundle());
            }
        });

        email.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) { }
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) { }

            @Override
            public void afterTextChanged(Editable s) {
                if (s.toString().isEmpty())
                    return;
                if (!Patterns.EMAIL_ADDRESS.matcher(email.getText().toString()).matches()) {
                    email.getBackground().setTint(Color.RED);
                    email.setError("Type a valid email address");
                    flag[0] = false;
                }
                //TODO(Add else if email verif)
                else{
                    flag[0]=true;
                    email.getBackground().setTint(Color.parseColor("darkgrey"));
                }
            }
        });

        username.addTextChangedListener(new TextWatcher(){
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) { }
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) { }
            @Override
            public void afterTextChanged(Editable s) {
                if(s.length()<4){
                    username.getBackground().setTint(Color.RED);
                    username.setError("Username less than 4 characters");
                    flag[2]=false;
                }
                // TODO(else if(username unique constraint))
                else{
                    username.getBackground().setTint(Color.parseColor("darkgrey"));
                    flag[2]=true;
                }
            }
        });

        password.addTextChangedListener(new TextWatcher(){
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) { }
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) { }
            @Override
            public void afterTextChanged(Editable s) {
                if(s.length() < 8){
                    password.getBackground().setTint(Color.RED);
                    password.setError("Password less than 8 characters",null);
                    flag[1]=false;
                }
                else{
                    flag[1]=true;
                    password.getBackground().setTint(Color.parseColor("darkgrey"));
                }
            }
        });
        //TODO(ADD PASSWORD TOGGLE)
        Button contd=findViewById(R.id.contd);
        contd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(flag[0] && flag[1] && flag[2]){
                    API_POST_register_user(email.getText().toString(),username.getText().toString(),password.getText().toString());
                }
                else{
                    if(!Patterns.EMAIL_ADDRESS.matcher(email.getText().toString()).matches())
                        email.getBackground().setTint(Color.RED);
                    else if(username.getText().length()<4) //TODO(add unique constraint again)
                        username.getBackground().setTint(Color.RED);
                    else if(password.getText().length() < 8)
                        password.getBackground().setTint(Color.RED);
                }
            }
        });


        /*_______________________________________START_GOOGLE____________________________________________*/
        signInButtonGoogle=findViewById(R.id.login_ggl);
        // Configure sign-in to request the user's ID, email address, and basic
        // profile. ID and basic profile are included in DEFAULT_SIGN_IN.
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestScopes(new Scope(Scopes.DRIVE_APPFOLDER))
                .requestServerAuthCode(serverClientId)
                .requestEmail()
                .build();
        // Build a GoogleSignInClient with the options specified by gso.
        mGoogleSignInClient = GoogleSignIn.getClient(this, gso);

        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .enableAutoManage(this,this)
                .addApi(Auth.GOOGLE_SIGN_IN_API, gso)
                .build();

        signInButtonGoogle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switch (view.getId()) {
                    case R.id.login_ggl:
                        signIn();
                        break;
                    // ...

                }
            }
        });
        /*__________________________________________END_GOOGLE______________________________________________*/


    }

    @Override
    public void onBackPressed() {
        Intent Int =new Intent(getApplicationContext(), MainActivity.class);
        startActivity(Int);
    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }


    /*_______________________________________START_GOOGLE____________________________________________*/
    private void signIn() {
        Intent signInIntent = mGoogleSignInClient.getSignInIntent();
        startActivityForResult(signInIntent, RC_SIGN_IN);
    }
    private void handleSignInResult(Task<GoogleSignInAccount> completedTask) {
        try {
            GoogleSignInAccount account = completedTask.getResult(ApiException.class);

            // Signed in successfully, show authenticated UI.
            API_POST_google_auth_response(authCode);
            //Toast.makeText(this, "hello", Toast.LENGTH_SHORT).show();
        } catch (ApiException e) {
            // The ApiException status code indicates the detailed failure reason.
            // Please refer to the GoogleSignInStatusCodes class reference for more information.
            Log.w("Error:", "signInResult:failed code=" + e.getStatusCode());
        }
    }

    private void getAuthCode() {
        // Start the retrieval process for a server auth code.  If requested, ask for a refresh
        // token.  Otherwise, only get an access token if a refresh token has been previously
        // retrieved.  Getting a new access token for an existing grant does not require
        // user consent.
        Intent signInIntent = Auth.GoogleSignInApi.getSignInIntent(mGoogleApiClient);
        startActivityForResult(signInIntent, RC_GET_AUTH_CODE);
    }

    /*
    @Override
    protected void onStart() {
        super.onStart();
        // Check for existing Google Sign In account, if the user is already signed in
        // the GoogleSignInAccount will be non-null.
        GoogleSignInAccount account = GoogleSignIn.getLastSignedInAccount(this);
        Intent i=new Intent(this,OTP.class);
        startActivity(i);
    }
    */

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // Result returned from launching the Intent from GoogleSignInClient.getSignInIntent(...);
        if (requestCode == RC_SIGN_IN) {
            // The Task returned from this call is always completed, no need to attach
            // a listener.
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            handleSignInResult(task);

            //Toast.makeText(this, "hello", Toast.LENGTH_SHORT).show();
        }

        if(requestCode==RC_GET_AUTH_CODE)
        {
            SharedPreferences preferences = getSharedPreferences("prefs", Context.MODE_PRIVATE);
            final SharedPreferences.Editor editor = preferences.edit();

            GoogleSignInResult result = Auth.GoogleSignInApi.getSignInResultFromIntent(data);
            Log.d("Success: ", "onActivityResult:GET_AUTH_CODE:success:" + result.getStatus().isSuccess());

            if (result.isSuccess()) {
                // [START get_auth_code]
                GoogleSignInAccount acct = result.getSignInAccount();
                authCode = acct.getServerAuthCode();
                String idTokenString = acct.getIdToken();

                // Show signed-in UI.
                Log.i("Auth: ",authCode);
                //STORING IN SHARED PREFERENCES GOOGLE AUTH CODE
                editor.putString("Auth", authCode );
                editor.commit();
                // TODO(user): send code to server and exchange for access/refresh/ID tokens.
                // [END get_auth_code]
                API_POST_google_auth_response(authCode);
            }
        }
    }
    /*__________________________________________END_GOOGLE______________________________________________*/

    public void API_POST_register_user(String personEmail,String personGivenName,String personPassword)
    {
        SharedPreferences preferences = getSharedPreferences("prefs", Context.MODE_PRIVATE);
        final SharedPreferences.Editor editor = preferences.edit();

        //API POST REGISTER
        retrofit2.Call<Register> register= RetrofitClient.getInstance().getApi().register(personEmail,personGivenName,personPassword);
        register.enqueue(new Callback<Register>() {
            @Override
            public void onResponse(retrofit2.Call<Register> call, Response<Register> response) {
                try {

                    Register s=response.body();
                    editor.putString("JWT", s.getAccessToken());
                    editor.commit();
                    Toast.makeText(SignUp.this, s.getAccessToken(), Toast.LENGTH_SHORT).show();
                    Intent i=new Intent(SignUp.this,PhoneNumberActivity.class);
                    startActivity(i);


                    Log.i("response","Got response from user");
                } catch (Exception e) {
                    e.printStackTrace();
                    Toast.makeText(SignUp.this,"User already registered!", Toast.LENGTH_SHORT).show();
                    Log.i("No response","exception");
                }
            }

            @Override
            public void onFailure(Call<Register> call, Throwable t) {
                Toast.makeText(SignUp.this, t.getMessage(), Toast.LENGTH_SHORT).show();
                Log.i("Info","No response for register user post");
            }

        });
    }

    public void API_POST_google_auth_response(String id_token)
    {
        Toast.makeText(this, "called google auth", Toast.LENGTH_SHORT).show();
        SharedPreferences preferences = getSharedPreferences("prefs", Context.MODE_PRIVATE);
        final SharedPreferences.Editor editor = preferences.edit();

        //API POST REGISTER
        retrofit2.Call<ResponseBody> register= RetrofitClient.getInstance().getApi().google_id_token(id_token);
        register.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(retrofit2.Call<ResponseBody> call, Response<ResponseBody> response) {

                try {
                    if(response.isSuccessful())
                    {
                        ResponseBody s=response.body();
                        editor.putString("JWT", s.string());
                        editor.commit();
                        Toast.makeText(SignUp.this, "rgf", Toast.LENGTH_SHORT).show();

                        Intent i=new Intent(SignUp.this,PhoneNumberActivity.class);
                        startActivity(i);
                        Log.i("response","Got response from user");
                    }
                    else
                    {
                        Toast.makeText(SignUp.this, "Service unavailable", Toast.LENGTH_SHORT).show();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    Toast.makeText(SignUp.this,"User already registered!", Toast.LENGTH_SHORT).show();
                    Log.i("No response","exception");
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Toast.makeText(SignUp.this, t.getMessage(), Toast.LENGTH_SHORT).show();
                Log.i("Info","No response for register user post");
            }

        });
    }
}
