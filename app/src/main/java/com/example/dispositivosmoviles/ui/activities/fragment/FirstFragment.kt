package com.example.dispositivosmoviles.ui.activities.fragment

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager

import com.example.dispositivosmoviles.databinding.FragmentFirstBinding
import com.example.dispositivosmoviles.logic.jkanLogic.JikanLogic
import com.example.dispositivosmoviles.logic.jkanLogic.MarvelLogic
import com.example.dispositivosmoviles.ui.activities.activities2.DetailsMarvelItem
import com.example.dispositivosmoviles.ui.activities.entity.jkanmarvel.LoginMarvel
import com.example.dispositivosmoviles.ui.adapters.MarvelAdapter
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

/**
 * A simple [Fragment] subclass.
 * Use the [FirstFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class FirstFragment : Fragment() {

/*
    private  lateinit var binding : FragmentFirstBinding

        override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
        ): View? {

            binding = FragmentFirstBinding.inflate(layoutInflater,
                container,
                false)

            // Inflate the layout for this fragment
            return binding.root
        }

        override fun onStart() {
            super.onStart()

            val names = arrayListOf<String>("carlos, valadimir",
                "andres","pepe", "mario","rosa")

            val adapter = ArrayAdapter<String>(requireActivity(), R.layout.simple_layout,names )

            binding.spinner.adapter = adapter
            //binding.listView.adapter = adapter
        }

         */
private lateinit var binding: FragmentFirstBinding;

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        binding = FragmentFirstBinding.inflate(
            inflater, container, false
        )
        return binding.root

    }

    override fun onStart() {
        super.onStart();

        val names = arrayListOf<String>("A", "B", "C", "D", "E")

        val adapter = ArrayAdapter<String>(
            requireActivity(),
            android.R.layout.simple_spinner_item,
            names
        )

        binding.spinner.adapter = adapter
        chargeDataRv()

        binding.reSwipe.setOnRefreshListener{
            chargeDataRv()
            binding.reSwipe.isRefreshing=false
        }





    }

    fun sendMarvelItem(item: LoginMarvel){
        val i = Intent(requireActivity(), DetailsMarvelItem::class.java)
        i.putExtra("name",item)
        startActivity(i)
    }

    fun chargeDataRv(){
        lifecycleScope.launch(Dispatchers.IO){
            val rvAdapter = MarvelAdapter(
                JikanLogic().getAllAnimes()



            ) {sendMarvelItem(it)};

            withContext(Dispatchers.Main){
                with(binding.rvMarvelChart){
                    this.adapter = rvAdapter
                    this.layoutManager= LinearLayoutManager(
                        requireActivity(),
                        LinearLayoutManager.HORIZONTAL,
                        false
                    )
                }
            }

        }



    }

}