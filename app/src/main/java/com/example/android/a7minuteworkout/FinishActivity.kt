package com.example.android.a7minuteworkout

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_finish.*

class FinishActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_finish)

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