package com.shokal.affirmation

import android.content.Intent
import android.graphics.BitmapFactory
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.shokal.affirmation.databinding.ActivityDetailViewBinding
import com.squareup.picasso.Picasso
import java.net.URL

private lateinit var detailViewBinding: ActivityDetailViewBinding

class Detail_View : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        detailViewBinding = ActivityDetailViewBinding.inflate(layoutInflater)
        setContentView(detailViewBinding.root)

        val email = intent.getStringExtra("email")
        detailViewBinding.cardName.text = intent.getStringExtra("name")
        Picasso.get().load(intent.getStringExtra("image")).into(detailViewBinding.imageView)
        detailViewBinding.cardEmail.text = email
        detailViewBinding.cardId.text = intent.getStringExtra("dob")
        detailViewBinding.cardBloodGroup.text = intent.getStringExtra("mobile")

        detailViewBinding.buttonMail.setOnClickListener {
            val intent = Intent(this, MailActivity::class.java)
            intent.putExtra("email", email)
            startActivity(intent)
        }
    }
}