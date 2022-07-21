package com.example.cursoappium

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import com.example.cursoappium.helpers.InputValidation
import com.example.cursoappium.model.User
import com.google.android.material.snackbar.Snackbar
import sql.DatabaseHelper

class RegisterUsers : AppCompatActivity(), View.OnClickListener {

    private val activity = this@RegisterUsers

    private lateinit var viewMessage: View

    private lateinit var textInputLayoutName: TextInputLayout
    private lateinit var textInputLayoutEmail: TextInputLayout
//
    private lateinit var textInputName: TextInputEditText
    private lateinit var textInputEmail: TextInputEditText
//
    private lateinit var inputValidation: InputValidation
    private lateinit var databaseHelper: DatabaseHelper

    private lateinit var botaoCadastrar: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        // hiding the action bar - tá quebrando a activity de register essa poha
//        supportActionBar!!.hide()
//
        initViews()

        initObjects()

        initListener()


        var btn_voltar = findViewById<Button>(R.id.BotaoVoltar) as Button
        btn_voltar.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

//        var btn_cadastrar = findViewById<Button>(R.id.BotaoCadastrar) as Button
//        btn_cadastrar.setOnClickListener {
//            postDataToSQLite()
//        }
    }

    private fun initViews(){
        viewMessage = findViewById(R.id.viewMessage)

        textInputLayoutName = findViewById<View>(R.id.textInputLayoutName) as TextInputLayout
        textInputLayoutEmail = findViewById<View>(R.id.textInputLayoutEmail) as TextInputLayout

        textInputName = findViewById<View>(R.id.TextInputNome) as TextInputEditText
        textInputEmail = findViewById<View>(R.id.TextInputEmail) as TextInputEditText



        botaoCadastrar = findViewById<Button>(R.id.BotaoCadastrar) as Button
//        botaoVoltar = findViewById<Button>(R.id.BotaoVoltar) as Button
    }

//    private fun initiViews(){
//        textInputName = findViewById(R.id.TextInputNome) as TextInputEditText
//        textInputEmail = findViewById(R.id.TextInputEmail) as TextInputEditText
//
//        botaoCadastrar = findViewById(R.id.button_cadastrar) as Button
//    }

    private fun initObjects(){
        inputValidation = InputValidation(activity)
        databaseHelper = DatabaseHelper(activity)
    }

    private fun initListener(){
        botaoCadastrar!!.setOnClickListener(this)
    }

    override fun onClick(v:View){
        when (v.id){
            R.id.BotaoCadastrar -> postDataToSQLite()
        }
    }

    private fun postDataToSQLite(){
        if (!inputValidation!!.isInputEditTextFilled(textInputName, textInputLayoutName,
                        "Insira o nome completo")){
            return
        }
        if (!inputValidation!!.isInputEditTextFilled(textInputEmail, textInputLayoutEmail,
                        "Insira um email válido")){
            return
        }

        if (!databaseHelper!!.checkUser(textInputEmail!!.text.toString().trim())) {

            var user = User(name = textInputName!!.text.toString().trim(),
                            email = textInputEmail!!.text.toString().trim()
                            )

            databaseHelper!!.addUser(user)

            Snackbar.make(viewMessage!!, R.string.toast_message,
                    Snackbar.LENGTH_LONG).show()
                    emptyInputEditText()

        } else {
            Snackbar.make(viewMessage!!, "Email já cadastrado",
                    Snackbar.LENGTH_LONG).show()
        }
    }

    private fun emptyInputEditText(){
        textInputName!!.text = null
        textInputEmail!!.text = null
    }
}