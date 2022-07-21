package com.example.cursoappium

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatTextView
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.cursoappium.adapters.UsersRecyclerAdapter
import com.example.cursoappium.model.User
import sql.DatabaseHelper

open class ListUsers : AppCompatActivity() {

    private val activity = this@ListUsers
    private lateinit var textViewName: AppCompatTextView
    private lateinit var recyclerViewUsers: RecyclerView
    private lateinit var databaseHelper: DatabaseHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_listar_usuarios)
        initViews()
        initObjects()
    }

    private fun initViews(){
        textViewName = findViewById<AppCompatTextView>(R.id.textViewName)
        // getting the recyclerview by its id
        recyclerViewUsers = findViewById<RecyclerView>(R.id.recyclerViewUsers)
    }

    private fun initObjects(){
        // this creates a vertical layout Manager
        val mLayoutManager = LinearLayoutManager(applicationContext)
        recyclerViewUsers.layoutManager = mLayoutManager

        // ArrayList of class ItemsViewModel
        var listUsers = ArrayList<User>()
        databaseHelper = DatabaseHelper(activity)
        listUsers = databaseHelper.getAllUser() as ArrayList<User>

        recyclerViewUsers.itemAnimator = DefaultItemAnimator()
        recyclerViewUsers.setHasFixedSize(true)
        val adapter = UsersRecyclerAdapter(listUsers)
        recyclerViewUsers.adapter = adapter



//        val emailFromIntent = intent.getStringExtra("EMAIL")
//        textViewName.text = emailFromIntent


    }
}




