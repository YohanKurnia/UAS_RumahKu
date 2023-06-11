package id.ac.umn.rumahku

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.firebase.ui.database.FirebaseRecyclerAdapter
import com.firebase.ui.database.FirebaseRecyclerOptions
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.StorageReference
import com.google.firebase.storage.ktx.storage

class RumahKuAdapter(options: FirebaseRecyclerOptions<Rumah>) : FirebaseRecyclerAdapter<Rumah, RumahKuAdapter.rumahViewHolder>(
    options
) {
    private lateinit var storage: StorageReference
    private lateinit var database: DatabaseReference

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): rumahViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.rumah_ku_list, parent, false)
        return rumahViewHolder(view)
    }

    override fun onBindViewHolder(holder: rumahViewHolder, position: Int, model: Rumah) {
        storage = Firebase.storage.reference.child("gambarRumah").child("${model.key}.jpg")
        holder.alamat.text = model.alamat
        Glide.with(holder.itemView).load(storage).into(holder.gambar)

        holder.ubah.setOnClickListener {
            val navController = it.findNavController()
            val bundle = Bundle()
            bundle.putString("rumahKuKey", model.key)
            navController.navigate(R.id.action_rumahKuFragment_to_editRumahFragment, bundle)
        }
        holder.hapus.setOnClickListener {
            val dialog: AlertDialog? = it?.let {
                val builder = AlertDialog.Builder(it.context)
                builder.setMessage("Anda yakin akan menghapus data ini?")
                builder.apply {
                    setPositiveButton("OK") { _, _ ->
                        database = FirebaseDatabase.getInstance().reference.child("rumah").child(model.key!!)
                        database.removeValue()
                        storage.delete()
                    }
                    setNegativeButton("BATAL"){dialog,_-> dialog.cancel()}
                }
                builder.create()
            }
            dialog?.show()
        }
    }

    class rumahViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val gambar = itemView.findViewById<ImageView>(R.id.listKuGambar)
        val alamat = itemView.findViewById<TextView>(R.id.listKuAlamat)
        val ubah = itemView.findViewById<Button>(R.id.btnUbah)
        val hapus = itemView.findViewById<Button>(R.id.btnHapus)
    }
}