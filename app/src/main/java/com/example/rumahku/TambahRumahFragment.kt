package com.example.rumahku

import android.app.Activity
import android.content.Intent
import android.graphics.Bitmap
import android.os.Bundle
import android.provider.MediaStore
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.contract.ActivityResultContracts
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.example.rumahku.databinding.FragmentTambahRumahBinding

class TambahRumahFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = DataBindingUtil.inflate<FragmentTambahRumahBinding>(inflater,
        R.layout.fragment_tambah_rumah, container, false)
        var resultLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()){result ->
            if (result.resultCode == Activity.RESULT_OK){
                @Suppress("DEPRECATED")
                val bitmap: Bitmap? = result.data?.extras?.getParcelable("data")
                binding.gambarRumah.setImageBitmap(bitmap)
            }
        }
        binding.btnGambar.setOnClickListener { view ->
            val picIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
            resultLauncher.launch(picIntent)
        }
        return binding.root
    }
}