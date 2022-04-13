package fr.yutok.example.medgsb

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import fr.yutok.example.medgsb.fragements.HomeFragment

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //charger notre MedRepository
        val repo = MedRepository()

        //metre a jour la liste medicament
        repo.updateData{
            //injecter le fragment dans notre boite (fragment_container)
            val transaction = supportFragmentManager.beginTransaction()
            transaction.replace(R.id.fragement_container, HomeFragment(this))
            transaction.addToBackStack(null)
            transaction.commit()
        }


    }
}