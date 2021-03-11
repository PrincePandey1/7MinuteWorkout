package com.example.android.a7minuteworkout

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.view.View
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_exercise.*

class ExerciseActivity : AppCompatActivity() {

    private var restTimer: CountDownTimer? = null
    private var restProgress = 1

    private var exerciseTimer: CountDownTimer? = null
    private var exerciseProgress = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_exercise)

        setSupportActionBar(toolbar_exercise_activity)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        toolbar_exercise_activity.setNavigationOnClickListener {
            onBackPressed()
        }
        setupRestView()
    }

    override fun onDestroy() {
        if(restTimer!=null){
            restTimer!!.cancel()
            restProgress = 0
        }
        super.onDestroy()
    }

    private fun setRestProgressBar(){
        progressBar.progress = restProgress
        restTimer = object: CountDownTimer(12000,1000){
            override fun onTick(millisUntilFinished: Long) {
                restProgress++
                progressBar.progress = 12-restProgress
                tvTimer.text = (12-restProgress).toString()
            }

            override fun onFinish() {
               setupExerciseView()
            }
        }.start()
    }

    private fun setExerciseProgressBar(){
        tvExerciseProgressBar.progress = exerciseProgress
        exerciseTimer = object: CountDownTimer(32000,1000){
            override fun onTick(millisUntilFinished: Long) {
                exerciseProgress++
                tvExerciseProgressBar.progress = 32-exerciseProgress
                tvExerciseTimer.text = (32-exerciseProgress).toString()
            }

            override fun onFinish() {
                     Toast.makeText(this@ExerciseActivity,"Let's Start",Toast.LENGTH_SHORT).show()
            }
        }.start()
    }
    private fun setupExerciseView(){
         llRestView.visibility = View.GONE
        llExerciseView.visibility  = View.VISIBLE

        if(exerciseTimer != null){
            exerciseTimer!!.cancel()
            exerciseProgress = 0
        }
        setExerciseProgressBar()
    }

    private fun setupRestView(){
           if(restTimer != null){
               restTimer!!.cancel()
               restProgress = 0
           }
           setRestProgressBar()
       }
}