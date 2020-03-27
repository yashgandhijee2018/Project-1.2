package com.demo.incampus.Activity

import android.content.Intent
import android.graphics.Color
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.demo.incampus.R
import kotlinx.android.synthetic.main.activity_categories.*

class Categories : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        //if the system api is below marshmallow, set status bar to default black
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M) {
            window.statusBarColor = Color.BLACK
        }
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_categories)
        add_cat.setOnClickListener{
            //add
        }
    }

    fun letsGo (view : View) {
        val intent = Intent(this, CreateCommunityActivity::class.java)
        startActivity(intent)
    }
}
