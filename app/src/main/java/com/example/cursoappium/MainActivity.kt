package com.example.cursoappium

import android.app.Dialog
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.Window
import android.widget.Button
import android.widget.TextView
import android.widget.Toast

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        var btn_cadastrar = findViewById<Button>(R.id.button_cadastrar) as Button
        btn_cadastrar.setOnClickListener {
            val intent = Intent(this, RegisterUsers::class.java)
            startActivity(intent)
        }

        var btn_sobre = findViewById<Button>(R.id.button_sobre) as Button
        btn_sobre.setOnClickListener{
            ShowDialog()
        }

        var btn_curso = findViewById<Button>(R.id.button_acessar_o_curso) as Button
        btn_curso.setOnClickListener{
            val intent = Intent(Intent.ACTION_VIEW,
                Uri.parse("https://github.com/clarabez/appium"))
            startActivity(intent)
        }

        var btn_listar_cadastros = findViewById<Button>(R.id.button_listar) as Button
        btn_listar_cadastros.setOnClickListener {
            val intent = Intent(this, ListUsers::class.java)
            startActivity(intent)
        }

    }

    fun ShowDialog(){
        var myDialog = Dialog(this)
        myDialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        myDialog.setContentView(R.layout.activity_dialog)
        myDialog.setTitle("Meu diálogo")

        var text = myDialog.findViewById<View>(R.id.button_ok) as TextView
        text.isEnabled = true
        text.setOnClickListener{
            Toast.makeText(applicationContext, "Obrigada por participar do curso :)", Toast.LENGTH_LONG).show()
            myDialog.cancel()
        }
        myDialog.show()
    }
}