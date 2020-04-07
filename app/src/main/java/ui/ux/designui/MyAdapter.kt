package ui.ux.designui

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.item.view.*

class MyAdapter(private val lists: MutableList<MyModel>) : RecyclerView.Adapter<MyHolder>() {

    private lateinit var context: Context

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyHolder {
        context = parent.context
        return MyHolder(LayoutInflater.from(context).inflate(R.layout.item, parent, false))

    }

    override fun getItemCount(): Int = lists.size

    override fun onBindViewHolder(holder: MyHolder, position: Int) {
        val list = lists[position]

        val nome = holder.itemView.nome
        val emailUser = holder.itemView.email
        val cel = holder.itemView.celular
        val cep = holder.itemView.cep


        nome.text = list.nome
        emailUser.text = list.email
        cel.text = list.cel
        cep.text = list.cep


        holder.itemView.setOnClickListener {


            Toast.makeText(context, "${list.email} é você jão?", Toast.LENGTH_SHORT).show()
            val intent = Intent(context,Detail::class.java)
            intent.putExtra("Email",list.email)
          /*  intent.putExtra("Nome", list.nome)
            intent.putExtra("Celular",list.cel)
            intent.putExtra("Cep", list.cep)*/
            context.startActivity(intent)
        }
    }
}