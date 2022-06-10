package pedroluiz.projeto.cartaovisita

import android.app.Application
import pedroluiz.projeto.cartaovisita.data.AppDataBase
import pedroluiz.projeto.cartaovisita.data.VisitaCardRepository

class App : Application() {
    val database by lazy { AppDataBase.getDatabase(this) }
    val repository by lazy { VisitaCardRepository(database.VisitaDao()) }


}