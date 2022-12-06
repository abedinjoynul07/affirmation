package com.shokal.affirmation

import android.app.ProgressDialog
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.shokal.affirmation.databinding.ActivityLoginBinding

lateinit var binding: ActivityLoginBinding
lateinit var firebaseAuth: FirebaseAuth

class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
        var email = binding.loginEmail.text.toString().trim()
        var password = binding.loginPassword.text.toString().trim()

        progressBar = ProgressDialog(this)
        progressBar.setCancelable(false)
        progressBar.setMessage("Loading..")
        firebaseAuth = FirebaseAuth.getInstance()

        binding.loginButton.setOnClickListener {
            if (email == "" || email == null || password == "" || password == null){
                binding.loginEmail.error
                binding.loginPassword.error
                Toast.makeText(this, "Email and Password is required", Toast.LENGTH_SHORT)
            }
            progressBar.show()
            Log.d("Registration", email)
            firebaseAuth.signInWithEmailAndPassword(email, password).addOnSuccessListener {
                    progressBar.dismiss()
                    val intent = Intent(this, MainActivity::class.java)
                    intent.putExtra("email", email)
                    startActivity(intent)
                }.addOnFailureListener {
                progressBar.dismiss()
                    Toast.makeText(this, "Login Failed", Toast.LENGTH_SHORT)
                }
        }
        binding.registrationIntentButton.setOnClickListener {
            val intent = Intent(this, RegistrationActivity::class.java)
            startActivity(intent)
        }
    }
}