package org.ucb.appp1.tareas.data.datasource

import dev.gitlive.firebase.Firebase
import dev.gitlive.firebase.firestore.firestore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import org.ucb.appp1.tareas.domain.model.Tarea

class TareasFirestoreDataSource {
    private val firestore by lazy { Firebase.firestore }
    private val tareasCollection by lazy { firestore.collection("tareas") }

    fun observeTareas(): Flow<List<Tarea>> {
        return tareasCollection.snapshots().map { snapshot ->
            snapshot.documents.map { doc ->
                try {
                    doc.data<Tarea>().copy(id = doc.id)
                } catch (e: Exception) {
                    Tarea(id = doc.id, titulo = doc.get<String>("titulo") ?: "")
                }
            }
        }
    }

    suspend fun agregarTarea(titulo: String) {
        val tarea = Tarea(
            titulo = titulo,
            completada = false,
            timestamp = kotlinx.datetime.Clock.System.now().toEpochMilliseconds()
        )
        tareasCollection.add(tarea)
    }
}
