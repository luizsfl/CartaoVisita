package pedroluiz.projeto.cartaovisita.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import pedroluiz.projeto.cartaovisita.App
import pedroluiz.projeto.cartaovisita.databinding.ActivityMainBinding
import pedroluiz.projeto.cartaovisita.util.Image

class MainActivity : AppCompatActivity() {

    private val adapter by lazy {   VisitaCardAdapter() }

    private val binding by lazy{ActivityMainBinding.inflate(layoutInflater)}
    private val mainViewModel : MainViewModel by viewModels {
        MainViewModelFactory((application as App).repository)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        binding.rvCards.adapter = adapter
        getAllVisitaCard()
        insertListener()
    }

    private fun insertListener(){
        binding.fab.setOnClickListener {
            val intent = Intent(this,AddCardVisita::class.java)
            startActivity(intent)
        }

        adapter.listenerShare = { card ->
            Image.share(this@MainActivity,card)
        }
    }

    private fun getAllVisitaCard(){
        mainViewModel.getAll().observe(this, { visitaCard->
            adapter.submitList(visitaCard)
        })
    }
}

