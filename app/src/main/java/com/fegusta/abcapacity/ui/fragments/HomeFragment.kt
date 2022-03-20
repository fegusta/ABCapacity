package com.fegusta.abcapacity.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.fegusta.abcapacity.R
import com.fegusta.abcapacity.adapter.QuestAdapter
import com.fegusta.abcapacity.model.Quest
import com.fegusta.abcapacity.repository.QuestRepository

class HomeFragment : Fragment() {

    private lateinit var recyclerView: RecyclerView

    private lateinit var questRepository: QuestRepository
    private lateinit var quests: ArrayList<Quest>

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
        questRepository = QuestRepository(view.context)
        quests = questRepository.getQuests()
    }

    private fun initRecyclerView(view: View) {
        GridLayoutManager(view.context,3,RecyclerView.VERTICAL,false).apply {
            recyclerView.layoutManager = this
        }
        recyclerView.adapter = QuestAdapter(quests)
    }
}