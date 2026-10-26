package org.ucb.appp1.tareas.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import org.ucb.appp1.tareas.domain.model.Tarea
import org.ucb.appp1.tareas.domain.service.NotificationService
import org.ucb.appp1.tareas.domain.usecase.AddTareaUseCase
import org.ucb.appp1.tareas.domain.usecase.ObserveTareasUseCase
import org.ucb.appp1.tareas.domain.usecase.UpdateTareaStatusUseCase

data class TareasState(
    val tareas: List<Tarea> = emptyList(),
    val newTaskTitle: String = "",
    val isMuted: Boolean = false,
    val isLoading: Boolean = false
)

class TareasViewModel(
    private val observeTareasUseCase: ObserveTareasUseCase,
    private val addTareaUseCase: AddTareaUseCase,
    private val updateTareaStatusUseCase: UpdateTareaStatusUseCase,
    private val notificationService: NotificationService
) : ViewModel() {

    private val _state = MutableStateFlow(TareasState(isMuted = notificationService.isMuted()))
    val state: StateFlow<TareasState> = _state.asStateFlow()

    private var initialLoadDone = false
    private val knownTaskIds = mutableSetOf<String>()

    init {
        observeTareas()
    }

    private fun observeTareas() {
        viewModelScope.launch {
            observeTareasUseCase().collect { lista ->
                if (!initialLoadDone) {
                    initialLoadDone = true
                    knownTaskIds.addAll(lista.map { it.id })
                } else {
                    for (tarea in lista) {
                        if (tarea.id.isNotEmpty() && !knownTaskIds.contains(tarea.id)) {
                            knownTaskIds.add(tarea.id)
                            notificationService.showTaskNotification(
                                title = "Nueva tarea",
                                message = "Nueva tarea: ${tarea.titulo}"
                            )
                        }
                    }
                }
                _state.update { it.copy(tareas = lista) }
            }
        }
    }

    fun onTitleChanged(title: String) {
        _state.update { it.copy(newTaskTitle = title) }
    }

    fun agregarTarea() {
        val title = _state.value.newTaskTitle.trim()
        if (title.isNotEmpty()) {
            viewModelScope.launch {
                addTareaUseCase(title)
                _state.update { it.copy(newTaskTitle = "") }
            }
        }
    }

    fun toggleTarea(tarea: Tarea) {
        viewModelScope.launch {
            updateTareaStatusUseCase(tarea.id, !tarea.completada)
        }
    }

    fun toggleMute(muted: Boolean) {
        notificationService.setMuted(muted)
        _state.update { it.copy(isMuted = muted) }
    }
}
