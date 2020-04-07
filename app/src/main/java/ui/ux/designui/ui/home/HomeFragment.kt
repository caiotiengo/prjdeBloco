package ui.ux.designui.ui.home

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.OrientationHelper
import androidx.recyclerview.widget.RecyclerView
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.QuerySnapshot
import kotlinx.android.synthetic.main.fragment_home.*
import ui.ux.designui.*

class HomeFragment : Fragment() {

    private lateinit var homeViewModel: HomeViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_home, container, false)
        return root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        homeViewModel =  ViewModelProviders.of(this).get(HomeViewModel::class.java)
        val db = FirebaseFirestore.getInstance()

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
                    my_recycler_view.layoutManager = LinearLayoutManager(activity)
                    my_recycler_view.addItemDecoration(DividerItemDecoration(activity, OrientationHelper.VERTICAL))
                    my_recycler_view.adapter = MyAdapter(lists)
                    Log.d("Sucesso", lists.toString())
                } else {
                    Log.d("Birou", "Error getting documents: ", task.exception)
                }
            })

    }



}