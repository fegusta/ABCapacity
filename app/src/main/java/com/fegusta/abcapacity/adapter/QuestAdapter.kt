package com.fegusta.abcapacity.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.fegusta.abcapacity.JogoActivity
import com.fegusta.abcapacity.R
import com.fegusta.abcapacity.constants.Constants
import com.fegusta.abcapacity.model.Quest

class QuestAdapter(var liestaQuest: ArrayList<Quest>) : RecyclerView.Adapter<QuestAdapter.QuestViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): QuestViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.main_line_view, parent, false)

        return QuestViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return liestaQuest.size
    }

    override fun onBindViewHolder(holder: QuestViewHolder, position: Int) {
        val quest = liestaQuest[position]
        holder.bind(quest)
    }

    class QuestViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bind(quest: Quest) {
            itemView.findViewById<TextView>(R.id.textView).text = quest.id.toString()
            itemView.findViewById<RelativeLayout>(R.id.main_line_view).setOnClickListener {
                val intent = Intent(itemView.context, JogoActivity::class.java)
                intent.putExtra("operacao", Constants.OPERACAO_JOGO)
                intent.putExtra("id", quest.id)
                itemView.context.startActivity(intent)
            }
        }
    }
}