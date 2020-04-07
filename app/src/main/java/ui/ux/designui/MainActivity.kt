package ui.ux.designui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.AdView
import com.google.android.gms.ads.MobileAds
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_registrar.*
import androidx.core.app.ComponentActivity.ExtraData
import androidx.core.content.ContextCompat.getSystemService
import android.icu.lang.UCharacter.GraphemeClusterBreak.T
import android.util.Log
import com.google.firebase.firestore.FirebaseFirestore
import android.widget.Button as Button


class MainActivity : AppCompatActivity() {
    lateinit var mAdView : AdView
    private val lists: MutableList<MyModel> = mutableListOf()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val btn_registrar = findViewById<Button>(R.id.btn_registrar)


        btn_registrar.setOnClickListener {
            val intent = Intent(this, Registrar::class.java)
            this.startActivity(intent)
        }
        btn_acessar.setOnClickListener {
            val email = edt_login.text.toString()
            val password = edt_senha.text.toString()
           val db = FirebaseAuth.getInstance()
            db.signInWithEmailAndPassword(email, password).addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    // Sign in success, update UI with the signed-in user's information
                    Log.d("Success", "signInWithEmail:success")
                    val intent = Intent(this, HomePage::class.java)
                    this.startActivity(intent)

                    val db = FirebaseFirestore.getInstance()
                    db.collection("form")
                        .get()
                        .addOnSuccessListener { documents ->
                            for (document in documents) {
                                Log.d("Success", "${document.id} => ${document.data}")
                                val nome = document.data["Nome"].toString()
                                val email = document.data["Email"].toString()
                                val cep = document.data["CEP"].toString()
                                val ong = document.data["ONG"].toString()


                                lists.apply {
                                    add(MyModel( nome, email, cep, ong))
                                }
                            }
                        }
                        .addOnFailureListener { exception ->
                            Log.w("Failure", "Error getting documents: ", exception)
                        }


                } else {
                    // If sign in fails, display a message to the user.
                    Log.w("Failure", "signInWithEmail:failure", task.exception)
                    Toast.makeText(baseContext, "Erro ao acessar o APP",
                        Toast.LENGTH_SHORT).show()

                }

                // ...
            }
        }

    }

    private fun createUser(email: String, password:String){
        var mAuth = FirebaseAuth.getInstance()
        mAuth.createUserWithEmailAndPassword(
            email, password
        ).addOnSuccessListener {

        }
    }
}
