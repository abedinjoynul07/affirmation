package com.shokal.affirmation

import android.app.ProgressDialog
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.FirebaseFirestore
import com.shokal.affirmation.adapter.ItemAdapter
import com.shokal.affirmation.model.User
import com.shokal.affirmation.model.ViewUserModel

class MainActivity : AppCompatActivity() {
    private lateinit var adapter: ItemAdapter
    private lateinit var progressBar: ProgressDialog
    private var list: MutableList<User> = mutableListOf<User>()
    private lateinit var viewModel: ViewUserModel

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
        fetchData()


        val swipeRefresh: SwipeRefreshLayout = findViewById(R.id.swipeLayout)
        swipeRefresh.setOnRefreshListener {
            fetchData()
            swipeRefresh.isRefreshing = false
        }
    }

    private fun fetchData() {
        list.clear()
        val db = FirebaseFirestore.getInstance()
        db.collection("Users")
            .orderBy("name", com.google.firebase.firestore.Query.Direction.ASCENDING).get()
            .addOnSuccessListener {
                val documents = it.documents
                Log.d("Data", documents.toString())
                for (data: DocumentSnapshot in documents) {
                    val model: User? = data.toObject(User::class.java)
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
