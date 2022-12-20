package com.shokal.affirmation

import android.app.ProgressDialog
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import com.google.firebase.auth.FirebaseAuth
import com.shokal.affirmation.databinding.ActivityLoginBinding
import com.shokal.affirmation.model.User

lateinit var binding: ActivityLoginBinding
lateinit var firebaseAuth: FirebaseAuth
lateinit var sharedPreferences : SharedPreferences
class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
        sharedPreferences = getSharedPreferences("preferences", MODE_PRIVATE)
        progressBar = ProgressDialog(this)
        progressBar.setCancelable(false)
        progressBar.setMessage("Loading..")
        firebaseAuth = FirebaseAuth.getInstance()

        val email = sharedPreferences.getString("email", null)
        val password = sharedPreferences.getString("password", null)
        Log.d("Login", email.toString())
        if(FirebaseAuth.getInstance().isSignInWithEmailLink(email.toString())){
            startActivity(Intent(this, HomeActivity::class.java))
        }else{
            if (email != null && password != null) {
                val intent = Intent(this, HomeActivity::class.java)
                intent.putExtra("email", email)
                startActivity(intent)
            }
        }
        authUser()
        binding.loginButton.setOnClickListener {
                progressBar.show()
                var email = binding.loginEmail.text.toString().trim()
                var password = binding.loginPassword.text.toString().trim()
                Log.d("Login", email)
                firebaseAuth.signInWithEmailAndPassword(email, password)
                    .addOnSuccessListener {
                        val editor = sharedPreferences.edit()
                        editor.putString("email", email)
                        // this should be salted
                        editor.putString("password", password)
                        editor.commit();
                        progressBar.dismiss()
                        val intent = Intent(this, HomeActivity::class.java)
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

    fun authUser(){
        val email = sharedPreferences.getString("email", null)
        val password = sharedPreferences.getString("password", null)
        Log.d("Login", email.toString())
        if(FirebaseAuth.getInstance().isSignInWithEmailLink(email.toString())){
            startActivity(Intent(this, HomeActivity::class.java))
        }else{
            if (email != null && password != null) {
                val intent = Intent(this, HomeActivity::class.java)
                intent.putExtra("email", email)
                startActivity(intent)
            }
        }
    }
}
