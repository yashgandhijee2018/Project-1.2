package com.demo.incampus.Interface;

import com.demo.incampus.Model.Phone;
import com.demo.incampus.Model.Register;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface Api {
    @FormUrlEncoded
    @POST("register")
    @Headers({ "Content-Type: application/json;charset=UTF-8"})
    Call<Register> register(
            @Field("email") String username,
            @Field("username") String name,
            @Field("password") String password
            //@Body JsonObject register
    );

    @FormUrlEncoded
    @POST("login")
    Call<Register> login(
            @Field("username") String username,
            @Field("password") String password
    );

    @FormUrlEncoded
    @POST("token")
    @Headers("Content-Type:application/x-www-form-urlencoded")
    Call<ResponseBody> getToken(
            @Field("grant_type") String grantType,
            @Field("client_id") String clientId,
            @Field("client_secret") String clientSecret,
            @Field("redirect_uri") String redirectUri,
            @Field("code") String code);

    @FormUrlEncoded
    @POST("otp")
        // @Headers("Authorization: Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJuYW1lIjoieWFzaCIsImlhdCI6MTU4NDk2OTQ3MH0.y5elEi_0HoBx8H9pYn7p5qSn-669lixXK4RYYSvsBps")
    Call<Phone> otpReceive(
            @Header("Authorization") String header,
            @Field("phone_number") String phoneNumber
    );

    @FormUrlEncoded
    @POST("verifyotp")
    Call<ResponseBody> verifyotp(
            @Field("otp") String OTP,
            @Field("session_id") String sessionID);

    @FormUrlEncoded
    @POST("social/android/auth/google")
    Call<ResponseBody> google_id_token(
            @Field("id_token") String id_token_google
    );
}

