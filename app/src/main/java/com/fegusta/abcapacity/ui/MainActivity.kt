package com.fegusta.abcapacity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.fegusta.abcapacity.adapter.LevelAdapter
import com.fegusta.abcapacity.adapter.QuestAdapter
import com.fegusta.abcapacity.repository.QuestRepository
import com.fegusta.abcapacity.ui.CadastroQuestActivity
import com.google.android.material.floatingactionbutton.FloatingActionButton

class MainActivity : AppCompatActivity(), View.OnClickListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        iniciarRecyclerView()

        var floatButton = findViewById<FloatingActionButton>(R.id.floatButton)
        floatButton.setOnClickListener(this)

    }


    private fun iniciarRecyclerView() {
        val recyclerView = findViewById<RecyclerView>(R.id.recycler_view_layour_recycler)
        recyclerView.layoutManager = LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false)
        val repo = QuestRepository(this)
        recyclerView.adapter = QuestAdapter(repo.getQuests())
    }

    override fun onClick(view: View?) {
        if(view!!.id == R.id.floatButton){
            val intent = Intent(this, CadastroQuestActivity::class.java)
            startActivity(intent)
        }
    }
}