package com.fegusta.abcapacity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import com.fegusta.abcapacity.model.Quest
import com.fegusta.abcapacity.repository.QuestRepository

class JogoActivity : AppCompatActivity(), View.OnClickListener {

    private val activity = this@JogoActivity

    private lateinit var operacao: String
    private var questId: Int = 0
    private lateinit var resposta: String
    private lateinit var quest: Quest


    private lateinit var textViewPergunta: TextView
    private lateinit var textoResposta: TextView

    private lateinit var buttonA: Button
    private lateinit var buttonB: Button
    private lateinit var buttonResponder: Button

    private lateinit var progressBarjogo: ProgressBar

    private lateinit var questRepository: QuestRepository

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_jogo)
        initViews()
        initListeners()
        initObjects()
        chargeElementsOnLayout()
    }

    private fun initViews() {
        textViewPergunta = findViewById<TextView>(R.id.textViewPergunta)
        textoResposta = findViewById<TextView>(R.id.textoResposta)

        buttonA = findViewById<Button>(R.id.buttonA)
        buttonB = findViewById<Button>(R.id.buttonB)
        buttonResponder = findViewById<Button>(R.id.buttonResponder)

        progressBarjogo = findViewById<ProgressBar>(R.id.progressBarjogo)
    }

    private fun initListeners() {
        buttonA.setOnClickListener(this)
        buttonB.setOnClickListener(this)
        buttonResponder.setOnClickListener(this)
    }

    private fun initObjects() {
        questRepository = QuestRepository(activity)
        resposta = ""
        questId = intent.getIntExtra("id",0)
        quest = questRepository.getQuest(questId)
    }

    private fun chargeElementsOnLayout() {
        textViewPergunta.setText(quest.question)
        buttonA.setText(quest.alternativaA)
        buttonB.setText(quest.alternativaB)
    }

    override fun onClick(v: View) {
        when(v.id){
            R.id.buttonA -> {
                textoResposta.setText(questRepository.getQuest(questId).alternativaA)
                resposta = "a"
            }

            R.id.buttonB -> {
                textoResposta.setText(questRepository.getQuest(questId).alternativaB)
                resposta = "b"
            }

            R.id.buttonResponder -> {
                if (resposta == quest.answer) {
                    Toast.makeText(this, "CORRETO",Toast.LENGTH_SHORT).show()
                    progressBarjogo.progress += 1
                } else {
                    Toast.makeText(this, "ERRADO",Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
}