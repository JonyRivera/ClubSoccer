package com.juanrolando.coche.data

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.Database
import androidx.sqlite.db.SupportSQLiteDatabase
import com.juanrolando.coche.R
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

@Database(entities = [Coche::class], version = 1, exportSchema = false)
abstract class CocheDataBase : RoomDatabase(){

    abstract fun cocheDao(): CocheDao

    companion object {
        @Volatile
        /*private var Instance: CocheDatabase? = null*/
        private var Instance: CocheDataBase? = null
        private var initialized = false

        fun getDatabase(context: Context): CocheDataBase {

            return Instance ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    CocheDataBase::class.java,
                    "coche_database"
                )
                    .fallbackToDestructiveMigration()
                    .addCallback(object : Callback() {
                        override fun onCreate(db: SupportSQLiteDatabase) {
                            super.onCreate(db)
                            initialized = false
                        }

                        override fun onOpen(db: SupportSQLiteDatabase) {

                            super.onOpen(db)
                            if(!initialized) {
                                GlobalScope.launch {
                                    Instance?.cocheDao()?.deleteAll()
                                    Instance?.cocheDao()?.insert(
                                        Coche(
                                            name = "Bayern Munich",
                                            country = "Alemania",
                                            city = "Munich",
                                            imagen = R.drawable.bayern,
                                            costPlayers = 935000000.00,
                                            yearFundation = 1900
                                        )
                                    )
                                    Instance?.cocheDao()?.insert(
                                        Coche(
                                            name = "Manchester City",
                                            country = "Inglaterra",
                                            city = "Manchester",
                                            imagen = R.drawable.city,
                                            costPlayers = 2500000000.00,
                                            yearFundation = 1880
                                        )
                                    )
                                    Instance?.cocheDao()?.insert(
                                        Coche(
                                            name = "Barcelona",
                                            country = "España",
                                            city = "Barcelona",
                                            imagen = R.drawable.barcelona,
                                            costPlayers = 980000000.00,
                                            yearFundation = 1903
                                        )
                                    )

                                    Instance?.cocheDao()?.insert(
                                        Coche(
                                            name = "Chavales",
                                            country = "Ecuador",
                                            city = "Calceta",
                                            imagen = R.drawable.chavales,
                                            costPlayers = 1000.00,
                                            yearFundation = 2010
                                        )
                                    )

                                    Instance?.cocheDao()?.insert(
                                        Coche(
                                            name = "Real Madrid",
                                            country = "España",
                                            city = "Madrid",
                                            imagen = R.drawable.madrid,
                                            costPlayers = 1200000000.00,
                                            yearFundation = 1902
                                        )
                                    )

                                    Instance?.cocheDao()?.insert(
                                        Coche(
                                            name = "Liverpool",
                                            country = "Inglaterra",
                                            city = "Liverpool",
                                            imagen = R.drawable.liverpool,
                                            costPlayers = 980000000.00,
                                            yearFundation = 1982
                                        )
                                    )

                                }

                            }
                        }
                    })
                    .build()
                Instance = instance
                instance
            }
        }


    }
}