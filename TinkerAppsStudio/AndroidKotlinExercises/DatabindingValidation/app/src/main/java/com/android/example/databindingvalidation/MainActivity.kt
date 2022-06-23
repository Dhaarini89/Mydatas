package com.android.example.databindingvalidation

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast

import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.LifecycleOwner

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.android.example.databindingvalidation.databinding.ActivityMainBinding

import com.google.android.material.dialog.MaterialAlertDialogBuilder

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    private lateinit var data : String
   lateinit var  viewModel: GameViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this,R.layout.activity_main)
        data = intent?.extras?.getString("levels").toString()
        levelsinfo =data
      //  viewModel = ViewModelProvider(this).get(GameViewModel::class.java)
        viewModel = GameViewModel()
        binding.gameViewModel = viewModel
        binding.lifecycleOwner = this
        binding.maxNoOfWords = MAX_NO_OF_WORDS
        //binding.lifecycleOwner = viewLifecycleOwner


    }

    override fun onStart() {
        super.onStart()
        binding.submit.setOnClickListener {
            onSubmitWord()
       }
        binding.skip.setOnClickListener { onSkipWord() }


    }
    private fun onSubmitWord() {
        val playerWord = binding.textInputEditText.text.toString()
        // viewModel.levels.value
        if (viewModel.isUserWordCorrect(playerWord)) {
            setErrorTextField(false)
            if (viewModel.nextWord()) {
                Log.d("word","next word function invoked")
            } else {
                showFinalScoreDialog()
            }
        }
        else {
            setErrorTextField(true)
        }
    }

    private fun showFinalScoreDialog() {
        //entries.scorecompanion=viewModel.score.value!!
        MaterialAlertDialogBuilder(this)
            .setTitle(getString(R.string.congratulations))
            .setMessage(getString(R.string.you_scored,viewModel.score.value))
            .setCancelable(false)
            .setNegativeButton(getString(R.string.exit)){_,_->
                exitGame()
            }
            .setPositiveButton(getString(R.string.play_again)){_,_->
                restartGame()
            }
            .show()

    }

    private fun restartGame() {
        viewModel.reinitializeData()
        setErrorTextField(false)
    }


    private fun onSkipWord() {
        if (viewModel.nextWord()) {
           // binding.textViewUnscrambledWord.text=viewModel.currentScrambledWord.value
            setErrorTextField(false)

        } else {
            showFinalScoreDialog()
        }
    }


    private fun exitGame() {
        val intent = Intent()
        intent.putExtra("value",viewModel.score.value!!)
        setResult(Activity.RESULT_OK,intent)
        finish()
    }

    private fun setErrorTextField(error: Boolean) {
        if (error) {
            binding.textField.isErrorEnabled = true
            binding.textField.error = getString(R.string.try_again)
        } else {
            binding.textField.isErrorEnabled = false
            binding.textInputEditText.text = null
        }
    }

}