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
import com.fegusta.abcapacity.model.Level
import com.fegusta.abcapacity.model.Quest

class LevelAdapter(var liestaLevel: ArrayList<Level>) : RecyclerView.Adapter<LevelAdapter.LevelViewHolder>() {

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LevelViewHolder {
            val itemView = LayoutInflater.from(parent.context).inflate(R.layout.main_line_view, parent, false)

            return LevelViewHolder(itemView)
        }

        override fun getItemCount(): Int {
            return liestaLevel.size
        }

        override fun onBindViewHolder(holder: LevelViewHolder, position: Int) {
            val level = liestaLevel[position]
            holder.bind(level)

        }

        class LevelViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

            fun bind(level: Level) {
                itemView.findViewById<TextView>(R.id.textView).text = level.id.toString()
                itemView.findViewById<RelativeLayout>(R.id.main_line_view).setOnClickListener {
                    val intent = Intent(itemView.context, JogoActivity::class.java)
                    intent.putExtra("id", level.id)
                    intent.putExtra("operacao", Constants.OPERACAO_JOGO)
                    itemView.context.startActivity(intent)
                }
            }
        }
}