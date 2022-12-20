package com.shokal.affirmation

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.google.firebase.firestore.FirebaseFirestore
import com.shokal.affirmation.databinding.ActivityViewProfileBinding
import com.shokal.affirmation.model.User

lateinit var profileBinding: ActivityViewProfileBinding
lateinit var userName : String
lateinit var userEmail: String
lateinit var userdob : String
lateinit var userPhone : String
lateinit var userImg : String
class ViewProfile : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        profileBinding = ActivityViewProfileBinding.inflate(layoutInflater)
        setContentView(profileBinding.root)

        val user = FirebaseAuth.getInstance().currentUser
        val userId = user?.uid
        val database = FirebaseFirestore.getInstance().collection("Users")
        val data = userId?.let { database.document(it) }

        if (data != null) {
            Log.d("Data",data.get().toString())
        }

//        data.addValueEventListener(object:ValueEventListener{
//            override fun onDataChange(snapshot: DataSnapshot) {
//                userName = userId?.let { snapshot.child(it).child("name").value }.toString()
//                userEmail = userId?.let { snapshot.child(it).child("email").value }.toString()
//                userdob = userId?.let { snapshot.child(it).child("dateOfBirth").value }.toString()
//                userPhone = userId?.let { snapshot.child(it).child("mobile").value }.toString()
//                userImg = userId?.let { snapshot.child(it).child("image").value }.toString()
//            }
//
//            override fun onCancelled(error: DatabaseError) {
//
//            }
//
//        })

//        profileBinding.name.text = data.get(
        profileBinding.email.text = userEmail
        profileBinding.dob.text = userdob
        profileBinding.phone.text = userPhone
        profileBinding.image.setImageResource(resources.getIdentifier(userImg, "drawable", packageName))


//            dob.text = intent.getStringExtra("dob").toString()
//            phone.text = intent.getStringExtra("phone").toString()

//        profileBinding.name.text = intent.getStringArrayExtra("user").toString()
//        profileBinding.email.text = intent.getStringArrayExtra("user").toString()
//        profileBinding.email.text = userId?.let { snapshot.child(it).child("email").value.toString() }
//        profileBinding.dob.text = userId?.let { snapshot.child(it).child("dateOfBirth").value.toString() }
//        profileBinding.phone.text = userId?.let { snapshot.child(it).child("mobile").value.toString() }
//        val imageName = userId?.let { snapshot.child(it).child("image").value.toString() }
//        profileBinding.image.setImageResource(resources.getIdentifier(imageName, "drawable", packageName))


    }
}
