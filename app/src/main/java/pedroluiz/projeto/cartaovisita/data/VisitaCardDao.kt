package pedroluiz.projeto.cartaovisita.data

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface VisitaCardDao {
    @Query("SELECT * FROM visitacard")
    fun getAll(): LiveData<List<VisitaCard>>

    @Query("SELECT * FROM visitacard where nome like :search")
    fun searchName(search :String): LiveData<List<VisitaCard>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(VisitaCard : VisitaCard)

}