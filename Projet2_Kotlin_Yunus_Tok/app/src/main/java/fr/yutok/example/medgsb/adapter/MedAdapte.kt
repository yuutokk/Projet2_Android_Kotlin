package fr.yutok.example.medgsb.adapter

import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import fr.yutok.example.medgsb.MainActivity
import fr.yutok.example.medgsb.MedModel
import fr.yutok.example.medgsb.MedRepository
import fr.yutok.example.medgsb.R

class MedAdapter(private val context: MainActivity,
                 private val medList: List<MedModel>,
                 private val layoutId: Int) :RecyclerView.Adapter<MedAdapter.ViewHolder>() {

    //boite pour ranger tout les composants à controler
    class ViewHolder(view: View) :RecyclerView.ViewHolder(view){
        // image du medicaments
        val medImage = view.findViewById<ImageView>(R.id.image_item)
        val medName:TextView? = view.findViewById(R.id.name_item)
        val medDesc:TextView? = view.findViewById(R.id.description_item)
        val favorisIcon = view.findViewById<ImageView>(R.id.favori_icon)


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(layoutId, parent, false)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        // recupere les information du medicament
        val currentMed = medList[position]

        //recuperer le repository
        val repo = MedRepository()

        //utiliser glide pour recuper l'image a partir de son lien -> composant
        Glide.with(context).load(Uri.parse(currentMed.imageUrl)).into(holder.medImage)

        //mettre a jour le nom de la plante
        holder.medName?.text = currentMed.name

        //mettre a jour la description de la plante
        holder.medDesc?.text = currentMed.desc

        //verifier si le medicament a ete mis en favoris ou non
        if(currentMed.favoris){
            holder.favorisIcon.setImageResource(R.drawable.ic_favori_plein)
        }
        else{
            holder.favorisIcon.setImageResource(R.drawable.ic_favori)
        }

        //pouvoir cliquer sur le coeur pour mettre en favoris
        holder.favorisIcon.setOnClickListener{
            //inverser si le bouton est like ou non
            currentMed.favoris = !currentMed.favoris
            //mettre a jour l'objet medicament
            repo.updateMed(currentMed)
        }



    }

    override fun getItemCount(): Int = medList.size
}