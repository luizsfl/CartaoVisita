package pedroluiz.projeto.cartaovisita.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import pedroluiz.projeto.cartaovisita.data.VisitaCard
import pedroluiz.projeto.cartaovisita.data.VisitaCardRepository

class MainViewModel(private val visitaCardRepository: VisitaCardRepository): ViewModel() {

    fun insert(visitaCard:VisitaCard){
        visitaCardRepository.insert(visitaCard)

    }

    fun getAll():LiveData<List<VisitaCard>> = visitaCardRepository.getAll()


}

class MainViewModelFactory(private val repository: VisitaCardRepository):
        ViewModelProvider.Factory{
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MainViewModel::class.java))
        {
            @Suppress("UNCHECKED_CAST")
            return MainViewModel(repository) as T
         }else{
            throw IllegalArgumentException("Unknown viewModel class!")

        }
    }


}