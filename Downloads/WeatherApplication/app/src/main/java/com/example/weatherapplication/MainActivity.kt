package com.example.weatherapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.weatherapplication.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.title = "Weather Application"
        initListeners()
    }

    private fun initListeners(){
        binding.btnForecast.setOnClickListener {
            startActivity(Intent(applicationContext, ForecastActivity::class.java))
        }
    }
}