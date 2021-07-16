package com.eclecticsIntern.newsApp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.eclecticsIntern.newsApp.databinding.ActivityMainBinding
import com.eclecticsIntern.newsApp.ui.NewsActivity

class MainActivity : AppCompatActivity() {
private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        startActivity(Intent(this, NewsActivity::class.java))
        finish()
    }
}