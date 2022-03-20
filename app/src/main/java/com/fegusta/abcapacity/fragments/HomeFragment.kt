package com.fegusta.abcapacity.fragments

import android.os.Bundle
import android.text.method.MovementMethod
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.fegusta.abcapacity.JogoActivity
import com.fegusta.abcapacity.R
import com.fegusta.abcapacity.R.id.recyclerViewFragmentHome
import com.fegusta.abcapacity.adapter.QuestAdapter
import com.fegusta.abcapacity.datasource.QuestDataSource
import com.fegusta.abcapacity.model.Quest
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.fegusta.abcapacity.repository.QuestRepository




/**
 * A simple [Fragment] subclass.
 * Use the [HomeFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
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