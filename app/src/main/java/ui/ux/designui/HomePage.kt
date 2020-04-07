package ui.ux.designui

import android.os.Bundle
import android.util.Log
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import androidx.drawerlayout.widget.DrawerLayout
import com.google.android.material.navigation.NavigationView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import android.view.Menu
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.QueryDocumentSnapshot
import com.google.firebase.firestore.QuerySnapshot
import com.google.android.gms.tasks.Task
import androidx.annotation.NonNull
import com.google.android.gms.tasks.OnCompleteListener
import androidx.core.content.ContextCompat.getSystemService
import android.icu.lang.UCharacter.GraphemeClusterBreak.T
import androidx.fragment.app.FragmentActivity
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.OrientationHelper
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.content_home_page.*
import kotlinx.android.synthetic.main.fragment_home.*


class HomePage : AppCompatActivity() {

    private lateinit var appBarConfiguration: AppBarConfiguration

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home_page)
        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)
        val db = FirebaseFirestore.getInstance()
       // val mAuth = FirebaseAuth.getInstance()

//LISTAGEM FIREBASE
        db.collection("usuarios").get()
            .addOnCompleteListener(OnCompleteListener<QuerySnapshot> { task ->
//                val usuario = mAuth.currentUser

                if (task.isSuccessful) {
                    val lists: MutableList<MyModel> = mutableListOf()
                    for (document in task.result!!) {
                        if (document.data["ONG"] == true) {
                            val nome = document.data["Nome"].toString()
                            val email = document.data["Email"].toString()
                            val cep = document.data["CEP"].toString()
                            val ong = document.data["ONG"].toString()


                            lists.apply {
                                add(MyModel(nome, email, cep, ong))
                            }
                        }
                    }
                    my_recycler_view.layoutManager = LinearLayoutManager(this)
                    my_recycler_view.addItemDecoration(DividerItemDecoration(this, OrientationHelper.VERTICAL))
                    my_recycler_view.adapter = MyAdapter(lists)
                    Log.d("Sucesso", lists.toString())
                } else {
                    Log.d("Birou", "Error getting documents: ", task.exception)
                }
            })

        val drawerLayout: DrawerLayout = findViewById(R.id.drawer_layout)
        val navView: NavigationView = findViewById(R.id.nav_view)
        val navController = findNavController(R.id.nav_host_fragment)
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.nav_home, R.id.nav_gallery, R.id.nav_slideshow
            ), drawerLayout
        )
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.home_page, menu)
        return true
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment)
        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }
}
