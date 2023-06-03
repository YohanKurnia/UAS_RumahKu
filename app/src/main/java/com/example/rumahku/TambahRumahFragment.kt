package com.example.rumahku

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.example.rumahku.databinding.FragmentTambahRumahBinding

class TambahRumahFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = DataBindingUtil.inflate<FragmentTambahRumahBinding>(inflater,
        R.layout.fragment_tambah_rumah, container, false)
        return binding.root
    }

}