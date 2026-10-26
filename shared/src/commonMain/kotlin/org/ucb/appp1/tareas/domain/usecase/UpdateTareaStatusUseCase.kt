package org.ucb.appp1.tareas.domain.usecase

import org.ucb.appp1.tareas.domain.repository.TareasRepository

class UpdateTareaStatusUseCase(private val repository: TareasRepository) {
    suspend operator fun invoke(id: String, completada: Boolean) {
        repository.actualizarEstadoTarea(id, completada)
    }
}
