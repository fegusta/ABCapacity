package com.fegusta.abcapacity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import com.fegusta.abcapacity.repository.UserRepository
import com.fegusta.abcapacity.ui.RegisterActivity
import com.google.android.material.textfield.TextInputEditText

class LoginActivity : AppCompatActivity(), View.OnClickListener {
    private val activity = this@LoginActivity

    private lateinit var userRepository: UserRepository
    private lateinit var buttonLoginUsuario: Button
    private lateinit var textCadastrar: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        var user = findViewById<TextInputEditText>(R.id.etUser).text
        var pass = findViewById<TextInputEditText>(R.id.etPassword).text

        initViews()
        initListeners()
        initObjects()

    }

    private fun initViews() {
        buttonLoginUsuario = findViewById<Button>(R.id.buttonRegisterUser)
        textCadastrar = findViewById<TextView>(R.id.textButtonGoToLoginPage)
    }

    private fun initListeners() {
        buttonLoginUsuario!!.setOnClickListener(this)
        textCadastrar!!.setOnClickListener(this)
    }

    private fun initObjects() {

        userRepository = UserRepository(activity)


    }


    private fun validarCampos() : Boolean{
        var user = findViewById<TextInputEditText>(R.id.etUser)
        var pass = findViewById<TextInputEditText>(R.id.etPassword)
        if(user.text.isNullOrEmpty()) {
            user.setError(getString(R.string.preencher_user))
            return false
        }
        if(pass.text.isNullOrEmpty()) {
            pass.setError(getString(R.string.preencher_senha))
            return false
        }
        return true
    }

    override fun onClick(v: View) {
        when (v.id) {
            R.id.buttonRegisterUser -> {
                validarCampos()
                verifyFromSQLite()
            }
            R.id.textButtonGoToLoginPage -> {
                // Navigate to RegisterActivity
                val intentRegister = Intent(applicationContext, RegisterActivity::class.java)
                startActivity(intentRegister)
            }
        }
    }

    private fun verifyFromSQLite() {

        if (userRepository.checkUser(findViewById<TextInputEditText>(R.id.etUser)!!.text.toString().trim() , findViewById<TextInputEditText>(R.id.etPassword)!!.text.toString().trim())) {


            Toast.makeText(this,"ENCONTRADO",Toast.LENGTH_SHORT).show()


        } else {

            // Snack Bar to show success message that record is wrong
            Toast.makeText(this,"USUARIO NAO ENCONTRADO",Toast.LENGTH_SHORT).show()
        }
    }

}