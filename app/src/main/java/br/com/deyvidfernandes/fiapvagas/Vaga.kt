package br.com.deyvidfernandes.fiapvagas

import com.google.firebase.database.IgnoreExtraProperties


@IgnoreExtraProperties
data class Vaga(val descricao: String? = null,
                val empresa: String? = null,
                val faixaInicioSalarial: Double? = 0.0,
                val faixaFimSalarial: Double? = 0.0) {
    // Null default values create a no-argument default constructor, which is needed
    // for deserialization from a DataSnapshot.
}
