package com.example.motivation.presentation

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.core.content.ContextCompat
import com.example.motivation.R
import com.example.motivation.UserPreferences
import com.example.motivation.databinding.ActivityUserBinding

class UserActivity : AppCompatActivity() {
    private lateinit var binding: ActivityUserBinding
    private lateinit var userPreferences: UserPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.statusBarColor = ContextCompat.getColor(this, R.color.background)
        binding = ActivityUserBinding.inflate(layoutInflater)
        userPreferences = UserPreferences(this)
        setContentView(binding.root)
        setListeners()
        checkUser()
    }

    private fun setListeners() {
        binding.buttonSave.setOnClickListener {
            login()
            finish()
        }
    }

    private fun checkUser() {
        val user = userPreferences.getUser("user")

        if (user.isNotEmpty() && user.isNotBlank()) {
            navigate()
        }
    }

    private fun navigate() {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
    }

    private fun login() {
        val userName = binding.editName.text.toString()
        if (userName.isNotEmpty() && userName.isNotBlank()) {
            userPreferences.saveUser(userName)
            navigate()
        } else {
            Toast.makeText(this, R.string.empty_name_error, Toast.LENGTH_LONG).show()
        }
    }


}