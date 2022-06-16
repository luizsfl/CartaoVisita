package pedroluiz.projeto.cartaovisita.ui

import android.app.AlertDialog
import android.app.Dialog
import android.content.Context
import android.content.DialogInterface
import android.graphics.Color
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.allViews
import com.google.android.material.snackbar.Snackbar
import pedroluiz.projeto.cartaovisita.App
import pedroluiz.projeto.cartaovisita.R
import pedroluiz.projeto.cartaovisita.data.VisitaCard
import pedroluiz.projeto.cartaovisita.databinding.ActivityAddCardVisitaBinding


class AddCardVisita : AppCompatActivity() {

    private val binding by lazy { ActivityAddCardVisitaBinding.inflate(layoutInflater) }

    private val mainViewModel: MainViewModel by viewModels {
        MainViewModelFactory((application as App).repository)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        insertListeners()

    }

    private fun insertListeners() {
        binding.btnClose.setOnClickListener {
            finish()
        }

        binding.btnConfirme.setOnClickListener {
            val nome = binding.tilName.editText?.text.toString()
            val empresa = binding.tilEmpresa.editText?.text.toString()

            if (nome.isEmpty()) {
                Snackbar.make(binding.root, "Favor informar um nome", Snackbar.LENGTH_SHORT)
                    .setAnchorView(binding.tilName)
                    .show()
            } else if (empresa.isEmpty()) {
                Snackbar.make(binding.root, "Favor informar a empresa", Snackbar.LENGTH_SHORT)
                    .setAnchorView(binding.tilEmpresa)
                    .show()

            } else {

                val visitaCard = VisitaCard(
                    nome = nome,
                    empresa = empresa,
                    telefone = binding.tilTelefone.editText?.text.toString(),
                    email = binding.tilEmail.editText?.text.toString(),
                    fundoPersonalizado = binding.btCor.text.toString(),
                )
                mainViewModel.insert(visitaCard)
                Toast.makeText(this, getString(R.string.label_add_visita_card), Toast.LENGTH_SHORT)
                    .show()

                finish()
            }

        }

        binding.btCor.setOnClickListener {
            onCreateDialog()?.show()
        }
    }

    fun onCreateDialog(): Dialog? {

        val builder: AlertDialog.Builder = AlertDialog.Builder(this)
        val nameCores = arrayOf(
            "Vermelho",
            "Rosa",
            "Rocho",
            "Azul",
            "Verde",
            "Amarelo",
            "Laranja",
            "Maron",
            "Cinza",
            "Preto"
        )

        builder.setTitle("Selecione uma cor")
            .setItems(nameCores, DialogInterface.OnClickListener { dialog, which ->
                val allColors: Array<String> = this.getResources().getStringArray(R.array.colors)

                binding.btCor.setText(nameCores[which])
                binding.btCor.setBackgroundColor(Color.parseColor((allColors[which])))
            })

        return builder.create()
    }

}