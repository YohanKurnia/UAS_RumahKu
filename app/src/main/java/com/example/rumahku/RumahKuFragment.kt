package com.example.rumahku

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.rumahku.databinding.FragmentRumahKuBinding
import com.firebase.ui.database.FirebaseRecyclerOptions
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class RumahKuFragment : Fragment() {
    lateinit var database: DatabaseReference
    lateinit var adapter: RumahKuAdapter
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?): View? {
        val binding = DataBindingUtil.inflate<FragmentRumahKuBinding>(inflater,
        R.layout.fragment_rumah_ku, container, false)

        database = FirebaseDatabase.getInstance().reference.child("rumah")
        binding.recycleKu.layoutManager = LinearLayoutManager(context)
        val option = FirebaseRecyclerOptions.Builder<Rumah>()
            .setQuery(database.orderByChild("pemilik").equalTo(FirebaseAuth.getInstance().currentUser?.uid)
                , Rumah::class.java).build()
        adapter = RumahKuAdapter(option)
        binding.recycleKu.adapter = adapter

        return binding.root
    }

    override fun onStart() {
        super.onStart()
        adapter.startListening()
    }

    override fun onStop() {
        super.onStop()
        adapter.stopListening()
    }
}