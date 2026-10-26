package org.ucb.appp1.tareas.domain.service

expect class NotificationService {
    fun showTaskNotification(title: String, message: String)
    fun isMuted(): Boolean
    fun setMuted(muted: Boolean)
}
