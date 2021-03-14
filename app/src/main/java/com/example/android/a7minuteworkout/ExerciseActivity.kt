package com.example.android.a7minuteworkout

import android.media.MediaPlayer
import android.os.Bundle
import android.os.CountDownTimer
import android.speech.tts.TextToSpeech
import android.util.Log
import android.view.View
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_exercise.*
import java.lang.Exception
import java.util.*
import kotlin.collections.ArrayList

class ExerciseActivity : AppCompatActivity() , TextToSpeech.OnInitListener {

    private var restTimer: CountDownTimer? = null
    private var restProgress = 1

    private var exerciseTimer: CountDownTimer? = null
    private var exerciseProgress = 1


    private var exerciseList: ArrayList<ExerciseModel>? = null
    private var currentExercisePosition = -1

    private var tts: TextToSpeech? = null

    private var player: MediaPlayer? = null

    private var exerciseAdapter: ExerciseStatusAdapter? = null



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_exercise)



        setSupportActionBar(toolbar_exercise_activity)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        toolbar_exercise_activity.setNavigationOnClickListener {
            onBackPressed()
        }
        tts = TextToSpeech(this,this)


        exerciseList = Constants.defaultExerciseList()
        setupRestView()

          setupExerciseStatus()

    }

    override fun onDestroy() {
        if(restTimer!=null){
            restTimer!!.cancel()
            restProgress = 0
        }
        if(exerciseTimer!=null) {
            exerciseTimer!!.cancel()
            exerciseProgress = 0
        }
        if(tts!= null){
          tts!!.stop()
          tts!!.shutdown()
        }
        if(player!=null){
            player!!.stop()
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

        speakOut(exerciseList!![currentExercisePosition].getName())
        setExerciseProgressBar()

        tvImageView.setImageResource(exerciseList!![currentExercisePosition].getImage())
        tv_exercise_name.text = exerciseList!![currentExercisePosition].getName()

    }

    private fun setupRestView(){
        try {
            player = MediaPlayer.create(applicationContext,R.raw.audio)
            player!!.isLooping = false
            player!!.start()
        }catch (e: Exception){
            e.printStackTrace()
        }


        llRestView.visibility = View.VISIBLE
        llExerciseView.visibility = View.GONE



           if(restTimer != null){
               restTimer!!.cancel()
               restProgress = 0
           }
           setRestProgressBar()

        tvUpcomingExerciseName.text = exerciseList!![currentExercisePosition+1].getName()
       }

    override fun onInit(status: Int) {
         if(status == TextToSpeech.SUCCESS){
             val result = tts!!.setLanguage(Locale.US)
             if(result == TextToSpeech.LANG_MISSING_DATA || result == TextToSpeech.LANG_NOT_SUPPORTED){
                 Log.e("TTS","The Language Specified is not Supported")
             }else{
                 Log.e("TTS","Initialization Failed")
             }
         }

    }
   private fun speakOut(text: String){
        tts!!.speak(text,TextToSpeech.QUEUE_FLUSH,null,"")

    }

    private fun setupExerciseStatus(){
        rvExerciseStatus.layoutManager = LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,
                false)

        exerciseAdapter = ExerciseStatusAdapter(exerciseList!!,this)
        rvExerciseStatus.adapter = exerciseAdapter

    }



}


