package org.ucb.appp1.tareas.domain.usecase

import org.ucb.appp1.tareas.domain.repository.TareasRepository

class AddTareaUseCase(private val repository: TareasRepository) {
    suspend operator fun invoke(titulo: String) {
        repository.agregarTarea(titulo)
    }
}
