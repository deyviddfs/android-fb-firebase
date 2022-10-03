package br.com.deyvidfernandes.fiapvagas

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import br.com.deyvidfernandes.fiapvagas.databinding.ActivityEsqueciSenhaBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class EsqueciSenhaActivity : AppCompatActivity() {
    private lateinit var auth: FirebaseAuth
    private lateinit var binding: ActivityEsqueciSenhaBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEsqueciSenhaBinding.inflate(layoutInflater)
        setContentView(binding.root)

        auth = Firebase.auth

        binding.buttonEnviar.setOnClickListener {
            listenerButtonEnviar()
        }
    }

    private fun listenerButtonEnviar() {
        val email = binding.editTextEmail.text.toString()

        if (email.isNotBlank()) {

        }
        else{
            Toast.makeText(
                baseContext, R.string.o_email_e_obrigatorio,
                Toast.LENGTH_SHORT
            ).show()
            binding.editTextEmail.requestFocus()
        }
    }
}