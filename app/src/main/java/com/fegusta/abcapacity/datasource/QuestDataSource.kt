package com.fegusta.abcapacity.datasource

import com.fegusta.abcapacity.model.Quest

class QuestDataSource {
    companion object {
        fun getQuest() : ArrayList<Quest>{
            var quests = ArrayList<Quest>()

            quests.add(Quest(1,1,"primeira pergunta", "arr","err","b"))
            quests.add(Quest(2,2,"segunda pergunta", "eeg","hll","a"))
            quests.add(Quest(3,3,"terceira pergunta", "aas","kjj","a"))
            quests.add(Quest(4,4,"quarta pergunta", "zxz","agg","b"))
            quests.add(Quest(5,5,"quinta pergunta", "azd","dxf","a"))
            quests.add(Quest(6,6,"sexta pergunta", "jbr","kcj","a"))
            quests.add(Quest(7,7,"setima pergunta", "ghs","rok","b"))
            quests.add(Quest(8,8,"oitava pergunta", "hga","dbg","b"))
            quests.add(Quest(9,9,"nona pergunta", "dlp","kcj","a"))
            quests.add(Quest(10,0,"decima pergunta", "fdk","açs","b"))

            return quests
        }
    }
}