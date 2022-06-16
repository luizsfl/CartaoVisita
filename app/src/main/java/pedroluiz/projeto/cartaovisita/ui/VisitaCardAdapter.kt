package pedroluiz.projeto.cartaovisita.ui

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ListAdapter
import androidx.constraintlayout.widget.ConstraintSet
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import pedroluiz.projeto.cartaovisita.data.VisitaCard
import pedroluiz.projeto.cartaovisita.databinding.ActivityAddCardVisitaBinding
import pedroluiz.projeto.cartaovisita.databinding.ItemVisitaCardBinding
import java.util.Date.from

class VisitaCardAdapter: androidx.recyclerview.widget.ListAdapter<VisitaCard,VisitaCardAdapter.ViewHolder>(DiffCallback()) {

    var listenerShare :(View) -> Unit = {
    }

    inner class ViewHolder(
        private val binding: ItemVisitaCardBinding
    ):RecyclerView.ViewHolder(binding.root){
        fun bind(item:VisitaCard){
            binding.tvNome.text = "Nome: " +item.nome
            binding.tvTelefone.text = "Telefone: "+item.telefone
            binding.tvEmail.text = "Email: "+item.email
            binding.tvEmpresa.text ="Empresa: "+item.empresa

            if (item.fundoPersonalizado.isNotEmpty()) {
                binding.mcvContent.setBackgroundColor(
                    Color.parseColor((item.fundoPersonalizado))
                )
            }

            binding.mcvContent.setOnClickListener {
                listenerShare(it)
            }

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding =ItemVisitaCardBinding.inflate(inflater,parent,false)

        return ViewHolder(binding)

    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))
        }
}


class DiffCallback: DiffUtil.ItemCallback<VisitaCard>(){

    override fun areItemsTheSame(oldItem: VisitaCard, newItem: VisitaCard) = oldItem ==newItem

    override fun areContentsTheSame(oldItem: VisitaCard, newItem: VisitaCard) = oldItem.id == newItem.id



}