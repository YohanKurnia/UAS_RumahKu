package id.ac.umn.rumahku

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.StorageReference
import com.google.firebase.storage.ktx.storage
import id.ac.umn.rumahku.databinding.FragmentRumahBinding

class RumahFragment : Fragment() {
    private lateinit var storage: StorageReference
    private lateinit var database: DatabaseReference
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?): View? {
        val binding = DataBindingUtil.inflate<FragmentRumahBinding>(inflater,
            R.layout.fragment_rumah, container, false)

        val key = arguments?.getString("rumahKey")
        storage = Firebase.storage.reference.child("gambarRumah").child("$key.jpg")
        database = Firebase.database.reference.child("rumah").child(key!!)

        Glide.with(this).load(storage).into(binding.gambarIni)
        database.child("alamat").get().addOnSuccessListener { binding.alamatIni.text = "Alamat :\n${it.value.toString()}" }
        database.child("deskripsi").get().addOnSuccessListener { binding.deskripsiIni.text = "Deskripsi :\n${it.value.toString()}" }
        database.child("telepon").get().addOnSuccessListener { binding.teleponIni.text = "Telepon :\n${it.value.toString()}" }

        binding.btnTelepon.setOnClickListener {
            val i = Intent(Intent.ACTION_DIAL)
            i.setData(Uri.parse("tel:${binding.teleponIni.text}"))
            startActivity(i)
        }

        return binding.root
    }

}