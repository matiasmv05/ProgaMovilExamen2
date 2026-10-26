package org.ucb.appp1.tareas.domain.repository

import kotlinx.coroutines.flow.Flow
import org.ucb.appp1.tareas.domain.model.Tarea

interface TareasRepository {
    fun observeTareas(): Flow<List<Tarea>>
    suspend fun agregarTarea(titulo: String)
}
