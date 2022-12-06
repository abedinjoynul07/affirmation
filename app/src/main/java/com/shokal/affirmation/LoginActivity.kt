package com.shokal.affirmation

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.shokal.affirmation.databinding.ActivityLoginBinding

lateinit var binding: ActivityLoginBinding
lateinit var firebaseAuth: FirebaseAuth
class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        firebaseAuth = FirebaseAuth.getInstance()
        var email = binding.loginEmail.toString().trim()
        var password = binding.loginPassword.toString().trim()


        binding.loginButton.setOnClickListener {
            firebaseAuth.signInWithEmailAndPassword(email, password)
                .addOnSuccessListener {
                    val intent = Intent(this, MainActivity::class.java)
                    intent.putExtra("email", email)
                    startActivity(intent)
                }
                .addOnFailureListener{
                    Toast.makeText(this, "Login Failed", Toast.LENGTH_SHORT)
                }
        }
        binding.registrationIntentButton.setOnClickListener {
            val intent = Intent(this, RegistrationActivity::class.java)
            startActivity(intent)
        }
    }
}