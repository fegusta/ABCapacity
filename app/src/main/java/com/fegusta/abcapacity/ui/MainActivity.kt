package com.fegusta.abcapacity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.fegusta.abcapacity.adapter.LevelAdapter
import com.fegusta.abcapacity.adapter.QuestAdapter
import com.fegusta.abcapacity.repository.QuestRepository
import com.fegusta.abcapacity.ui.CadastroQuestActivity
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.floatingactionbutton.FloatingActionButton

class MainActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var floatButton: FloatingActionButton
    private lateinit var recyclerViewLayourRecycler: RecyclerView

    private lateinit var bottom_navigation: BottomNavigationView

    private lateinit var questRepository: QuestRepository

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initViews()
        initListeners()
        initObjects()
        initRecyclerView()
        initSignalNotifications(R.id.menu_main)
    }

    private fun initViews() {
        floatButton = findViewById<FloatingActionButton>(R.id.floatButton)

        bottom_navigation = findViewById<BottomNavigationView>(R.id.bottom_navigation)

        recyclerViewLayourRecycler = findViewById<RecyclerView>(R.id.recyclerViewLayourRecycler)
    }

    private fun initListeners() {
        floatButton.setOnClickListener(this)

        bottom_navigation.setOnNavigationItemSelectedListener { item ->
            when(item.itemId) {
                R.id.menu_add_quest -> {
                    val intent = Intent(this, CadastroQuestActivity::class.java)
                    startActivity(intent)
                    true
                }
                else -> false
            }
        }
    }

    private fun initObjects() {
        questRepository = QuestRepository(this)
    }

    private fun initRecyclerView() {
        GridLayoutManager(this,3, RecyclerView.VERTICAL, false).apply {
            recyclerViewLayourRecycler.layoutManager = this
        }
        recyclerViewLayourRecycler.adapter = QuestAdapter(questRepository.getQuests())
    }

    private fun initSignalNotifications(id: Int) {
        var badge = bottom_navigation.getOrCreateBadge(id)
        badge.isVisible = true
        badge.number = 99
    }

    override fun onClick(v: View) {
        when(v.id) {
            R.id.floatButton -> {
                val intent = Intent(this, CadastroQuestActivity::class.java)
                startActivity(intent)
            }
        }
    }
}