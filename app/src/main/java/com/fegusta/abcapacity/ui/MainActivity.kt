package com.fegusta.abcapacity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.fegusta.abcapacity.adapter.LevelAdapter
import com.fegusta.abcapacity.adapter.QuestAdapter
import com.fegusta.abcapacity.repository.QuestRepository
import com.fegusta.abcapacity.ui.CadastroQuestActivity
import com.google.android.material.floatingactionbutton.FloatingActionButton

class MainActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var floatButton: FloatingActionButton
    private lateinit var recyclerViewLayourRecycler: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initViews()
        initListeners()
        initRecyclerView()
    }

    private fun initViews() {
        floatButton = findViewById<FloatingActionButton>(R.id.floatButton)

        recyclerViewLayourRecycler = findViewById<RecyclerView>(R.id.recyclerViewLayourRecycler)
    }

    private fun initListeners() {
        floatButton.setOnClickListener(this)
    }

    private fun initRecyclerView() {
        GridLayoutManager(this,3, RecyclerView.VERTICAL, false).apply {
            recyclerViewLayourRecycler.layoutManager = this
        }
        val repo = QuestRepository(this)
        recyclerViewLayourRecycler.adapter = QuestAdapter(repo.getQuests())
    }

    override fun onClick(view: View?) {
        if(view!!.id == R.id.floatButton){
            val intent = Intent(this, CadastroQuestActivity::class.java)
            startActivity(intent)
        }
    }
}