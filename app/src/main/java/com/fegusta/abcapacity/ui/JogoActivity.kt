package com.fegusta.abcapacity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import com.fegusta.abcapacity.model.Level
import com.fegusta.abcapacity.model.Quest

class JogoActivity : AppCompatActivity() {

    private lateinit var operacao: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_jogo)
        var id = intent.getIntExtra("id",0)

        var question = 0

        var textoResposta = findViewById<TextView>(R.id.textoResposta)
        var buttonA = findViewById<Button>(R.id.buttonA)
        var buttonB = findViewById<Button>(R.id.buttonB)
        var buttonLogin = findViewById<Button>(R.id.buttonLogin)
        var progressBarjogo = findViewById<ProgressBar>(R.id.progressBarjogo)

    }
}