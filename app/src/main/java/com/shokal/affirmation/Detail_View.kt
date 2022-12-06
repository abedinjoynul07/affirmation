package com.shokal.affirmation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.ContactsContract.CommonDataKinds.Email
import android.widget.ImageView
import android.widget.TextView
lateinit var binding
class Detail_View : AppCompatActivity() {
    private lateinit var imageView: ImageView
    private lateinit var textName : TextView
    private lateinit var textEmail: TextView
    private lateinit var textBloodGroup : TextView
    private lateinit var textId : TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_view)

    }
}