package com.fegusta.abcapacity.ui.fragments

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.fegusta.abcapacity.JogoActivity
import com.fegusta.abcapacity.R
import com.fegusta.abcapacity.adapter.BaseAdapter
import com.fegusta.abcapacity.adapter.BaseViewHolder
import com.fegusta.abcapacity.constants.Constants
import com.fegusta.abcapacity.data.model.Level
import com.fegusta.abcapacity.data.repository.LevelRepository

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
        //GridLayoutManager(view.context,3,RecyclerView.VERTICAL,false).apply {
        //    recyclerView.layoutManager = this
        //}
        recyclerView.layoutManager = LinearLayoutManager(view.context)
        recyclerView.adapter = BaseAdapter {
            LevelView(it)
        }.apply {
            items = levels
        }
        //recyclerView.adapter = LevelAdapter(levels)
    }

    class LevelView(viewGroup: ViewGroup) : BaseViewHolder<Level>(R.layout.main_line_view, viewGroup) {

        override fun bind(item: Level) {
            itemView.findViewById<TextView>(R.id.textNameLevel).text = item.nameLevel
            itemView.findViewById<TextView>(R.id.textIdLevel).text = item.id.toString()
            itemView.findViewById<RelativeLayout>(R.id.main_line_view).setOnClickListener {
                val intent = Intent(itemView.context, JogoActivity::class.java)
                intent.putExtra("id", item.id)
                intent.putExtra("operacao", Constants.OPERACAO_JOGO)
                itemView.context.startActivity(intent)
            }
        }
    }
}