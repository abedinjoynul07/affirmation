package com.shokal.affirmation

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.shokal.affirmation.databinding.ActivityLoginBinding
import com.shokal.affirmation.databinding.ActivityRegistrationBinding

lateinit var regBinding : ActivityRegistrationBinding
lateinit var regFirebaseAuth: FirebaseAuth
lateinit var database : FirebaseFirestore
class RegistrationActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        regBinding = ActivityRegistrationBinding.inflate(layoutInflater)
        setContentView(regBinding.root)

        regBinding.registrationSubmitButton.setOnClickListener {



            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
        }

        regBinding.loginIntentButton.setOnClickListener {
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
        }
    }
}