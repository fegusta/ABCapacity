package com.fegusta.abcapacity

import android.content.Intent
import android.os.Bundle
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.fegusta.abcapacity.adapter.QuestAdapter
import com.fegusta.abcapacity.datasource.QuestDataSource


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var recyclerView = findViewById<RecyclerView>(R.id.recycler_view_layour_recycler)

        iniciarRecyclerView()

    }

    private fun iniciarRecyclerView() {
        val recyclerView = findViewById<RecyclerView>(R.id.recycler_view_layour_recycler)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = QuestAdapter(QuestDataSource.getQuest())
    }


}