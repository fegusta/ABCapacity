package com.fegusta.abcapacity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.fegusta.abcapacity.model.Level
import com.fegusta.abcapacity.model.Quest
import com.fegusta.abcapacity.repository.LevelRepository
import com.fegusta.abcapacity.repository.QuestRepository
import com.fegusta.abcapacity.ui.RegisterActivity

class JogoActivity : AppCompatActivity(), View.OnClickListener {

    private val activity = this@JogoActivity

    private var levelId: Int = 0
    private var count: Int = 0
    private lateinit var resposta: String
    private lateinit var listQuest: ArrayList<Quest>


    private lateinit var textViewPergunta: TextView
    private lateinit var textoResposta: TextView

    private lateinit var buttonA: Button
    private lateinit var buttonB: Button
    private lateinit var buttonResponder: Button

    private lateinit var progressBarjogo: ProgressBar

    private lateinit var questRepository: QuestRepository
    private lateinit var levelRepository: LevelRepository

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
        levelRepository = LevelRepository(activity)
        resposta = ""
        levelId = intent.getIntExtra("id",0)
        listQuest = questRepository.getQuestByLevelId(levelId - 1)
        progressBarjogo.max = listQuest.size
    }

    private fun chargeElementsOnLayout() {
        textViewPergunta.setText(listQuest[count].question)
        buttonA.setText(listQuest[count].alternativaA)
        buttonB.setText(listQuest[count].alternativaB)
    }

    override fun onClick(v: View) {
        when(v.id){
            R.id.buttonA -> {
                textoResposta.setText(listQuest[count].alternativaA)
                resposta = "a"
            }

            R.id.buttonB -> {
                textoResposta.setText(listQuest[count].alternativaB)
                resposta = "b"
            }

            R.id.buttonResponder -> {
                if (resposta == listQuest[count].answer) {
                    progressBarjogo.progress++
                    count++
                    if (count < listQuest.size) {
                        toDo()
                    } else {
                        val builderDialog = AlertDialog.Builder(v.context)
                        builderDialog.setTitle("Sucesso!")
                        builderDialog.setMessage("Lição completada! Parabens!")

                        builderDialog.setPositiveButton("Voltar ao menu") { _, _ ->
                            val intent = Intent(v.context, MainActivity::class.java)
                            startActivity(intent)
                        }

                        builderDialog.show()
                    }


                } else {
                    Toast.makeText(this, "ERRADO",Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    private fun toDo() {
        textViewPergunta.setText(listQuest[count].question)
        textoResposta.setText("")
        buttonA.setText(listQuest[count].alternativaA)
        buttonB.setText(listQuest[count].alternativaB)
    }
}