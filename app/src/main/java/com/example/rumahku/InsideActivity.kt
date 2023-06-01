package com.example.rumahku

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.example.rumahku.databinding.ActivityInsideBinding
import com.firebase.ui.auth.AuthUI
import com.google.firebase.auth.FirebaseUser

class InsideActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setContentView(R.layout.activity_inside)
        val binding = DataBindingUtil.setContentView<ActivityInsideBinding>(this, R.layout.activity_inside)
        @Suppress("DEPRECATION")
        val user = intent.getParcelableExtra<FirebaseUser?>("user")
    }
}