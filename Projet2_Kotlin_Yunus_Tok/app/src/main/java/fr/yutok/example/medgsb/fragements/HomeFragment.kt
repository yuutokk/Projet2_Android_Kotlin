package fr.yutok.example.medgsb.fragements

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import fr.yutok.example.medgsb.MainActivity
import fr.yutok.example.medgsb.MedModel
import fr.yutok.example.medgsb.MedRepository.Singleton.medList
import fr.yutok.example.medgsb.R
import fr.yutok.example.medgsb.adapter.MedAdapter
import fr.yutok.example.medgsb.adapter.MedItemDecoration

class HomeFragment(
    private val context:MainActivity
) : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {


        val view = inflater?.inflate(R.layout.fragement_home,container, false)


        //recuperer le recycler view
        val horizontalRecyclerView = view.findViewById<RecyclerView>(R.id.horizontal_recycler_view)
        horizontalRecyclerView.adapter = MedAdapter(context, medList,R.layout.item_horizontal_med)

        // recuperer le second recyclerView
        val verticalRecyclerView = view.findViewById<RecyclerView>(R.id.vertical_recycler_view)
        verticalRecyclerView.adapter = MedAdapter(context, medList, R.layout.item_verticale_med)
        verticalRecyclerView.addItemDecoration(MedItemDecoration())

        return view
    }
}