package com.shokal.affirmation

import android.app.ProgressDialog
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.FirebaseFirestore
import com.shokal.affirmation.adapter.ItemAdapter
import com.shokal.affirmation.model.User

class MainActivity : AppCompatActivity() {

    private lateinit var adapter: ItemAdapter
    private lateinit var progressBar: ProgressDialog
    private val list = mutableListOf<User>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        progressBar = ProgressDialog(this)
        progressBar.setCancelable(false)
        progressBar.setMessage("Fetching Data..")
        progressBar.show()

        val recyclerView = findViewById<RecyclerView>(R.id.recyclerView)
        recyclerView.setHasFixedSize(true)
        adapter = ItemAdapter(this, list)
        recyclerView.adapter = adapter
        fethcData()

        val swipeRefresh: SwipeRefreshLayout = findViewById(R.id.swipeLayout)
        swipeRefresh.setOnRefreshListener {
            fethcData()
            swipeRefresh.isRefreshing = false
        }
    }

    fun fethcData() {
        list.clear()
        val db = FirebaseFirestore.getInstance()
        db.collection("Users")
            .orderBy("name", com.google.firebase.firestore.Query.Direction.ASCENDING).get()
            .addOnSuccessListener {
                val documents = it.documents
                for (d: DocumentSnapshot in documents) {
                    val model: User? = d.toObject(User::class.java)
                    if (model != null) {
                        list.add(model)
                    }
                }
                adapter.notifyDataSetChanged()

                if (progressBar.isShowing) {
                    progressBar.dismiss()
                }
            }
    }

}
