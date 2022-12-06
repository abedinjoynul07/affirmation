package com.shokal.affirmation.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.shokal.affirmation.Detail_View
import com.shokal.affirmation.R
import com.shokal.affirmation.model.User

class ItemAdapter(
    private val context: Context, private val dataset: List<User>
) : RecyclerView.Adapter<ItemAdapter.ItemViewHolder>() {

//    private val arrayList = ArrayList<User>()

    class ItemViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val textName: TextView = view.findViewById(R.id.cardName)
        val textEmail: TextView = view.findViewById(R.id.cardEmail)
        val textBloodGroup: TextView = view.findViewById(R.id.cardBloodGroup)
        val textId: TextView = view.findViewById(R.id.cardId)
        val image: ImageView = view.findViewById(R.id.imageView)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val adapterLayout =
            LayoutInflater.from(parent.context).inflate(R.layout.card_view, parent, false)
        return ItemViewHolder(adapterLayout)
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val item = dataset[position]
        holder.textName.text = "Name: " + item.name
        holder.textEmail.text = "Email: " + item.email
        holder.textBloodGroup.text = "BG: " + item.mobile
        holder.textId.text = "ID: " + item.id
        val imageId = context.resources.getIdentifier(item.image, "drawable", context.packageName)

        holder.image.setImageResource(imageId)

        holder.image.setOnClickListener {
            val intent = Intent(context, Detail_View::class.java)
            intent.putExtra("name", item.name)

            context.startActivity(intent)
        }
//        val mImageRef: StorageReference = FirebaseStorage.getInstance().getReference("image/rabbi.png")
//        val ONE_MEGABYTE = (1024 * 1024).toLong()
//        mImageRef.getBytes(ONE_MEGABYTE)
//            .addOnSuccessListener {
//                val bm = BitmapFactory.decodeByteArray(it, 0, it.size)
//                holder.image.setImageBitmap(bm)
//
//            }
//        val imageUri = item.image
//
//        Log.d("Data", imageUri.toString())
//
//        Picasso.get().load(imageUri).into(holder.image)
    }

    override fun getItemCount() = dataset.size


//    fun updateUserlist(userList: List<User>){
//        this.arrayList.clear()
//        this.arrayList.addAll(userList)
//        notifyDataSetChanged()
//    }
}