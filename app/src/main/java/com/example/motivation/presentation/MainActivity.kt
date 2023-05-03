package com.example.motivation.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.content.ContextCompat
import com.example.motivation.PhraseDataMock
import com.example.motivation.R
import com.example.motivation.UserPreferences
import com.example.motivation.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private var phraseCategoryId = 1
    private var _phraseCategoryName = "ALL"
    private var phraseCategoryName: String
        get() = _phraseCategoryName
        set(value) {
            _phraseCategoryName = value
            changePhraseCategory(_phraseCategoryName)
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.statusBarColor = ContextCompat.getColor(this, R.color.background)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setListeners()
        loadUserData()
        changePhraseCategory(_phraseCategoryName)
    }

    private fun setListeners() {
        binding.buttonNewPhrase.setOnClickListener {
            nextPhrase()
        }

        binding.imageAll.setOnClickListener {
            phraseCategoryId = 1
            phraseCategoryName = "ALL"
        }

        binding.imageHappy.setOnClickListener {
            phraseCategoryId = 2
            phraseCategoryName = "HAPPY"
        }

        binding.imageSunny.setOnClickListener {
            phraseCategoryId = 3
            phraseCategoryName = "SUNNY"
        }
    }

    private fun loadUserData() {
        val userPreferences = UserPreferences(this)
        val user = userPreferences.getUser("user")
        binding.textGreeting.text = "Olá, ${user}"
    }

    private fun changePhraseCategory(category: String) {
        binding.imageAll.setColorFilter(ContextCompat.getColor(this, R.color.currentLine))
        binding.imageHappy.setColorFilter(ContextCompat.getColor(this, R.color.currentLine))
        binding.imageSunny.setColorFilter(ContextCompat.getColor(this, R.color.currentLine))

        when(category) {
            "ALL" -> {
                binding.imageAll.setColorFilter(ContextCompat.getColor(this, R.color.purple))
            }
            "HAPPY" -> {
                binding.imageHappy.setColorFilter(ContextCompat.getColor(this, R.color.cyan))
            }
            "SUNNY" -> {
                binding.imageSunny.setColorFilter(ContextCompat.getColor(this, R.color.yellow))
            }

        }

        nextPhrase()
    }

    private fun setPhrase(phrase: String) {
        binding.textMotivation.text = phrase
    }

    private fun nextPhrase() {
        val phrase = PhraseDataMock().getPhrase(phraseCategoryId)
        setPhrase(phrase)

    }

}