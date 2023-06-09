package com.example.rumahku

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.NonNull
import androidx.cardview.widget.CardView
import androidx.databinding.DataBindingUtil
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.firebase.ui.database.FirebaseRecyclerAdapter
import com.firebase.ui.database.FirebaseRecyclerOptions
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.StorageReference
import com.google.firebase.storage.ktx.storage

class RumahAdapter(options: FirebaseRecyclerOptions<Rumah>) : FirebaseRecyclerAdapter<Rumah, RumahAdapter.rumahViewHolder>(
    options
) {
    private lateinit var storage: StorageReference

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): rumahViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.rumah_list, parent, false)
        return rumahViewHolder(view)
    }

    override fun onBindViewHolder(holder: rumahViewHolder, position: Int, model: Rumah) {
        storage = Firebase.storage.reference.child("gambarRumah").child("${model.key}.jpg")
        holder.alamat.text = model.alamat
        Glide.with(holder.itemView).load(storage).into(holder.gambar)
        holder.card.setOnClickListener {
            val navController = it.findNavController()
            val bundle = Bundle()
            bundle.putString("rumahKey", model.key)
            navController.navigate(R.id.action_mainFragment_to_rumahFragment, bundle)
        }
    }

    class rumahViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val gambar = itemView.findViewById<ImageView>(R.id.listGambar)
        val alamat = itemView.findViewById<TextView>(R.id.listAlamat)
        val card = itemView.findViewById<CardView>(R.id.listCard)
    }
}