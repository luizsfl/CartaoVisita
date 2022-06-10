package pedroluiz.projeto.cartaovisita.data

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

class VisitaCardRepository(private val dao: VisitaCardDao){

    fun insert(visitaCard: VisitaCard) = runBlocking {
        launch (Dispatchers.IO){
            dao.insert(visitaCard)
        }

    }


    fun getAll() = dao.getAll()

}