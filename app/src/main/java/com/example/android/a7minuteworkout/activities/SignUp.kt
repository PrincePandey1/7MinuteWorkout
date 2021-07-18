package com.example.android.a7minuteworkout.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.widget.Toast
import com.example.android.a7minuteworkout.R
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import kotlinx.android.synthetic.main.activity_sign_up.*

class SignUp : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)

        setupActionBar()
        btn_Sign_Up.setOnClickListener {
            registerUser()
        }

    }
    private fun registerUser(){   // It trims the excessive space enter by the user in email,name & password
        val name: String = et_name_sign_up.text.toString().trim{ it <= ' '}
        val email: String = et_email_sign_up.text.toString().trim{ it <= ' '}
        val password: String = et_password_signUp.text.toString().trim{ it <= ' '}

        if (validateForm(name, email, password)){
            showProgressDialog("Please wait...")
            FirebaseAuth.getInstance().createUserWithEmailAndPassword(email,password).addOnCompleteListener {

                    task ->
                if (task.isSuccessful) {
                    val firebaseUser : FirebaseUser = task.result!!.user!!
                    val registeredEmail = firebaseUser.email!!
                     Toast.makeText(this, "$name you have successfully registered ", Toast.LENGTH_LONG).show()
                      FirebaseAuth.getInstance().signOut()
                     finish()
                    val user = User(firebaseUser.uid , name , registeredEmail)
                    FireStoreClass().registerUser(this , user)

                } else {
                    Toast.makeText(this,
                        task.exception!!.message,
                        Toast.LENGTH_SHORT).show()
                }
            }
        }}

    private fun validateForm(name: String, email: String, password:String): Boolean{

        return when{
            TextUtils.isEmpty(name)->{
                showErrorSnackBar("Please enter a name")
                false
            }
            TextUtils.isEmpty(email)->{
                showErrorSnackBar("Please enter a email")
                false
            }
            TextUtils.isEmpty(password)->{
                showErrorSnackBar("Please enter a Password")
                false
            }
            else -> {
                return true
            }
        }
    }
    private fun setupActionBar() {
        setSupportActionBar(toolbar_sign_up_activity)

        val actionBar = supportActionBar
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true)
            actionBar.setHomeAsUpIndicator(R.drawable.ic_back)
        }
        toolbar_sign_up_activity.setNavigationOnClickListener {
            onBackPressed()
        }

    }

    fun userRegisteredSuccess() {
        Toast.makeText(this, " You have successfully registered ", Toast.LENGTH_LONG).show()
        hideProgressDialog()
        FirebaseAuth.getInstance().signOut()
        finish()
    }

}