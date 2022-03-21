package com.fegusta.abcapacity.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.fegusta.abcapacity.R
import com.fegusta.abcapacity.data.model.Level
import com.fegusta.abcapacity.data.repository.LevelRepository
import com.google.android.material.textfield.TextInputEditText

class AddLevelFragment : Fragment(), View.OnClickListener  {

    private lateinit var buttonRegisterLevel: Button

    private lateinit var editTextInfoLevelFragment: TextInputEditText
    private lateinit var editTextNameLevelFragment: TextInputEditText

    private lateinit var levelRepository: LevelRepository

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_add_level, container, false)
        initView(view)
        initListeners(view)
        initObjects(view)
        return view
    }

    private fun initView(view: View) {
        buttonRegisterLevel = view.findViewById<Button>(R.id.buttonRegisterLevel)

        editTextInfoLevelFragment = view.findViewById<TextInputEditText>(R.id.editTextInfoLevelFragment)
        editTextNameLevelFragment = view.findViewById<TextInputEditText>(R.id.editTextNameLevelFragment)
    }

    private fun initListeners(view: View) {
        buttonRegisterLevel.setOnClickListener(this)
    }

    private fun initObjects(view: View) {
        levelRepository = LevelRepository(view.context)
    }

    override fun onClick(view: View) {
        when (view.id) {
            R.id.buttonRegisterLevel -> {
                salvarLevel(view)
            }
        }
    }

    private fun salvarLevel(view: View) {
        val level = Level(
            nameLevel = editTextNameLevelFragment.text.toString(),
            infoLevel = editTextInfoLevelFragment.text.toString()
        )

        val id = levelRepository.save(level)
        if (id > 0) {
            val builderDialog = AlertDialog.Builder(view.context)
            builderDialog.setTitle("Sucesso!")
            builderDialog.setMessage("Seu level foi gravado com sucesso!\n\nDeseja cadastrar outro level?")

            builderDialog.setPositiveButton("Sim") { _, _ ->
                limparFormulario()
            }

            builderDialog.setNegativeButton("Não") { _, _ ->
                Toast.makeText(view.context,"NAONAONAO", Toast.LENGTH_SHORT).show()
            }
            builderDialog.show()
        }
    }

    private fun limparFormulario() {
        editTextInfoLevelFragment.setText("")
    }

}