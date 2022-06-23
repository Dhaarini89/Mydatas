package com.android.example.exercise1

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.android.example.databindingvalidation.GameViewModel
import com.android.example.databindingvalidation.MAX_NO_OF_WORDS
import com.android.example.exercise1.databinding.FragmentSecondBinding
import com.android.example.exercise1.datastore.DataStoreManager
import com.android.example.exercise1.viewModel.mainviewmodel
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */
class SecondFragment : Fragment() {

    private var _binding: FragmentSecondBinding? = null
    private val viewmodel: mainviewmodel by viewModels()
    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!
   // lateinit var dataStoreManager : DataStoreManager

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
      //  dataStoreManager = DataStoreManager(requireContext())
        _binding = FragmentSecondBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
      //  viewModel = ViewModelProvider(this).get(GameViewModel::class.java)
      //  binding.testViewModel = v
       // binding.maxNoOfWords = MAX_NO_OF_WORDS
       binding.buttonSecond.setOnClickListener {
              // findNavController().navigate(R.id.action_SecondFragment_to_FirstFragment)
             //  viewmodel.updatetestsecond()

           }
           viewmodel.updatetestsecond()


           viewmodel.textLiveData.observe(viewLifecycleOwner, Observer { data ->
               binding.textviewSecond.text=data
           })

      // binding.submit.setOnClickListener { onSubmitWord() }
       // binding.skip.setOnClickListener { onSkipWord() }


    }
    
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}