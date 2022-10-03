package br.com.deyvidfernandes.fiapvagas

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class VagaAdapter(private val dataSet: MutableList<Vaga>): RecyclerView.Adapter<VagaAdapter.ViewHolder>(){

    class ViewHolder(view: View): RecyclerView.ViewHolder(view){
        val textViewDescricao: TextView
        val textViewEmpresa: TextView
        val faixaFimSalarial: TextView

        init {
            textViewDescricao = view.findViewById(R.id.textViewDescricao)
            textViewEmpresa = view.findViewById(R.id.textViewEmpresa)
            faixaFimSalarial = view.findViewById(R.id.faixaFimSalarial)
        }
    }

    //Define o layout
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
                        .inflate(R.layout.recyclerview_vaga, parent, false)

        return ViewHolder(view)
    }

    //De/Para Objeto para o layout
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val vaga = dataSet[position]
        holder.textViewDescricao.text = vaga.descricao
        holder.textViewEmpresa.text = vaga.empresa
        holder.faixaFimSalarial.text = vaga.faixaFimSalarial.toString()
    }

    //Retorna o tamanho da lista
    override fun getItemCount(): Int {
        return dataSet.size
    }
}