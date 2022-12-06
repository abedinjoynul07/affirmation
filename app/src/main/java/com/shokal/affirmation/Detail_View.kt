package com.shokal.affirmation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.ContactsContract.CommonDataKinds.Email
import android.view.LayoutInflater
import android.widget.ImageView
import android.widget.TextView
import com.shokal.affirmation.databinding.ActivityDetailViewBinding

private lateinit var detailViewBinding: ActivityDetailViewBinding
class Detail_View : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        detailViewBinding = ActivityDetailViewBinding.inflate(layoutInflater)
        setContentView(detailViewBinding.root)

        val image = this.resources.getIdentifier(intent.getStringExtra("image"), "drawable", this.packageName)
        detailViewBinding.cardName.text = intent.getStringExtra("name")
        detailViewBinding.imageView.setImageResource(image)
        detailViewBinding.cardEmail.text = intent.getStringExtra("email")
        detailViewBinding.cardId.text = intent.getStringExtra("dob")
        detailViewBinding.cardBloodGroup.text = intent.getStringExtra("mobile")
    }
}