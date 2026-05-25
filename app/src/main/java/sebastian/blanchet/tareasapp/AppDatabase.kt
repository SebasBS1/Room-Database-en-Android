package sebastian.blanchet.tareasapp

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlin.concurrent.Volatile

@Database(
    entities = [TaskEntity::class],
    version = 1
)
abstract class AppDatabase: RoomDatabase(){

    abstract fun taskDao(): TaskDao

    companion object{
        @Volatile
        private var INSTANCE: AppDatabase? = null

        // Tareas que se cargan la primera vez que se
// instala la app. Edita esta lista con tus
// tareas reales del reto con el socio formador.
        private val TAREAS_INICIALES = listOf(
            TaskEntity(
                titulo = "Configurar repositorio en GitHub",
                completado = true
            ),
            TaskEntity(
                titulo = "Implementar base de datos con Room",
                completado = true
            ),
            TaskEntity(
                titulo = "Construir UI con Jetpack Compose",
                completado = true
            ),
            TaskEntity(
                titulo = "Hacer el layout del feed",
                completado = true
            ),
            TaskEntity(
                titulo = "Hacer la función de persistencia",
                completado = true
            ),
            TaskEntity(
                titulo = "Hacer la función del logout",
                completado = true
            ),
            TaskEntity(
                titulo = "Hacer pantalla de login con funcionalidad",
                completado = true
            ),TaskEntity(
                titulo = "Hacer pantalla de register con funcionalidad",
                completado = true
            ),TaskEntity(
                titulo = "Hacer layour de pantalla completa",
                completado = true
            ),TaskEntity(
                titulo = "Hacer funcionalidad de pantalla completa con zoom",
                completado = true
            ),TaskEntity(
                titulo = "Hacer el layout de los threads",
                completado = true
            ), TaskEntity(
                titulo = "Implementar funcionalidad de threads",
                completado = true
            ), TaskEntity(
                titulo = "Hacer publicación de comentarios",
                completado = true
            ),TaskEntity(
                titulo = "Hacer publicación de threads",
                completado = true
            ),TaskEntity(
                titulo = "Hacer edición de comentarios",
                completado = true
            ),TaskEntity(
                titulo = "Hacer edición de threads",
                completado = true
            ),TaskEntity(
                titulo = "Hacer deletion de comentarios",
                completado = true
            ),TaskEntity(
                titulo = "Hacer deletion de threads",
                completado = true
            )
        )

        fun getInstance(
            context: Context
        ): AppDatabase {
            return INSTANCE ?: synchronized(
                this
            ){
                val instance = Room
                    .databaseBuilder(
                        context.applicationContext,
                        AppDatabase::class.java,
                        "tasks_db"
                    )
                    .addCallback(object : RoomDatabase.Callback() {
                        override fun onCreate(db: SupportSQLiteDatabase) {
                            super.onCreate(db)
// Insertamos las tareas iniciales en un
// hilo separado. NUNCA en el main thread.
                            CoroutineScope(Dispatchers.IO).launch {
                                val dao = getInstance(context).taskDao()
                                TAREAS_INICIALES.forEach { tarea ->
                                    dao.insert(tarea)
                                }
                            }
                        }
                    })
                    .build()
                INSTANCE = instance
                instance
            }
        }
    }
}