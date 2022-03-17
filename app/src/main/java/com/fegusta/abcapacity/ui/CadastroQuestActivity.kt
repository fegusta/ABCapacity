package com.fegusta.abcapacity.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AlertDialog
import com.fegusta.abcapacity.R
import com.fegusta.abcapacity.model.Quest
import com.fegusta.abcapacity.repository.QuestRepository

class CadastroQuestActivity : AppCompatActivity() {

    private lateinit var adapter: ArrayAdapter<CharSequence>
    private lateinit var operacao: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cadastro_quest)

        var buttonCadastrar = findViewById<Button>(R.id.buttonRegisterUser)

        buttonCadastrar.setOnClickListener {
            Toast.makeText(this,"TESTE",Toast.LENGTH_LONG).show()
            salvarQuest()
        }
    }

    private fun salvarQuest() {
        val quest = Quest(
            question = findViewById<EditText>(R.id.editTextQuestion).text.toString(),
            alternativaA = findViewById<EditText>(R.id.editTextAlternativaA).text.toString(),
            alternativaB = findViewById<EditText>(R.id.editTextAlternativaB).text.toString(),
            answer = findViewById<EditText>(R.id.editTextAnswer).text.toString()
        )

        val repo = QuestRepository(this)
        val id = repo.save(quest)
        if (id > 0) {
            val builderDialog = AlertDialog.Builder(this)
            builderDialog.setTitle("Sucesso!")
            builderDialog.setMessage("Sua quest foi gravado com sucesso!\n\nDeseja cadastrar outra quest?")

            builderDialog.setPositiveButton("Sim") { _, _ ->
                limparFormulario()
            }

            builderDialog.setNegativeButton("Não") { _, _ ->
                onBackPressed()
            }

        }
    }

    private fun limparFormulario() {
        findViewById<TextView>(R.id.editTextQuestion).setText("")
        findViewById<TextView>(R.id.editTextAlternativaA).setText("")
        findViewById<CheckBox>(R.id.editTextAlternativaB).setText("")
        findViewById<TextView>(R.id.editTextAnswer).setText("")
    }
}