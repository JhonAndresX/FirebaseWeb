package com.example.movilesfirebase

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.FilterQueryProvider
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AlertDialog
import com.google.firebase.analytics.FirebaseAnalytics
import com.google.firebase.auth.FirebaseAuth

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val analytics = FirebaseAnalytics.getInstance(this)
        val bundle = Bundle()
        bundle.putString("message", "Integracion de firebase completa")
        analytics.logEvent("InitScreen", bundle)
        setup()

    }

    private fun setup() {
        title = "Autentificacion"
        val btnRegistrarse = findViewById<Button>(R.id.btnRegistrarse)
        val btnAcceder = findViewById<Button>(R.id.btnAcceder)
        val txtEmail = findViewById<EditText>(R.id.txtemail)
        val txtContraseña = findViewById<EditText>(R.id.txtContraseña)


        btnRegistrarse.setOnClickListener {
            if (txtEmail.text.isNotEmpty() && txtContraseña.text.isNotEmpty()) {
                FirebaseAuth.getInstance().createUserWithEmailAndPassword(
                    txtEmail.text.toString(),
                    txtContraseña.text.toString()
                ).addOnCompleteListener {


                    if (it.isSuccessful) {
                        showhome(it.result?.user?.email ?: "", ProviderType.BASIC)
                    } else {
                        if (txtEmail.text.isEmpty()) {
                            Toast.makeText(this, "Ingrese su email", Toast.LENGTH_SHORT).show()

                        } else if (txtContraseña.text.isEmpty()) {
                            Toast.makeText(this, "Ingrese su contraseña", Toast.LENGTH_SHORT).show()

                        }
                    }


                }
            }
        }
btnAcceder.setOnClickListener {
    if (txtEmail.text.isNotEmpty() && txtContraseña.text.isNotEmpty()) {
        FirebaseAuth.getInstance().signInWithEmailAndPassword(
            txtEmail.text.toString(),
            txtContraseña.text.toString()
        ).addOnCompleteListener {


            if (it.isSuccessful) {
                showhome(it.result?.user?.email ?:"", ProviderType.BASIC)
            } else {
            if (txtEmail.text.isEmpty()) {
                Toast.makeText(this, "Ingrese su email", Toast.LENGTH_SHORT).show()

            } else if (txtContraseña.text.isEmpty()) {
                Toast.makeText(this, "Ingrese su contraseña", Toast.LENGTH_SHORT).show()

            }
        }
        }


    }
}
    }
    private fun showalert(){
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Error")
        builder.setMessage("Se ah producido un error autentificando al usuario")
        builder.setPositiveButton("Aceptar",null)
        val dialog: AlertDialog = builder.create()
        dialog.show()
    }
    private fun showhome(email: String, provider: ProviderType){
val homeIntent = Intent(this,HomeActivity::class.java).apply{
putExtra("email",email)
    putExtra("provider",provider.name)
}
startActivity(homeIntent)
}

}



