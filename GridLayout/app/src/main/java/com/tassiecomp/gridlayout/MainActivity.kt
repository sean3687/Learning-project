package com.tassiecomp.gridlayout

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.tassiecomp.gridlayout.adapters.AlphaAdapters
import com.tassiecomp.gridlayout.model.AlphaChar

class MainActivity : AppCompatActivity() {

    private var recyclerView: RecyclerView? = null
    private var gridlayoutManager: GridLayoutManager ? = null
    private var arrayList: ArrayList<AlphaChar>? =null
    private var alphaAdapters: AlphaAdapters? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerView = findViewById(R.id.my_recycler_view)
        gridlayoutManager = GridLayoutManager(applicationContext,3, LinearLayoutManager.VERTICAL,false)
        recyclerView?.layoutManager = gridlayoutManager
        recyclerView?.setHasFixedSize(true)
        arrayList = ArrayList()
        arrayList = setDataInList()
        alphaAdapters = AlphaAdapters(applicationContext, arrayList!!)
        recyclerView?.adapter = alphaAdapters
    }

    private fun setDataInList(): ArrayList<AlphaChar>{
        var items: ArrayList<AlphaChar> = ArrayList()

        items.add(AlphaChar(R.drawable.letter_a, "Latter A"))
        items.add(AlphaChar(R.drawable.letter_b, "Latter B"))
        items.add(AlphaChar(R.drawable.letter_c, "Latter C"))
        items.add(AlphaChar(R.drawable.letter_d, "Latter D"))
        items.add(AlphaChar(R.drawable.letter_e, "Latter E"))
        items.add(AlphaChar(R.drawable.letter_f, "Latter F"))
        items.add(AlphaChar(R.drawable.letter_g, "Latter G"))
        items.add(AlphaChar(R.drawable.letter_h, "Latter H"))


        return items
    }
}