package com.example.android.a7minuteworkout.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.WindowManager
import com.example.android.a7minuteworkout.R
import kotlinx.android.synthetic.main.activity_intro.*

class IntroActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_intro)

        window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,  //Inorder to hide status bar
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
        )

        btn_SignIn_Intro.setOnClickListener {
            val intent = Intent(this,SignInActivity::class.java)
            startActivity(intent)
        }

        btn_SignUp_intro.setOnClickListener {
            startActivity(Intent(this,SignUp::class.java))
        }

    }
}