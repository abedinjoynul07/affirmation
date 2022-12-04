package com.shokal.affirmation.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.shokal.affirmation.R
import com.shokal.affirmation.model.User

class ItemAdapter(
    private val context: Context, private val dataset: List<User>
) : RecyclerView.Adapter<ItemAdapter.ItemViewHolder>() {
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
        holder.textName.text = item.name
        holder.textEmail.text = item.email
        holder.textBloodGroup.text = item.bloodGroup
        holder.textId.text = item.id.toString()
        val imageId = context.resources.getIdentifier(item.image, "drawable", context.packageName)
        holder.image.setImageResource(imageId)
    }

    override fun getItemCount() = dataset.size
}