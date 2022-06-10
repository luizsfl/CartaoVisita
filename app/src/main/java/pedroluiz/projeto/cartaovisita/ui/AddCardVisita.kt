package pedroluiz.projeto.cartaovisita.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import pedroluiz.projeto.cartaovisita.App
import pedroluiz.projeto.cartaovisita.R
import pedroluiz.projeto.cartaovisita.data.VisitaCard
import pedroluiz.projeto.cartaovisita.databinding.ActivityAddCardVisitaBinding

class AddCardVisita : AppCompatActivity() {

    private val binding by lazy{ActivityAddCardVisitaBinding.inflate(layoutInflater)}

    private val mainViewModel : MainViewModel by viewModels {
        MainViewModelFactory((application as App).repository)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        insertListeners()
    }

    private fun insertListeners(){
        binding.btnClose.setOnClickListener{
            finish()
        }

        binding.btnConfirme.setOnClickListener {
            val visitaCard = VisitaCard(
                nome = binding.tilName.editText?.text.toString() ,
                empresa = binding.tilEmpresa.editText?.text.toString(),
                telefone = binding.tilTelefone.editText?.text.toString(),
                email = binding.tilEmail.editText?.text.toString(),
                fundoPersonalizado = binding.tilCor.editText?.text.toString(),
                )
            mainViewModel.insert(visitaCard)
            Toast.makeText(this,getString(R.string.label_add_visita_card),Toast.LENGTH_SHORT)
                .show()

            finish()
        }
    }

}