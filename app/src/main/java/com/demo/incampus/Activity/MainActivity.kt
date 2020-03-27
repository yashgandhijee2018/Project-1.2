package com.demo.incampus.Activity

import android.app.ActivityOptions
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.demo.incampus.R
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        login_btn.setOnClickListener(){
            val int=Intent(this, SignIn::class.java)
            startActivity(int,ActivityOptions.makeSceneTransitionAnimation(this).toBundle())
        }
        signup_btn.setOnClickListener(){
            val int=Intent(this, SignUp::class.java)
            startActivity(int,ActivityOptions.makeSceneTransitionAnimation(this).toBundle())
        }
    }
    override fun onBackPressed() {
        moveTaskToBack(true)
    }
}
