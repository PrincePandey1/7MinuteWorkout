package com.example.android.a7minuteworkout

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.WindowManager
import kotlinx.android.synthetic.main.activity_finish.*
import java.text.SimpleDateFormat
import java.util.*

class FinishActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_finish)

        window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
        )

        setSupportActionBar(finish_toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        finish_toolbar.setNavigationOnClickListener {
            onBackPressed()
        }

        finish_button.setOnClickListener {
            finish()
        }

    }

}