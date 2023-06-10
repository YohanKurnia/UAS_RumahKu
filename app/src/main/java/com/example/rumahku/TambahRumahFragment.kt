package com.example.rumahku

import android.app.Activity
import android.content.Intent
import android.graphics.Bitmap
import android.os.Bundle
import android.provider.MediaStore
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.example.rumahku.databinding.FragmentTambahRumahBinding
import com.google.firebase.auth.FirebaseAuth

class TambahRumahFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?): View? {
        val binding = DataBindingUtil.inflate<FragmentTambahRumahBinding>(inflater,
        R.layout.fragment_tambah_rumah, container, false)
        val resultLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()){result ->
            if (result.resultCode == Activity.RESULT_OK){
                @Suppress("DEPRECATED")
                val bitmap: Bitmap? = result.data?.extras?.getParcelable("data")
                binding.gambarRumah.setImageBitmap(bitmap)
            }
        }
        binding.btnGambar.setOnClickListener {
            val picIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
            resultLauncher.launch(picIntent)
        }
        binding.btnTambah.setOnClickListener {
            if (binding.gambarRumah.drawable != null &&
                    binding.alamatRumah.text != null &&
                    binding.deskripsiRumah.text != null &&
                    binding.noTelepon.text != null){
                val database = RumahKuDatabase()
                database.initDB()
                database.tambahRumah(binding.gambarRumah.drawable,
                FirebaseAuth.getInstance().currentUser?.uid,
                binding.alamatRumah.text.toString(),
                binding.deskripsiRumah.text.toString(),
                binding.noTelepon.text.toString(), null)
                it.findNavController().navigate(R.id.action_tambahRumahFragment_to_mainFragment)
            } else{
                Toast.makeText(activity, "Tolong lengkapi semua bagian", Toast.LENGTH_SHORT).show()
            }
        }
        return binding.root
    }
}