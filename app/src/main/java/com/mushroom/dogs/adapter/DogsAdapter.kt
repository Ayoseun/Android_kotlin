package com.mushroom.dogs.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.mushroom.dogs.R
import com.mushroom.dogs.model.DogsApi
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.dogs_rv_layout.view.*

class DogsAdapter(val context: Context?,private val dogsImages : ArrayList<DogsApi>): RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.dogs_rv_layout, parent, false)
        return ViewHolder(v)
    }

    override fun getItemCount(): Int {
        return dogsImages.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        Picasso.get().load(dogsImages[position].message).into(holder.itemView.dogImage)

    }

    class ViewHolder(v: View?) : RecyclerView.ViewHolder(v!!), View.OnClickListener {
        override fun onClick(v: View?) {

        }

        init {
            itemView.setOnClickListener(this)
        }

        val dogImage = itemView.dogImage!!
    }

}
