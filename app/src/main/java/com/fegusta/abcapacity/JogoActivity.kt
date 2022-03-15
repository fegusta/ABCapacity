package com.fegusta.abcapacity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView

class JogoActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_jogo)

        var textoResposta = findViewById<TextView>(R.id.textoResposta)
        var buttonA = findViewById<Button>(R.id.buttonA)
        var buttonB = findViewById<Button>(R.id.buttonB)

        buttonA.setOnClickListener {
            textoResposta.setText("A")
        }

        buttonB.setOnClickListener {
            textoResposta.setText("B")
        }
    }
}