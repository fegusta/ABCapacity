package com.fegusta.abcapacity.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.constraintlayout.widget.ConstraintLayout
import com.fegusta.abcapacity.R
import com.fegusta.abcapacity.helpers.InputValidation
import com.fegusta.abcapacity.model.User
import com.fegusta.abcapacity.repository.UserRepository
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout

class RegisterActivity : AppCompatActivity(), View.OnClickListener {

    private val activity = this@RegisterActivity

    private lateinit var constraintLayoutRegister: ConstraintLayout

    private lateinit var tilRegisterName: TextInputLayout
    private lateinit var tilRegisterEmail: TextInputLayout
    private lateinit var tilRegisterPassword: TextInputLayout
    private lateinit var tilRegisterConfirmPassword: TextInputLayout

    private lateinit var textRegisterName: TextInputEditText
    private lateinit var textRegisterEmail: TextInputEditText
    private lateinit var textRegisterPassword: TextInputEditText
    private lateinit var textRegisterConfirmPassword: TextInputEditText

    private lateinit var buttonRegisterUser: Button
    private lateinit var textButtonGoToLoginPage: TextView

    private lateinit var inputValidation: InputValidation
    private lateinit var userRepository: UserRepository


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        initViews()
        initListeners()
        initObjects()

    }

    private fun initViews() {

        constraintLayoutRegister = findViewById<ConstraintLayout>(R.id.constraintLayoutRegister)

        tilRegisterName = findViewById<TextInputLayout>(R.id.tilRegisterName)
        tilRegisterEmail = findViewById<TextInputLayout>(R.id.tilRegisterEmail)
        tilRegisterPassword = findViewById<TextInputLayout>(R.id.tilRegisterPassword)
        tilRegisterConfirmPassword = findViewById<TextInputLayout>(R.id.tilRegisterConfirmPassword)

        textRegisterName = findViewById<TextInputEditText>(R.id.textRegisterName)
        textRegisterEmail = findViewById<TextInputEditText>(R.id.textRegisterEmail)
        textRegisterPassword = findViewById<TextInputEditText>(R.id.textRegisterPassword)
        textRegisterConfirmPassword = findViewById<TextInputEditText>(R.id.textRegisterConfirmPassword)

        buttonRegisterUser = findViewById<Button>(R.id.buttonRegisterUser)
        textButtonGoToLoginPage = findViewById<TextView>(R.id.textButtonGoToLoginPage)
    }

    private fun initListeners() {
        buttonRegisterUser!!.setOnClickListener(this)
        textButtonGoToLoginPage!!.setOnClickListener(this)
    }

    private fun initObjects() {
        userRepository = UserRepository(activity)
        inputValidation = InputValidation(activity)
    }

    override fun onClick(v: View) {
        when (v.id) {
            R.id.buttonRegisterUser -> postDataToSQLite()

            R.id.textButtonGoToLoginPage -> finish()
        }
    }

    private fun postDataToSQLite() {

        if (!inputValidation!!.isInputEditTextFilled(textRegisterName!!,tilRegisterName!!, getString(R.string.error_message_name))) {
            return
        }
        if (!inputValidation!!.isInputEditTextFilled(textRegisterEmail!!,tilRegisterEmail!!, getString(R.string.error_message_email))) {
            return
        }
        if (!inputValidation!!.isInputEditTextEmail(textRegisterEmail!!,tilRegisterEmail!!, getString(R.string.error_message_invalid_email))) {
            return
        }
        if (!inputValidation!!.isInputEditTextFilled(textRegisterPassword!!,tilRegisterPassword!!, getString(R.string.error_message_password))) {
            return
        }
        if (!inputValidation!!.isInputEditTextMatches(textRegisterPassword,textRegisterConfirmPassword, tilRegisterConfirmPassword,getString(
                        R.string.error_message_password_invalid))) {
            return
        }
        if(!userRepository!!.checkUser(textRegisterEmail.text.toString().trim())) {
            var user = User(
                name = textRegisterName.text.toString().trim(),
                email = textRegisterEmail.text.toString().trim(),
                password = textRegisterPassword.text.toString().trim()
            )

            userRepository!!.save(user)
            Snackbar.make(constraintLayoutRegister!!, getString(R.string.register_success_message), Snackbar.LENGTH_LONG).show()
            emptyInputEditText()
        }

        else {
            // Snack Bar to show error message that record already exists
            Snackbar.make(constraintLayoutRegister!!, getString(R.string.error_email_exists), Snackbar.LENGTH_LONG).show()
        }
    }

    private fun emptyInputEditText() {
        textRegisterName!!.text = null
        textRegisterEmail!!.text = null
        textRegisterPassword!!.text = null
        textRegisterConfirmPassword!!.text = null
    }
}