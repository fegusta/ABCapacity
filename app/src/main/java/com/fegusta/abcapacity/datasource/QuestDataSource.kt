package com.fegusta.abcapacity.datasource

import com.fegusta.abcapacity.model.Quest

class QuestDataSource {
    companion object {
        fun getQuest() : ArrayList<Quest>{
            var quests = ArrayList<Quest>()

            quests.add(Quest(1,"primeira pergunta", "arr","err","b"))
            quests.add(Quest(2,"segunda pergunta", "eeg","hll","a"))
            quests.add(Quest(3,"terceira pergunta", "aas","kjj","a"))
            quests.add(Quest(4,"quarta pergunta", "zxz","agg","b"))
            quests.add(Quest(5,"quinta pergunta", "hga","kcj","a"))

            return quests
        }
    }
}