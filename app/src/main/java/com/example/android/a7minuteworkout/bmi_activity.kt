package com.example.android.a7minuteworkout

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_bmi_activity.*
import java.math.BigDecimal
import java.math.RoundingMode

class bmi_activity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_bmi_activity)
        
        
        setSupportActionBar(toolbar_bmi_activity)

        val actionbar = supportActionBar
        if(actionbar!=null){
            actionbar.setDisplayHomeAsUpEnabled(true)
            actionbar.title = "CALCULATE BMI"
        }
        toolbar_bmi_activity.setNavigationOnClickListener {
            onBackPressed()
        }
        btnCalculateUnits.setOnClickListener {
            if(validateMetricUnits()){

                val heightValue : Float = etMetricUnitHeight.text.toString().toFloat()/100   //divided 100 to covert it into metre
                val weightValue : Float = etMetricUnitWeight.text.toString().toFloat()

                val bmi = weightValue /(heightValue*heightValue)
                displayBMIResult(bmi)

            }else{
                Toast.makeText(this@bmi_activity , "Please enter valid values." , Toast.LENGTH_LONG).show()
            }
        }

    }
    private fun displayBMIResult(bmi: Float){
       val bmiLabel : String
       val bmiDescription: String

       if(bmi.compareTo(15f)<=0){
           bmiLabel = "Very severely underweight"
           bmiDescription = "Oops! You really need to take better care of yourself! Eat more!"
       }else if(bmi.compareTo(15f) > 0 && bmi.compareTo(16f)<=0){
           bmiLabel = "Severely underweight"
           bmiDescription = "Oops! You really need to take better care of yourself! Eat more!"
       }else if(bmi.compareTo(16f) >0 && bmi.compareTo(18.5f)<=0){
           bmiLabel = "Underweight"
           bmiDescription = "Oops! You really need to take better care of yourself! Eat more!"
       }else if(bmi.compareTo(18.5f) >0 && bmi.compareTo(25f)<=0) {
           bmiLabel = "Normal"
           bmiDescription = "Congratulations! You are in a good shape!"
       }else if(bmi.compareTo(25f) >0 && bmi.compareTo(30f)<=0) {
           bmiLabel = "Normal"
           bmiDescription = "Congratulations! You are in a good shape!"
       }else if(bmi.compareTo(30f) >0 && bmi.compareTo(35f)<=0) {
           bmiLabel = "Normal"
           bmiDescription = "Congratulations! You are in a good shape!"
       }else if(bmi.compareTo(35f) >0 && bmi.compareTo(40f)<=0) {
           bmiLabel = "Normal"
           bmiDescription = "Congratulations! You are in a good shape!"
       }else{
           bmiLabel = "obese Class |||| ( Very Severely obese)"
           bmiDescription = "OMG! You are in a very dangerous condition! Act now!"
       }
        tvBMIType.visibility = View.VISIBLE
        tvBMIValue.visibility = View.VISIBLE
        tvYourBMI.visibility = View.VISIBLE
        tvBMIDescription.visibility = View.VISIBLE

        val bmiValue = BigDecimal(bmi.toDouble()).setScale(2,RoundingMode.HALF_EVEN).toString()

        tvBMIValue.text = bmiValue
        tvBMIType.text = bmiLabel
        tvBMIDescription.text = bmiDescription
    }

       private fun  validateMetricUnits(): Boolean{
           var isValid = true

           if(etMetricUnitWeight.text.toString().isEmpty())
               isValid = false
           if (etMetricUnitHeight.text.toString().isEmpty())
               isValid = false

           return isValid
       }

}