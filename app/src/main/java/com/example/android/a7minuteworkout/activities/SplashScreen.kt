package com.example.android.a7minuteworkout.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.WindowManager
import com.example.android.a7minuteworkout.R

class SplashScreen : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)


        window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        )

        Handler().postDelayed({
            var currentUserID = FireStoreClass().getCurrentUserId()
            if (currentUserID.isNotEmpty()){
               startActivity(Intent(this,MainActivity::class.java))
           }else{
              startActivity(Intent(this, IntroActivity::class.java))
           }
          finish()
        },2500)
    }
}