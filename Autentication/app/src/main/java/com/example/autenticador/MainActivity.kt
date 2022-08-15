package com.example.autenticador

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class MainActivity : AppCompatActivity() {

    private lateinit var  auth : FirebaseAuth
    private lateinit var inputEmail : EditText
    private lateinit var inputPassword : EditText
    private lateinit var btnLogin : Button
    private lateinit var txtRegister : TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        auth = Firebase.auth
        inputEmail = findViewById(R.id.inputUsername)
        inputPassword = findViewById(R.id.inputPassword)
        btnLogin = findViewById(R.id.btnLogin)
        txtRegister = findViewById(R.id.txtRegister)

        txtRegister.setOnClickListener {
            val intent = Intent(this, RegisterActivity::class.java)
            startActivity(intent)
        }

        btnLogin.setOnClickListener {
            performLogin()
        }

    }

    fun performLogin() {
        if(inputEmail.text.isEmpty() && inputPassword.text.isEmpty()){
            Toast.makeText(this,  "Um dos campos está vazio!", Toast.LENGTH_SHORT).show()
            return
        }

        val userEmail = inputEmail.text.toString()
        val userPassword = inputPassword.text.toString()

        auth.signInWithEmailAndPassword(userEmail, userPassword)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    Toast.makeText(this, "Sucesso ao Logar! \uD83D\uDE00", Toast.LENGTH_SHORT).show()
                    val intent = Intent(this, PrincipalActivity::class.java)
                    startActivity(intent)
                } else {
                    Toast.makeText(this, "Usuário e/ou senha não conferem! \uD83D\uDE1E", Toast.LENGTH_SHORT).show()
                }
            }
    }
}