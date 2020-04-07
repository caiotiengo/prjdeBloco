package ui.ux.designui.ui.home

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.OrientationHelper
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.firestore.QuerySnapshot
import kotlinx.android.synthetic.main.fragment_home.*
import ui.ux.designui.MyAdapter
import ui.ux.designui.MyModel

class HomeViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value =   ""
    }


    val text: LiveData<String> = _text
}