package br.com.deyvidfernandes.fiapvagas

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import br.com.deyvidfernandes.fiapvagas.databinding.ActivityMainBinding
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var database: DatabaseReference
    private val VAGAS = "vagas"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        database = Firebase.database.reference
        val vagas: MutableList<Vaga> = ArrayList()

        database.child(VAGAS).addValueEventListener(object :
            ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {

                vagas.clear()
                for (postSnapshot in snapshot.children) {
                    val vaga = postSnapshot.getValue(Vaga::class.java)
                    vagas.add(vaga!!)
                }

            }

            override fun onCancelled(databaseError: DatabaseError) {
                Log.i("FIREBASE", "The read failed: " + databaseError.message)
            }
        })
    }

    private fun load(notas: MutableList<Vaga>) {
        val recyclerView = binding.recyclerView
        recyclerView.layoutManager = LinearLayoutManager(binding.root.context)
        recyclerView.adapter = VagaAdapter(notas)

        val itemDecor = DividerItemDecoration(binding.root.context, DividerItemDecoration.VERTICAL)
        recyclerView.addItemDecoration(itemDecor)
    }
}