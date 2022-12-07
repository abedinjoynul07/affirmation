package com.shokal.affirmation

import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class view_profile : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_view_profile)

        val image=findViewById<ImageView>(R.id.profile_image)
        val name=findViewById<TextView>(R.id.name)
        val email=findViewById<TextView>(R.id.email)
        val dob=findViewById<TextView>(R.id.dob)
        val phone=findViewById<TextView>(R.id.phone)

        val imageId=this.resources.getIdentifier(intent.getStringExtra("image"),"drawable",this.packageName)
        image.setImageResource(imageId)
        name.text = intent.getStringExtra("name").toString()
        email.text = intent.getStringExtra("email").toString()
        dob.text = intent.getStringExtra("dob").toString()
        phone.text = intent.getStringExtra("phone").toString()

    }
}