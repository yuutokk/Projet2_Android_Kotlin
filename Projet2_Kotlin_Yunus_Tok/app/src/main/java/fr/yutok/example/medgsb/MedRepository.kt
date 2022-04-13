package fr.yutok.example.medgsb

import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import fr.yutok.example.medgsb.MedRepository.Singleton.databaseRef
import fr.yutok.example.medgsb.MedRepository.Singleton.medList

class MedRepository {

    object Singleton{
        // se connecter à la reference medicaments
        val databaseRef = FirebaseDatabase.getInstance().getReference("medicaments")

        //créer une liste qui va contenir nos médicaments
        val medList = arrayListOf<MedModel>()
    }

    fun updateData(callback: () -> Unit){
        //abosrber les connées depuis la databseRef -> lise med
        databaseRef.addValueEventListener(object :ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {

                // mettre a jour la bdd
                medList.clear()
                //recolter la liste
                for (ds in snapshot.children){
                    //construire un objet medicament
                    val med = ds.getValue(MedModel::class.java)

                    //verifier que le medicament n'est pas null
                    if (med != null){
                        // ajouter le medicament a notre liste
                        medList.add(med)
                    }

                }

                //actionner le callback
                callback()
            }

            override fun onCancelled(error: DatabaseError) {

            }

        })
    }

    //mettre a jour un objet dans la bdd
    fun updateMed(med: MedModel) = databaseRef.child(med.id).setValue(med)






}