package org.ucb.appp1.tareas.domain.usecase

import kotlinx.coroutines.flow.Flow
import org.ucb.appp1.tareas.domain.model.Tarea
import org.ucb.appp1.tareas.domain.repository.TareasRepository

class ObserveTareasUseCase(private val repository: TareasRepository) {
    operator fun invoke(): Flow<List<Tarea>> = repository.observeTareas()
}
