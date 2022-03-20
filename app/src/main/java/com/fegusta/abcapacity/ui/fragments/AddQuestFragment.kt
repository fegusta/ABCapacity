package com.fegusta.abcapacity.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.appcompat.app.AlertDialog
import com.fegusta.abcapacity.R
import com.fegusta.abcapacity.model.Quest
import com.fegusta.abcapacity.repository.QuestRepository
import com.google.android.material.textfield.TextInputEditText


class AddQuestFragment : Fragment(), View.OnClickListener {

    private lateinit var buttonCadastrar: Button

    private lateinit var editTextQuestionFragment: TextInputEditText
    private lateinit var editTextAlternativaAFragment: TextInputEditText
    private lateinit var editTextAlternativaBFragment: TextInputEditText
    private lateinit var editTextAnswerFragment: TextInputEditText

    private lateinit var questRepository: QuestRepository

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_add_quest, container, false)
        initView(view)
        initListeners(view)
        initObjects(view)
        return view
    }

    private fun initView(view: View) {
        buttonCadastrar = view.findViewById<Button>(R.id.buttonRegisterQuest)

        editTextQuestionFragment = view.findViewById<TextInputEditText>(R.id.editTextQuestionFragment)
        editTextAlternativaAFragment = view.findViewById<TextInputEditText>(R.id.editTextAlternativaAFragment)
        editTextAlternativaBFragment = view.findViewById<TextInputEditText>(R.id.editTextAlternativaBFragment)
        editTextAnswerFragment = view.findViewById<TextInputEditText>(R.id.editTextAnswerFragment)
    }

    private fun initListeners(view: View) {
        buttonCadastrar.setOnClickListener(this)
    }

    private fun initObjects(view: View) {
        questRepository = QuestRepository(view.context)
    }

    override fun onClick(view: View) {
        when (view.id) {
            R.id.buttonRegisterQuest -> {
                salvarQuest(view)
            }
        }
    }

    private fun salvarQuest(view: View) {
        val quest = Quest(
                question = editTextQuestionFragment.text.toString(),
                alternativaA = editTextAlternativaAFragment.text.toString(),
                alternativaB = editTextAlternativaBFragment.text.toString(),
                answer = editTextAnswerFragment.text.toString()
        )

        val id = questRepository.save(quest)
        if (id > 0) {
            val builderDialog = AlertDialog.Builder(view.context)
            builderDialog.setTitle("Sucesso!")
            builderDialog.setMessage("Sua quest foi gravado com sucesso!\n\nDeseja cadastrar outra quest?")

            builderDialog.setPositiveButton("Sim") { _, _ ->
                limparFormulario()
            }

            builderDialog.setNegativeButton("Não") { _, _ ->
                Toast.makeText(view.context,"NAONAONAO",Toast.LENGTH_SHORT).show()
            }

            builderDialog.show()

        }
    }

    private fun limparFormulario() {
        editTextQuestionFragment.setText("")
        editTextAlternativaAFragment.setText("")
        editTextAlternativaBFragment.setText("")
        editTextAnswerFragment.setText("")
    }

}