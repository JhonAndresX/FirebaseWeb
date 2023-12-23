package com.example.movilesfirebase

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import com.google.firebase.auth.FirebaseAuth

enum class ProviderType{
    BASIC
}
class HomeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
       val bundle = intent.extras
        val email = bundle?.getString("email")
       val provider = bundle?.getString("provider")
setup(email ?:"",provider ?:"")
    }
private fun setup(email: String, provider:String){
    title = "inicio"
    val txEmail = findViewById<TextView>(R.id.txEmail)
    val btnSalir = findViewById<Button>(R.id.btnRegresar)

    txEmail.text = email


    btnSalir.setOnClickListener{
        FirebaseAuth.getInstance().signOut()
        onBackPressed()
    }



}
}