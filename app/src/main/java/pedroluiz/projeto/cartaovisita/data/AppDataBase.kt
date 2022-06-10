package pedroluiz.projeto.cartaovisita.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [VisitaCard::class], version = 1)
abstract class AppDataBase: RoomDatabase (){

    abstract fun VisitaDao() : VisitaCardDao

    companion object{
        @Volatile
        private var INSTANCE : AppDataBase ? = null

        fun getDatabase(context : Context): AppDataBase{

            return  INSTANCE?: synchronized(this){
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDataBase::class.java,
                    "visitacard_db"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}



