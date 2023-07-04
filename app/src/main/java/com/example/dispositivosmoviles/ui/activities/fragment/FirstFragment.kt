package com.example.dispositivosmoviles.ui.activities.fragment

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

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
   // private  lateinit var binding : FragmentFirstBinding
    private lateinit var lmanager : LinearLayoutManager
    private  var  rvAdapter: MarvelAdapter = MarvelAdapter { sendMarvelItem(it) }
    private lateinit var LoginMarvel : MutableList<LoginMarvel>
    private var page = 1
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
            layoutInflater, container, false
        )

        lmanager = LinearLayoutManager(
            requireActivity(),
            LinearLayoutManager.VERTICAL,
            false
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
        chargeDataRV("cap")

        binding.reSwipe.setOnRefreshListener{
            chargeDataRV("cap")
            binding.reSwipe.isRefreshing=false
        }

        binding.rvMarvelChart.addOnScrollListener(
            object  : RecyclerView.OnScrollListener(){
                override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                    super.onScrolled(recyclerView, dx, dy)

                    if(dy>0){
                        //elementos que tengo
                        val v = lmanager.childCount
                        //cual es mi posicion actual
                        val p = lmanager.findFirstVisibleItemPosition()
                        //cuantos elementos tengo en total
                        val t = lmanager.itemCount

                        if((v+p) >= t){
                            lifecycleScope.launch(Dispatchers.IO){

                                val newItems =JikanLogic().getAllAnimes()
                               /* val newItems =  MarvelLogic().getCharactersMarvel(
                                    name="spider",
                                    limit=20)*/

                                withContext(Dispatchers.Main){
                                    rvAdapter.updateListitem(newItems)

                                }


                            }

                    }



                    }
                }
        })

        binding.txtFilter.addTextChangedListener{ filterText ->
            var newItems =  LoginMarvel.filter { items ->
                items.name.contains(filterText.toString())
            }
            rvAdapter.replaceListAdpater(newItems)
        }

    }

    fun sendMarvelItem(item: LoginMarvel){
        val i = Intent(requireActivity(), DetailsMarvelItem::class.java)
        i.putExtra("name",item)
        startActivity(i)
    }

    fun chargeDataRV(search:String) {


        lifecycleScope.launch(Dispatchers.IO) {
            var marvelCharItems = MarvelLogic().getCharactersMarvel(
                "spider", page*2
            )
            rvAdapter.items =
                JikanLogic().getAllAnimes()

            /*            rvAdapter = MarvelAdapter(MarvelLogic().getMarvelChars(name=search, 20)) {
                            sendMarvelItem(it)
                        }*/

            withContext(Dispatchers.Main) {
                var marvelCharItems =
                with(binding.rvMarvelChart) {
                    this.adapter = rvAdapter;
                    this.layoutManager = lmanager;

                }
                //lmanager.scrollToPositionWithOffset(pos, 10)
            }
        }

        page++


    }

}