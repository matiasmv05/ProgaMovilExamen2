package org.ucb.appp1.tareas.domain.model

import kotlinx.serialization.Serializable

@Serializable
data class Tarea(
    val id: String = "",
    val titulo: String = "",
    val completada: Boolean = false,
    val timestamp: Long = 0L
)
