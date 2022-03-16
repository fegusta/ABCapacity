package com.fegusta.abcapacity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView

class JogoActivity : AppCompatActivity() {

    private lateinit var operacao: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_jogo)

        var textoResposta = findViewById<TextView>(R.id.textoResposta)
        var buttonA = findViewById<Button>(R.id.buttonA)
        var buttonB = findViewById<Button>(R.id.buttonB)

        operacao = intent.getStringExtra("operacao")!!

        var textViewPergunta = findViewById<TextView>(R.id.textViewPergunta)
        textViewPergunta.setText(intent.getStringExtra("question"))
        buttonA.setText(intent.getStringExtra("alternativaA"))
        buttonB.setText(intent.getStringExtra("alternativaB"))

        buttonA.setOnClickListener {
            textoResposta.setText(intent.getStringExtra("alternativaA"))
        }

        buttonB.setOnClickListener {
            textoResposta.setText(intent.getStringExtra("alternativaB"))
        }
    }
}