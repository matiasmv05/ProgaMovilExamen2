package org.ucb.appp1.tareas.data.repository

import kotlinx.coroutines.flow.Flow
import org.ucb.appp1.tareas.data.datasource.TareasFirestoreDataSource
import org.ucb.appp1.tareas.domain.model.Tarea
import org.ucb.appp1.tareas.domain.repository.TareasRepository

class TareasRepositoryImpl(
    private val dataSource: TareasFirestoreDataSource
) : TareasRepository {

    override fun observeTareas(): Flow<List<Tarea>> {
        return dataSource.observeTareas()
    }

    override suspend fun agregarTarea(titulo: String) {
        dataSource.agregarTarea(titulo)
    }

    override suspend fun actualizarEstadoTarea(id: String, completada: Boolean) {
        dataSource.actualizarEstadoTarea(id, completada)
    }
}
