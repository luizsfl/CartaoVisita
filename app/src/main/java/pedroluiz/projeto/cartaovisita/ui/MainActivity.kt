package pedroluiz.projeto.cartaovisita.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import androidx.activity.viewModels
import androidx.appcompat.widget.SearchView
import pedroluiz.projeto.cartaovisita.App
import pedroluiz.projeto.cartaovisita.R
import pedroluiz.projeto.cartaovisita.databinding.ActivityMainBinding
import pedroluiz.projeto.cartaovisita.util.Image

class MainActivity : AppCompatActivity(), SearchView.OnQueryTextListener {

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

        binding.srlCards.setOnRefreshListener {
            mainViewModel.getAll().observe(this, { visitaCard->
                adapter.submitList(visitaCard)
                binding.srlCards.isRefreshing = false
            })
        }
    }

    private fun getAllVisitaCard(){
        mainViewModel.getAll().observe(this, { visitaCard->
            adapter.submitList(visitaCard)
        })
    }

    override fun onPrepareOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_pesquisa,menu)
        val searchMenu = menu.findItem(R.id.action_search).actionView as SearchView
        searchMenu.setOnQueryTextListener(this)
        return super.onPrepareOptionsMenu(menu)
    }

    override fun onQueryTextSubmit(query: String?): Boolean {
        return false
    }

    override fun onQueryTextChange(newText: String?): Boolean {
        if (newText!=null) {
            mainViewModel.searchName("%"+newText+"%").observe(this,{ cards->
                adapter.submitList(cards)
            })
        }

        return false
    }
}

