package com.fegusta.abcapacity.datasource

import com.fegusta.abcapacity.model.Level
import com.fegusta.abcapacity.model.Quest

class LevelDataSouce {
    companion object {
        fun getLevel() : ArrayList<Level>{
            var levels = ArrayList<Level>()

            levels.add(Level(1,"primeira nivel"))
            levels.add(Level(2,"segunda nivel"))
            levels.add(Level(3,"terceira nivel"))
            levels.add(Level(4,"quarta nivel"))
            levels.add(Level(5,"quinta nivel"))

            return levels
        }
    }
}