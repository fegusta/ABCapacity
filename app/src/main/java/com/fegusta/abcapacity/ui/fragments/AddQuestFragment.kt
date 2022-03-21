package com.fegusta.abcapacity.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.Spinner
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import com.fegusta.abcapacity.R
import com.fegusta.abcapacity.model.Level
import com.fegusta.abcapacity.model.Quest
import com.fegusta.abcapacity.repository.LevelRepository
import com.fegusta.abcapacity.repository.QuestRepository
import com.google.android.material.textfield.TextInputEditText


class AddQuestFragment : Fragment(), View.OnClickListener {

    private lateinit var buttonCadastrar: Button

    private lateinit var editTextQuestionFragment: TextInputEditText
    private lateinit var editTextAlternativaAFragment: TextInputEditText
    private lateinit var editTextAlternativaBFragment: TextInputEditText
    private lateinit var editTextAnswerFragment: TextInputEditText

    private lateinit var spinnerAddQuestFragment: Spinner

    private lateinit var questRepository: QuestRepository
    private lateinit var levelRepository: LevelRepository

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_add_quest, container, false)
        initView(view)
        initListeners(view)
        initObjects(view)
        initSpinner(view)
        return view
    }

    private fun initView(view: View) {
        buttonCadastrar = view.findViewById<Button>(R.id.buttonRegisterQuest)

        editTextQuestionFragment = view.findViewById<TextInputEditText>(R.id.editTextQuestionFragment)
        editTextAlternativaAFragment = view.findViewById<TextInputEditText>(R.id.editTextAlternativaAFragment)
        editTextAlternativaBFragment = view.findViewById<TextInputEditText>(R.id.editTextAlternativaBFragment)
        editTextAnswerFragment = view.findViewById<TextInputEditText>(R.id.editTextAnswerFragment)

        spinnerAddQuestFragment = view.findViewById<Spinner>(R.id.spinnerAddQuestFragment)
    }

    private fun initListeners(view: View) {
        buttonCadastrar.setOnClickListener(this)
    }

    private fun initObjects(view: View) {
        questRepository = QuestRepository(view.context)
        levelRepository = LevelRepository(view.context)
    }

    private fun initSpinner(view: View) {
        var listLevel = levelRepository.getLevels()
        var levelsId = ArrayList<Int>()
        for (level in listLevel){
            levelsId.add(level.id)
        }
        val adapter = ArrayAdapter(view.context,android.R.layout.simple_spinner_dropdown_item,levelsId)
        spinnerAddQuestFragment.adapter = adapter
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
            levelId = spinnerAddQuestFragment.selectedItemPosition,
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
                Toast.makeText(view.context, "NAONAONAO", Toast.LENGTH_SHORT).show()
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