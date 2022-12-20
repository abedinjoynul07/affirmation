package com.shokal.affirmation.adapter

import android.content.Context
import android.content.Intent
import android.graphics.BitmapFactory
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.shokal.affirmation.Detail_View
import com.shokal.affirmation.R
import com.shokal.affirmation.model.JsonPlaceholderItem
import com.squareup.picasso.Picasso
import java.net.URL


class ItemAdapter(
    private val context: Context, private val dataset: List<JsonPlaceholderItem>
) : RecyclerView.Adapter<ItemAdapter.ItemViewHolder>() {

    val TAG = "retro"
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
//        holder.textName.text = "Name: " + item.name
//        holder.textEmail.text = "Email: " + item.email
//        holder.textBloodGroup.text = "BG: " + item.mobile
//        holder.textId.text = "ID: " + item.id
//        val imageId = context.resources.getIdentifier(item.image, "drawable", context.packageName)

        Log.d(TAG, dataset.size.toString())
        holder.textName.text = "Name: " + item.id
        holder.textEmail.text = "Email: " + item.title
        holder.textBloodGroup.text = "BG: " + item.albumId
        holder.textId.text = "ID: " + item.url
        Log.d(TAG, URL(item.thumbnailUrl).toString())

        Picasso.get().load(item.thumbnailUrl).into(holder.image)
//        val url = URL(item.thumbnailUrl)
//        Log.d(TAG, url.toString())
//        val bmp = BitmapFactory.decodeStream(url.openConnection().getInputStream())
//        holder.image.setImageBitmap(bmp)

//        holder.image.setOnClickListener {
//            val intent = Intent(context, Detail_View::class.java)
//            intent.putExtra("name", item.name)
//            intent.putExtra("image", item.image)
//            intent.putExtra("dob", item.dateOfBirth)
//            intent.putExtra("email", item.email)
//            intent.putExtra("mobile", item.mobile)
//
//            context.startActivity(intent)
//        }


        holder.image.setOnClickListener {
            val intent = Intent(context, Detail_View::class.java)
            intent.putExtra("name", item.title)
            intent.putExtra("dob", item.albumId)
            intent.putExtra("email", item.id)
            intent.putExtra("mobile", item.url)
            intent.putExtra("image", item.thumbnailUrl)
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