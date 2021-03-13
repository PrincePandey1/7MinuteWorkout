package com.example.android.a7minuteworkout

import android.os.Bundle
import android.os.CountDownTimer
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_exercise.*

class ExerciseActivity : AppCompatActivity() {

    private var restTimer: CountDownTimer? = null
    private var restProgress = 1

    private var exerciseTimer: CountDownTimer? = null
    private var exerciseProgress = 1


    private var exerciseList: ArrayList<ExerciseModel>? = null
    private var currentExercisePosition = -1


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_exercise)

        setSupportActionBar(toolbar_exercise_activity)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        toolbar_exercise_activity.setNavigationOnClickListener {
            onBackPressed()
        }


        exerciseList = Constants.defaultExerciseList()
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
        restTimer = object: CountDownTimer(11000,1000){
            override fun onTick(millisUntilFinished: Long) {
                restProgress++
                progressBar.progress = 11-restProgress
                tvTimer.text = (11-restProgress).toString()

            }

            override fun onFinish() {
                currentExercisePosition++
               setupExerciseView()
            }
        }.start()

    }

    private fun setExerciseProgressBar(){
        tvExerciseProgressBar.progress = exerciseProgress
        exerciseTimer = object: CountDownTimer(31000,1000){
            override fun onTick(millisUntilFinished: Long) {
                exerciseProgress++
                tvExerciseProgressBar.progress = 31-exerciseProgress
                tvExerciseTimer.text = (31-exerciseProgress).toString()
            }

            override fun onFinish() {
                if(currentExercisePosition < exerciseList!!.size -1) {
                    setupRestView()
                }
               else {
                    Toast.makeText(this@ExerciseActivity,
                            "Congratulations You have completed 7 Minute Workout",
                            Toast.LENGTH_SHORT).show()
                }

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

        tvImageView.setImageResource(exerciseList!![currentExercisePosition].getImage())
        tv_exercise_name.text = exerciseList!![currentExercisePosition].getName()

    }

    private fun setupRestView(){

        llRestView.visibility = View.VISIBLE
        llExerciseView.visibility = View.GONE



           if(restTimer != null){
               restTimer!!.cancel()
               restProgress = 0
           }
           setRestProgressBar()

        tvUpcomingExerciseName.text = exerciseList!![currentExercisePosition+1].getName()
       }
}


