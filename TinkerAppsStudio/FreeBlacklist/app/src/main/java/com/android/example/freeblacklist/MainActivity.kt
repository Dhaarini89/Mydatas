package com.android.example.freeblacklist

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Base64
import android.util.Log
import android.util.Log.INFO
import android.view.View
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.android.example.freeblacklist.databinding.ActivityMainBinding




class MainActivity : AppCompatActivity() {
    private lateinit var  viewModel : OverViewModel
    private lateinit var binding : ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

       binding = DataBindingUtil.setContentView(this,R.layout.activity_main)
        viewModel= ViewModelProvider(this).get(OverViewModel::class.java)
        binding.button.setOnClickListener{
            setdata()
            binding.test.setText(viewModel.progress.value)
        }

        binding.buttonSessiontoken.setOnClickListener{
            getBlackList()
        }

        binding.RBlackListFilter.setOnClickListener{
            removeBlackListFilter()
        }
    }
    private fun setdata()
    {
        viewModel.postFreeDetails()
//       Toast.makeText(applicationContext,viewModel.app_token,Toast.LENGTH_LONG).show()

    }

     fun getBlackList()
    {
       viewModel.getSessionTokenDetails()
        // viewModel.progress.observe()
        viewModel.progress.observe(this, Observer {
            binding.test.text = it.toString()
        })

        //binding.test.setText(viewModel.progress.value)
    }
    fun removeBlackListFilter()
    {
         viewModel.removeBlackListFilter()
        viewModel.progress.observe(this, Observer {
            binding.test.text = it.toString()
        })
    }
}