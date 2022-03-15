package com.fegusta.abcapacity.datasource

import com.fegusta.abcapacity.model.Quest

class QuestDataSource {
    companion object {
        fun getQuest() : ArrayList<Quest>{
            var quests = ArrayList<Quest>()

            quests.add(Quest(1,"primeira pergunta", arrayOf("a","b"),"b"))
            quests.add(Quest(2,"segunda pergunta", arrayOf("a","b"),"a"))
            quests.add(Quest(3,"terceira pergunta", arrayOf("a","b","c"),"c"))
            quests.add(Quest(4,"quarta pergunta", arrayOf("a","b","c","d"),"d"))

            return quests
        }
    }
}