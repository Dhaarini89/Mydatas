package com.android.example.exercise1


import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.datastore.dataStoreFile
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.android.example.exercise1.databinding.FragmentFirstBinding
import com.android.example.exercise1.viewModel.mainviewmodel
import androidx.lifecycle.Observer
import androidx.lifecycle.asLiveData
import com.android.example.exercise1.datastore.DataStoreManager
import com.android.example.exercise1.repository.model
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import kotlin.properties.ReadWriteProperty
import kotlin.reflect.KProperty

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class FirstFragment : Fragment() {
    private val modelvalue = model(samplevalue ="something" )

    private var _binding: FragmentFirstBinding? = null
   private  val viewModel: mainviewmodel by activityViewModels()
    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!
   lateinit var dataStoreManager: DataStoreManager

    override fun onCreateView(
        inflater: LayoutInflater,container: ViewGroup?,savedInstanceState:Bundle?
    ): View? {
        _binding = FragmentFirstBinding.inflate(inflater,container,false)
        dataStoreManager = DataStoreManager(requireContext())
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

         dataStoreManager.scoreFlow.asLiveData().observe(requireActivity() ,{
             if (it!=null)
             {
                 binding.textviewFirst.text = it.toString()
             }
         })


        binding.buttonFirst.setOnClickListener {
         /* GlobalScope.launch(Dispatchers.IO) {
              dataStoreManager.savetoDataStore(
                  score = 100
              )
              Log.d("datastore","I was here fragment1")

          } */
            findNavController().navigate(R.id.action_FirstFragment_to_SecondFragment)

        }

            binding.buttonMvvm.setOnClickListener{
            viewModel.updatetext("check")
        }
        binding.buttonUi.setOnClickListener {
            binding.textviewFirst.text=modelvalue.samplevalue
        }
      viewModel.textLiveData.observe(viewLifecycleOwner,Observer { text -> binding.textviewFirst.text = text })

    }
}



