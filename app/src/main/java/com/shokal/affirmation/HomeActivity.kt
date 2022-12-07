package com.shokal.affirmation

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class HomeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        val userList=findViewById<Button>(R.id.user_list)
        val email= intent.getStringExtra("email")

        userList.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            intent.putExtra("email", email)
            startActivity(intent)
        }

    }
}