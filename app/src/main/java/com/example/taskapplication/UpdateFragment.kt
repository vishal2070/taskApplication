package com.example.taskapplication

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.LiveData
import com.example.taskapplication.databinding.FragmentUpdateBinding
import com.example.taskapplication.db.ApiService
import com.example.taskapplication.db.AppDao
import com.example.taskapplication.db.AppDatabase
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class UpdateFragment : Fragment() {

    private lateinit var binding: FragmentUpdateBinding

    init {

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        binding = FragmentUpdateBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

         val pid = arguments?.getInt("id")
         val pdescription = arguments?.getString("description")
         val ptitle = arguments?.getString("title")
         Log.d("TAG-Desription", "onViewCreated: "+ pid)

        binding.btnUpdate.setOnClickListener {
            if(pid !=null && ptitle !=null && pdescription != null){
                val title = binding.title.text.toString()
                val description = binding.description.text.toString()
                Log.d("TAG-Desription", "onViewCreated: "+ title +"   "+description)
                val updatedata = DataClass(id=pid,title=title,body=description)
                val dao : AppDao = AppDatabase.getDatabase(requireContext()).dao()
                dao.insertSingle(updatedata)
            }

        }
    }
}