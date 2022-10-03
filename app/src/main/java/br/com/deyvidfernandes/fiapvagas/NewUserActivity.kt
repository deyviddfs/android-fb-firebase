package br.com.deyvidfernandes.fiapvagas

import android.app.Activity
import android.os.Bundle
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import br.com.deyvidfernandes.fiapvagas.databinding.ActivityNewUserBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.UserProfileChangeRequest
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase


class NewUserActivity : AppCompatActivity() {
    private lateinit var auth: FirebaseAuth
    private lateinit var binding: ActivityNewUserBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityNewUserBinding.inflate(layoutInflater)
        setContentView(binding.root)

        auth = Firebase.auth

        binding.buttonEnviar.setOnClickListener {
            listenerButtonEnviar()
        }

        if (binding.root !is EditText) {
            binding.root.setOnTouchListener(View.OnTouchListener { _, _ ->
                hideSoftKeyboard(this)
                false
            })
        }
    }

    fun hideSoftKeyboard(activity: Activity) {
        val inputMethodManager: InputMethodManager = activity.getSystemService(
            INPUT_METHOD_SERVICE
        ) as InputMethodManager
        if (inputMethodManager.isAcceptingText) {
            inputMethodManager.hideSoftInputFromWindow(
                activity.currentFocus!!.windowToken,
                0
            )
        }
    }

    private fun listenerButtonEnviar() {
        val email = binding.editTextEmail.text.toString()
        val password = binding.editTextSenha1.text.toString()
        val nome = binding.editTextNome.text.toString()
        if (validarCamposObrigatorios()) {
            auth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this) { task ->
                    if (task.isSuccessful) {
                        val profileUpdates = UserProfileChangeRequest.Builder()
                            .setDisplayName(nome)
                            .build()
                        val user = auth.currentUser
                        user?.updateProfile(profileUpdates)
                        Toast.makeText(
                            baseContext, R.string.usuario_cadastrado_com_sucesso,
                            Toast.LENGTH_SHORT
                        ).show()
                        finish()
                    }
                }
        }
    }

    private fun validarCamposObrigatorios(): Boolean{
        return false
    }
}