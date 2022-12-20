package com.shokal.affirmation

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.shokal.affirmation.databinding.ActivityMailBinding
lateinit var mailBinding: ActivityMailBinding
class MailActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mailBinding = ActivityMailBinding.inflate(layoutInflater)
        setContentView(mailBinding.root)
        val email = intent.getStringExtra("email")
        mailBinding.mailTo.setText(email)
//        mailBinding.mailTo.isEnabled = false

        mailBinding.sendMailButton.setOnClickListener {
            var subject = mailBinding.mailSub.text.toString()
            var mailBody = mailBinding.mailBody.text.toString()
            Log.d("emailData", email.toString())
            Log.d("emailData", subject)
            Log.d("emailData", mailBody)
//            val intent = Intent(Intent.ACTION_SEND)
//            intent.data = Uri.parse("mailto:")
//            intent.type = "text/plain"
//            intent.putExtra(Intent.EXTRA_EMAIL, arrayOf(email?.trim()))
//            intent.putExtra(Intent.EXTRA_SUBJECT, subject)
//            intent.putExtra(Intent.EXTRA_TEXT, mailBody)
//            it.context.startActivity(intent)

//            val intent1 = new Intent (this, LoginActivity::class.java)
//            startActivity(intent1)
            val intent = Intent(Intent.ACTION_SEND_MULTIPLE)
            intent.data = Uri.parse("mailto:")
            intent.type = "text/plain"
            intent.putExtra(Intent.EXTRA_EMAIL, arrayOf(email))
            intent.putExtra(Intent.EXTRA_SUBJECT, subject)
            intent.putExtra(Intent.EXTRA_TEXT, mailBody)
            it.context.startActivity(intent)
        }
    }
}