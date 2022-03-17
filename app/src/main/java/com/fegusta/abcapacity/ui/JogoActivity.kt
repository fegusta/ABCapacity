package com.fegusta.abcapacity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import com.fegusta.abcapacity.model.Level
import com.fegusta.abcapacity.model.Quest
import com.fegusta.abcapacity.repository.QuestRepository

class JogoActivity : AppCompatActivity() {

    private lateinit var operacao: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_jogo)
        var id = intent.getIntExtra("id",0)
        var quest = Quest()
        val repository = QuestRepository(this)
        var resposta = ""

        quest = repository.getQuest(id)

        var question = 0

        var buttonA = findViewById<Button>(R.id.buttonA)
        var buttonB = findViewById<Button>(R.id.buttonB)

        findViewById<TextView>(R.id.textViewPergunta).setText(quest.question)
        buttonA.setText(quest.alternativaA)
        buttonB.setText(quest.alternativaB)

        var textoResposta = findViewById<TextView>(R.id.textoResposta)

        buttonA.setOnClickListener {
            textoResposta.setText(quest.alternativaA)
            resposta = "a"
        }

        buttonB.setOnClickListener {
            textoResposta.setText(quest.alternativaB)
            resposta = "b"
        }

        var progressBarjogo = findViewById<ProgressBar>(R.id.progressBarjogo)

        var buttonResponder = findViewById<Button>(R.id.buttonResponder)
        buttonResponder.setOnClickListener {
            if (resposta == quest.answer) {
                Toast.makeText(this, "CORRETO",Toast.LENGTH_LONG).show()
                progressBarjogo.progress += 1
            } else {
                Toast.makeText(this, "ERRADO",Toast.LENGTH_LONG).show()
            }
        }


    }
}