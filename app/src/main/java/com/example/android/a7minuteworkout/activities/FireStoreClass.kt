package com.example.android.a7minuteworkout.activities

import android.app.Activity
import android.util.Log
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.SetOptions

class FireStoreClass {

    private val mFireStore = FirebaseFirestore.getInstance()

    fun  registerUser(activity: SignUp , userInfo: User){
        mFireStore.collection(Constant.USERS)
            .document(getCurrentUserId())
            .set(userInfo , SetOptions.merge())
            .addOnSuccessListener {
                activity.userRegisteredSuccess()
            }.addOnFailureListener {
                    e->
                Log.e(activity.javaClass.simpleName,"Something went Wrong")
            }
    }

    fun getCurrentUserId(): String {
        //  return FirebaseAuth.getInstance().currentUser!!.uid
        // In Alternative we check whether the Current User is empty or not
        val currentUser = FirebaseAuth.getInstance().currentUser
        var currentUserID = " "
        if(currentUser != null){
            currentUserID = currentUser.uid
        }
        return currentUserID
    }

    fun loadUserData(activity: Activity){

        mFireStore.collection(Constant.USERS)
            .document(getCurrentUserId())
            .get()
            .addOnSuccessListener { document ->
                val loggedInUser = document.toObject(User::class.java)

                when(activity){
                    is SignIn ->{
                        activity.signInSuccess(loggedInUser)
                    }


                }

            }.addOnFailureListener {
                    e->
                when(activity){
                    is SignIn ->{
                        activity.hideProgressDialog()
                    }
                    is MainActivity ->{
                       activity.hideProgressDialog()
                    }

                }

                Log.e("SignInSuccess","Something went Wrong")
            }
    }


}