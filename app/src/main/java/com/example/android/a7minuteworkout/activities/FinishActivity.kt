package com.example.android.a7minuteworkout.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.WindowManager
import com.example.android.a7minuteworkout.R
import kotlinx.android.synthetic.main.activity_finish.*

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