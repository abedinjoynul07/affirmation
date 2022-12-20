package com.shokal.affirmation

import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.shokal.affirmation.databinding.ActivityHomeBinding

class HomeActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        val binding: ActivityHomeBinding
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val email= intent.getStringExtra("email")


        binding.userList.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            intent.putExtra("email", email)
            startActivity(intent)
        }

        binding.logout.setOnClickListener {
            logout()
        }

        binding.profile.setOnClickListener {
            val intent = Intent(this, ViewProfile::class.java)
            startActivity(intent)
        }

    }

    fun logout(){
        FirebaseAuth.getInstance().signOut()
        startActivity(Intent(this, LoginActivity::class.java))
        finish()
        val sharedPreferences: SharedPreferences = getSharedPreferences("preferences", MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        Log.d("Login", editor.toString())
        editor.clear()
        editor.commit()
        editor.apply()
    }
}