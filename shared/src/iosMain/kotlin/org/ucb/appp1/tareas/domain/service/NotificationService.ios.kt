package org.ucb.appp1.tareas.domain.service

actual class NotificationService {
    actual fun showTaskNotification(title: String, message: String) {}
    actual fun isMuted(): Boolean = false
    actual fun setMuted(muted: Boolean) {}
}
