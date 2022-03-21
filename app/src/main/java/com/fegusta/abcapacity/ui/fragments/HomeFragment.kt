package com.fegusta.abcapacity.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.fegusta.abcapacity.R
import com.fegusta.abcapacity.adapter.LevelAdapter
import com.fegusta.abcapacity.adapter.QuestAdapter
import com.fegusta.abcapacity.model.Level
import com.fegusta.abcapacity.model.Quest
import com.fegusta.abcapacity.repository.LevelRepository
import com.fegusta.abcapacity.repository.QuestRepository

class HomeFragment : Fragment() {

    private lateinit var recyclerView: RecyclerView

    private lateinit var levelRepository: LevelRepository
    private lateinit var levels: ArrayList<Level>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_home, container, false)
        initViews(view)
        initObjects(view)
        initRecyclerView(view)
        return view
    }

    private fun initViews(view: View) {
        recyclerView = view.findViewById<RecyclerView>(R.id.recyclerViewFragmentHome)
    }

    private fun initObjects(view: View) {
        levelRepository = LevelRepository(view.context)
        levels = levelRepository.getLevels()
    }

    private fun initRecyclerView(view: View) {
        GridLayoutManager(view.context,3,RecyclerView.VERTICAL,false).apply {
            recyclerView.layoutManager = this
        }
        recyclerView.adapter = LevelAdapter(levels)
    }
}