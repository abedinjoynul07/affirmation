package com.shokal.affirmation

import android.app.ProgressDialog
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.google.firebase.auth.FirebaseAuth
import com.shokal.affirmation.`interface`.ApiInterface
import com.shokal.affirmation.adapter.ItemAdapter
import com.shokal.affirmation.model.JsonPlaceholderItem
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {
    private var isLinearLayoutManager = true
    private lateinit var adapter: ItemAdapter
    private lateinit var progressBar: ProgressDialog
    private var list: MutableList<JsonPlaceholderItem> = mutableListOf<JsonPlaceholderItem>()
    private lateinit var recyclerView : RecyclerView
    val TAG = "retro"
    private val base_url = "https://jsonplaceholder.typicode.com/"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        progressBar = ProgressDialog(this)
        progressBar.setCancelable(false)
        progressBar.setMessage("Fetching Data...")
        progressBar.show()

        recyclerView = findViewById(R.id.recyclerView)
        recyclerView.setHasFixedSize(true)
        adapter = ItemAdapter(this, list)
        recyclerView.adapter = adapter


        chooseLayout()
        fetchData()

        val swipeRefresh: SwipeRefreshLayout = findViewById(R.id.swipeLayout)
        swipeRefresh.setOnRefreshListener {
            fetchData()
            swipeRefresh.isRefreshing = false
        }
    }

    private fun fetchData() {
        list.clear()

//        Firebase Code

//        val db = FirebaseFirestore.getInstance()
//        db.collection("Users")
//            .orderBy("name", com.google.firebase.firestore.Query.Direction.ASCENDING).get()
//            .addOnSuccessListener {
//                val documents = it.documents
//                Log.d("Data", documents.toString())
//                for (data: DocumentSnapshot in documents) {
//                    val model: User? = data.toObject(User::class.java)
//                    if (model != null) {
//                        list.add(model)
//                    }
//                }
//                adapter.notifyDataSetChanged()
//
//                if (progressBar.isShowing) {
//                    progressBar.dismiss()
//                }
//            }


        val retrofit = Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(base_url)
            .build()
            .create(ApiInterface::class.java)

        val retrofitData = retrofit.getData()

        retrofitData.enqueue(object : Callback<List<JsonPlaceholderItem>?> {
            override fun onResponse(
                call: Call<List<JsonPlaceholderItem>?>,
                response: Response<List<JsonPlaceholderItem>?>
            ) {
                val responseData = response.body()!!

                list = responseData.toList() as MutableList<JsonPlaceholderItem>
//                for(item in responseData){
//                    Log.d(TAG, item.title)
//                    list.add(item)
//                }

                if (progressBar.isShowing) {
                    progressBar.dismiss()
                }
                Log.d(TAG, list[0].thumbnailUrl)
            }

            override fun onFailure(call: Call<List<JsonPlaceholderItem>?>, t: Throwable) {
                TODO("Not yet implemented")
            }
        })
    }

    private fun chooseLayout() {
        if (isLinearLayoutManager) {
            recyclerView.layoutManager = LinearLayoutManager(this)
        } else {
            recyclerView.layoutManager = GridLayoutManager(this, 2)
        }
        recyclerView.adapter = ItemAdapter(this, list)
    }

    private fun setIcon(menuItem: MenuItem?) {
        if (menuItem == null)
            return
        menuItem.icon =
            if (isLinearLayoutManager)
                ContextCompat.getDrawable(this, R.drawable.outline_apps_24)
            else ContextCompat.getDrawable(this, R.drawable.outline_view_list_24)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_layout, menu)
        val layoutButton = menu.findItem(R.id.action_switch_layout)
        setIcon(layoutButton)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_switch_layout -> {
                isLinearLayoutManager = !isLinearLayoutManager
                chooseLayout()
                setIcon(item)

                return true
            }
            R.id.action_logout -> {
                FirebaseAuth.getInstance().signOut()
                startActivity(Intent(this, LoginActivity::class.java))
                finish()
                val sharedPreferences: SharedPreferences = getSharedPreferences("preferences", MODE_PRIVATE)
                val editor = sharedPreferences.edit()
                Log.d("Login", editor.toString())
                editor.clear()
                editor.commit()
                editor.apply()
                return true
            }
            R.id.action_email -> {
                val intent = Intent(this, MailActivity::class.java)
                startActivity(intent)
                return true
            }
            R.id.action_text -> {
                Toast.makeText(this, "Test", Toast.LENGTH_SHORT).show()

                return true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

//    private fun filter(text: String) {
//        val filteredlist: MutableList<User> = mutableListOf()
//        for (item in list) {
//            if (item.name?.contains(text.toLowerCase()) == true) {
//                filteredlist.add(item)
//            }
//        }
//        if (filteredlist.isEmpty()) {
//            Toast.makeText(this, "No Data Found..", Toast.LENGTH_SHORT).show()
//        } else {
//            adapter = ItemAdapter(this, filteredlist)
//
//        }
//    }
}
