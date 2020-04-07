package ui.ux.designui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_main.btn_registrar
import kotlinx.android.synthetic.main.activity_registrar.*
import com.google.firebase.firestore.FirebaseFirestore






class Registrar : AppCompatActivity() {
    private val lists: MutableList<MyModel> = mutableListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registrar)
        btn_registrar.setOnClickListener {
            val email = edt_email.text.toString()
            val password = edt_password.text.toString()
            if(email.isNullOrBlank()){
                Toast.makeText(baseContext,"Preencha os campos necessarios: EMAIL.", Toast.LENGTH_SHORT).show()
            }else if(password.isNullOrBlank()){
                Toast.makeText(baseContext,"Preencha os campos necessarios: SENHA.", Toast.LENGTH_SHORT).show()

            }else{
                createUser(email, password)
            }

        }
        btn_voltar.setOnClickListener{
            finish()
        }

    }
    private fun createUser(email: String, password:String){
        val mAuth = FirebaseAuth.getInstance()
        val db = FirebaseFirestore.getInstance()
        mAuth.createUserWithEmailAndPassword(
            email, password
        ).addOnSuccessListener {
            val usuario = mAuth.currentUser
            val nome = edt_nome.text.toString()
            val DOB = edt_nascimento.text.toString()
            val cel = edt_nascimento.text.toString()
            val cpf = edt_cpf.text.toString()
            val emailUser = usuario!!.email
            val cep = edt_cep.text.toString()
            val ong = switch_ong.run { text.toString() }

            val intent = Intent(this, Registrar::class.java)
            this.startActivity(intent)


            db.collection("usuarios").document(usuario!!.uid).set(mapOf(
                "Name" to nome,
                "Data de Nascimento" to DOB,
                "Celular" to cel,
                "CPF" to cpf,
                "Email" to emailUser,
                "CEP" to cep,
                "ONG" to ong
            )).addOnSuccessListener {
                finish()
                Toast.makeText(baseContext,"Usuario criado com Sucesso", Toast.LENGTH_SHORT).show()
                val intent = Intent(this, HomePage::class.java)
                this.startActivity(intent)


                //Direcionar daqui para a home page

            }.addOnFailureListener {
                Toast.makeText(baseContext, "Problemas ao cadastrar o usúario:${it.message.toString()}", Toast.LENGTH_SHORT).show()

            }
        }.addOnFailureListener {
            Toast.makeText(baseContext, "Problemas ao cadastrar o usúario:${it.message.toString()}", Toast.LENGTH_SHORT).show()

        }


    }
}
