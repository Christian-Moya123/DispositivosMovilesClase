package com.example.dispositivosmoviles.ui.fragments

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.dispositivosmoviles.R
import com.example.dispositivosmoviles.ui.validator.otro.RandomNumListAdapter


class FirstFragment : Fragment() {

    // Add RecyclerView member
    private lateinit var recyclerView: RecyclerView

    @SuppressLint("MissingInflatedId")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_first, container, false)

        // Add the following lines to create RecyclerView
        recyclerView = view.findViewById(R.id.recyclerview)
        recyclerView.setHasFixedSize(true)
        recyclerView.layoutManager = LinearLayoutManager(view.context)
        recyclerView.adapter = RandomNumListAdapter(1234)

        return view
    }




//    private lateinit var binding: FragmentFirstBinding;
//    private var rvAdapter: MarvelAdapter = MarvelAdapter(
//        { item -> sendMarvelItem(item) },
//        { item -> saveMarvelItem(item) }
//    )
//    private lateinit var lmanager : LinearLayoutManager
//    private var page = 1
//
//    private var marvelCharsItems : MutableList<MarvelChars> = mutableListOf<MarvelChars>()
//
//
//    override fun onCreateView(
//        inflater: LayoutInflater, container: ViewGroup?,
//        savedInstanceState: Bundle?
//    ): View? {
//        // Inflate the layout for this fragment
//
//        binding = FragmentFirstBinding.inflate(
//            layoutInflater, container, false
//        )
//
//
//        lmanager = LinearLayoutManager(
//            requireActivity(),
//            LinearLayoutManager.VERTICAL,
//            false)
//        return binding.root
//
//    }
//
//    override fun onStart() {
//        super.onStart();
//
//
//        val names = arrayListOf<String>("A", "B", "C", "D", "E")
//
//        val adapter1 = ArrayAdapter<String>(
//            requireActivity(),
//            android.R.layout.simple_spinner_item,
//            names
//        )
//
//        binding.spinner.adapter = adapter1
//        chargeDataRV(5)
//
//        binding.rvSwipe.setOnRefreshListener {
//            chargeDataRV(5)
//            binding.rvSwipe.isRefreshing = false
//            lmanager.scrollToPositionWithOffset(5, 20)
//        }
//
//        binding.rvMarvelChars.addOnScrollListener(
//            object: RecyclerView.OnScrollListener(){
//                override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
//                    super.onScrolled(recyclerView, dx, dy)
//                    if(dx>0){
//                        val v = lmanager.childCount//Cuantos han pasado
//                        val p = lmanager.findFirstVisibleItemPosition()//Cual es mi posicion actual
//                        val t = lmanager.itemCount//Cuantos tengo en total
//
//                        if ((v + p) >= t) {
//                            lifecycleScope.launch((Dispatchers.Main))
//                            {
//                                val x = with(Dispatchers.IO){
//                                    MarvelLogic().getAllMarvelCharacters(1,2)
//                                    //MarvelLogic().getMarvelChars(name = "", page*3 )
//                                    //JikanAnimeLogic().getAllAnimes()
//                                }
//                                rvAdapter.updateListAdapter((x))
//
//                            }
//                        }
//
//                    }
//
//                }
//            })
//
//        binding.txtFilter.addTextChangedListener{ filteredText ->
//            val newItems = marvelCharsItems.filter {items ->
//                items.name.lowercase().contains(filteredText.toString().lowercase())
//            }
//
//            rvAdapter.replaceListAdapter(newItems)
//        }
//
//    }
//
//    fun corrotine(){
//        lifecycleScope.launch(Dispatchers.Main){
//            var name = "Bayron"
//
//            name = withContext(Dispatchers.IO)
//            {
//                name ="Jairo"
//                return@withContext name
//            }
//
//            binding.cardView.radius
//        }
//    }
//
//    fun sendMarvelItem(item: MarvelChars) {
//        //Intent(contexto de la activity, .class de la activity)
//        val i = Intent(requireActivity(), DetailsMarvelItem::class.java)
//        i.putExtra("item", item)//mandamos los items a la otra activity
//        startActivity(i)
//    }
//
//    fun saveMarvelItem(item: MarvelChars) :Boolean{
//        //Intent(contexto de la activity, .class de la activity)
//        lifecycleScope.launch(Dispatchers.Main){
//            withContext(Dispatchers.IO){
//                DispositivosMoviles
//                    .getDbInstance()
//                    .marvelDao()
//                    .insertMarvelCharacter(listOf( item.getMarvelCharsDB()))
//            }
//        }
//
//        return true
//    }
//
//    fun chargeDataRV(pos:Int) {
//        lifecycleScope.launch(Dispatchers.Main) {
//            //rvAdapter.items = JikanAnimeLogic().getAllAnimes()
//            marvelCharsItems = withContext(Dispatchers.IO){
//                return@withContext MarvelLogic().getAllMarvelCharacters(1,2)
//
//            }
//
//
//            rvAdapter.items = marvelCharsItems
//
//            binding.rvMarvelChars.apply {
//                this.adapter = rvAdapter;
//                this.layoutManager = lmanager;
//
//
//                lmanager.scrollToPositionWithOffset(pos ,10)
//            }
//        }
//        page++
//    }
}