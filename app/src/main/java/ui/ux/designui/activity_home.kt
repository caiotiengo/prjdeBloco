package ui.ux.designui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toolbar
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.OrientationHelper
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.android.synthetic.main.activity_home.*

class activity_home : AppCompatActivity() {
    private val lists: MutableList<MyModel> = mutableListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        setSupportActionBar(main_toolbar)

        val actionBar = supportActionBar

        // Set toolbar title/app title
        actionBar!!.title = "DesignUI"

        // Set action bar elevation
        actionBar.elevation = 4.0F

        // Display the app icon in action bar/toolbar
        actionBar.setDisplayShowHomeEnabled(true)
        actionBar.setLogo(R.mipmap.ic_launcher)
        actionBar.setDisplayUseLogoEnabled(true)



        val db = FirebaseFirestore.getInstance()
        db.collection("form")
            .get()
            .addOnSuccessListener { documents ->
                for (document in documents) {
                    Log.d("Success", "${document.id} => ${document.data}")
                        val nome = document.data["Nome"].toString()
                        val email = document.data["Email"].toString()
                        val cep = document.data["CEP"].toString()
                        val celular = document.data["Celular"].toString()

                    lists.apply {
                        add(MyModel( nome, email, cep, celular))
                    }
                }
            }
            .addOnFailureListener { exception ->
                Log.w("Failure", "Error getting documents: ", exception)
            }


        // my_recycler_view.layoutManager = LinearLayoutManager(this)
        // my_recycler_view.addItemDecoration(DividerItemDecoration(this, OrientationHelper.VERTICAL))
        // my_recycler_view.adapter = MyAdapter(lists)

    }
}
