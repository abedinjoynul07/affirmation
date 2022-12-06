package com.shokal.affirmation

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.shokal.affirmation.databinding.ActivityRegistrationBinding

lateinit var regBinding : ActivityRegistrationBinding
lateinit var regFirebaseAuth: FirebaseAuth
lateinit var database : FirebaseFirestore
class RegistrationActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        regBinding = ActivityRegistrationBinding.inflate(layoutInflater)
        setContentView(regBinding.root)

        regFirebaseAuth = FirebaseAuth.getInstance()
        database = FirebaseFirestore.getInstance()

        regBinding.registrationSubmitButton.setOnClickListener {

            val email = regBinding.registrationEmail.text.toString().trim()
            val password = regBinding.registrationPassword.text.toString().trim()
            val dob = regBinding.dateOfBirth.text.toString().trim()
            val mobile = regBinding.registrationMobileNumber.text.toString().trim()
            val name = regBinding.registrationName.text.toString().trim()

            Log.d("Registration", email.toString())
            regFirebaseAuth.createUserWithEmailAndPassword(email, password)
                .addOnSuccessListener {
                    startActivity(Intent(this, LoginActivity::class.java))
                    firebaseAuth.uid?.let { it1 ->
                        database.collection("Users")
                            .document(it1)
                            .set(com.shokal.affirmation.model.User(name,email, dob, it1, mobile, "image"))
                    }
                }
                .addOnFailureListener {
                    Toast.makeText(this, "Registration Failed.", Toast.LENGTH_SHORT)
                }
        }
        regBinding.loginIntentButton.setOnClickListener {
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
        }
    }
}