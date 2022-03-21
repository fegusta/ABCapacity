package com.fegusta.abcapacity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import com.fegusta.abcapacity.helpers.InputValidation
import com.fegusta.abcapacity.data.repository.UserRepository
import com.fegusta.abcapacity.ui.RegisterActivity
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout

class LoginActivity : AppCompatActivity(), View.OnClickListener {
    private val activity = this@LoginActivity

    private lateinit var constraintLayoutRegister: ConstraintLayout

    private lateinit var buttonLoginUsuario: Button
    private lateinit var textButtonGoToRegisterPage: TextView

    private lateinit var tilLoginUser: TextInputLayout
    private lateinit var tilLoginPassword: TextInputLayout

    private lateinit var  textLoginUser: TextInputEditText
    private lateinit var  textLoginPassword: TextInputEditText

    private lateinit var inputValidation: InputValidation
    private lateinit var userRepository: UserRepository

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        initViews()
        initListeners()
        initObjects()

    }

    private fun initViews() {

        constraintLayoutRegister = findViewById<ConstraintLayout>(R.id.constraintLayoutLogin)

        tilLoginUser = findViewById<TextInputLayout>(R.id.tilLoginUser)
        tilLoginPassword = findViewById<TextInputLayout>(R.id.tilLoginPassword)

        textLoginUser = findViewById<TextInputEditText>(R.id.textLoginUser)
        textLoginPassword = findViewById<TextInputEditText>(R.id.textLoginPassword)

        buttonLoginUsuario = findViewById<Button>(R.id.buttonRegisterUser)
        textButtonGoToRegisterPage = findViewById<TextView>(R.id.textButtonGoToRegisterPage)
    }

    private fun initListeners() {
        buttonLoginUsuario!!.setOnClickListener(this)
        textButtonGoToRegisterPage!!.setOnClickListener(this)
    }

    private fun initObjects() {
        userRepository = UserRepository(activity)
        inputValidation = InputValidation(activity)
    }

    override fun onClick(v: View) {
        when (v.id) {
            R.id.buttonRegisterUser -> {
                verifyFromSQLite()
            }
            R.id.textButtonGoToRegisterPage -> {
                // Navigate to RegisterActivity
                val intentRegister = Intent(applicationContext, RegisterActivity::class.java)
                startActivity(intentRegister)
            }
        }
    }

    private fun verifyFromSQLite() {

        if (!inputValidation!!.isInputEditTextFilled(textLoginUser!!,tilLoginUser!!, getString(R.string.error_message_email))) {
            return
        }
        if (!inputValidation!!.isInputEditTextFilled(textLoginUser!!,tilLoginPassword!!, getString(R.string.error_message_password))) {
            return
        }
        if (!inputValidation!!.isInputEditTextEmail(textLoginUser!!,tilLoginUser!!, getString(R.string.error_message_invalid_email))) {
            return
        }

        if (userRepository.checkUser(textLoginUser!!.text.toString().trim() , textLoginPassword!!.text.toString().trim())) {

            val accountsIntent = Intent(activity, MainActivity::class.java)
            accountsIntent.putExtra("EMAIL", textLoginUser!!.text.toString().trim { it <= ' ' })
            emptyInputEditText()
            startActivity(accountsIntent)

        } else {
            // Snack Bar to show success message that record is wrong
            Snackbar.make(constraintLayoutRegister!!, getString(R.string.error_valid_email_password), Snackbar.LENGTH_LONG).show()
        }
    }

    private fun emptyInputEditText() {
        textLoginUser!!.text = null
        textLoginPassword!!.text = null
    }
}