package com.android.githubrepositories.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.fragment.NavHostFragment
import com.android.githubrepositories.R
import com.android.githubrepositories.databinding.ActivityHomeBinding

private lateinit var binding: ActivityHomeBinding

internal class HomeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}