package com.example.autenticador

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class RegisterActivity : AppCompatActivity() {

    private lateinit var  auth : FirebaseAuth
    private lateinit var inputEmail : EditText
    private lateinit var inputPassword : EditText
    private lateinit var btnRegister : Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        auth = Firebase.auth
        inputEmail = findViewById(R.id.inputUsername)
        inputPassword = findViewById(R.id.inputPassword)
        btnRegister = findViewById(R.id.btnRegister)

        btnRegister.setOnClickListener {
            performRegister()
        }

    }

    fun performRegister(){
        if(inputEmail.text.isEmpty() && inputPassword.text.isEmpty()){
            Toast.makeText(this,  "Um dos campos está vazio!", Toast.LENGTH_SHORT).show()
            return
        }

        val userEmail = inputEmail.text.toString()
        val userPassword = inputPassword.text.toString()
        auth.createUserWithEmailAndPassword(userEmail, userPassword)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    Toast.makeText(this, "Cadastrado com sucesso! \uD83D\uDE00", Toast.LENGTH_SHORT).show()
                    val intent = Intent(this, MainActivity::class.java)
                    startActivity(intent)
                } else {
                    Toast.makeText(this, "Falha ao cadastrar! \uD83D\uDE1E", Toast.LENGTH_SHORT).show()
                }
            }
    }
}