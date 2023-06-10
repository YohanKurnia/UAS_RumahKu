package com.example.rumahku

import android.app.Activity
import android.content.Intent
import android.graphics.Bitmap
import android.os.Bundle
import android.provider.MediaStore
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import com.bumptech.glide.Glide
import com.bumptech.glide.signature.ObjectKey
import com.example.rumahku.databinding.FragmentEditRumahBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.StorageReference
import com.google.firebase.storage.ktx.storage

class EditRumahFragment : Fragment() {
    private lateinit var storage: StorageReference
    private lateinit var database: DatabaseReference
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?): View? {
        val binding = DataBindingUtil.inflate<FragmentEditRumahBinding>(inflater,
        R.layout.fragment_edit_rumah, container, false)

        val key = arguments?.getString("rumahKuKey")
        storage = Firebase.storage.reference.child("gambarRumah").child("$key.jpg")
        database = Firebase.database.reference.child("rumah").child(key!!)

        Glide.with(this).load(storage).into(binding.gambarRumahEdit)
        database.child("alamat").get().addOnSuccessListener { binding.alamatRumahEdit.setText(it.value.toString()) }
        database.child("deskripsi").get().addOnSuccessListener { binding.deskripsiRumahEdit.setText(it.value.toString()) }
        database.child("telepon").get().addOnSuccessListener { binding.noTeleponEdit.setText(it.value.toString()) }
        binding.btnEdit.setOnClickListener {
            if (binding.gambarRumahEdit.drawable != null &&
                binding.alamatRumahEdit.text != null &&
                binding.deskripsiRumahEdit.text != null &&
                binding.noTeleponEdit.text != null){
                val database = RumahKuDatabase()
                database.initDB()
                database.tambahRumah(null,
                    FirebaseAuth.getInstance().currentUser?.uid,
                    binding.alamatRumahEdit.text.toString(),
                    binding.deskripsiRumahEdit.text.toString(),
                    binding.noTeleponEdit.text.toString(), key)
                it.findNavController().navigate(R.id.action_editRumahFragment_to_rumahKuFragment)
            } else{
                Toast.makeText(activity, "Tolong lengkapi semua bagian", Toast.LENGTH_SHORT).show()
            }
        }

        return binding.root
    }
}