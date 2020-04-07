package ui.ux.designui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.widget.Toolbar

class Detail : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //tem que criar uma activity pra detalhe
        setContentView(R.layout.activity_detail)
        val navBarTitle = intent.getStringExtra("Email")

        val toolbar: Toolbar = findViewById(R.id.main_toolbar)
        setSupportActionBar(toolbar)
        supportActionBar?.title = navBarTitle


/*
                     //Puxando as id de text view


        val nomeId : TextView = findViewById(R.id.textoDetalhe)
        val cel : TextView = findViewById(R.id.textoDetalhe2)

                    // Puxando os intents lá do MyAdapter.KT (Linha 43 em diante)

        val nome = intent.getStringExtra("Nome")
        val celular = intent.getStringExtra("Celular")


                    //Setando o valor na textview
        cel.setText(celular).toString()
        nomeId.setText(nome).toString()



*/



    }
}
