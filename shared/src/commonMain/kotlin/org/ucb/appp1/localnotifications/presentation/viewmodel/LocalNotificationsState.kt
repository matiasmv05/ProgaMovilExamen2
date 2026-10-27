package org.ucb.appp1.localnotifications.presentation.viewmodel

const val DEFAULT_HELLO_TITLE = "Hola desde la app"
const val DEFAULT_HELLO_TEXT = "Notificación local creada con NotificationManager. Tócala para abrir la app."

data class LocalNotificationsState(
    val title: String = DEFAULT_HELLO_TITLE,
    val text: String = DEFAULT_HELLO_TEXT,
    val statusMessage: String? = null
) {
    val canSend: Boolean get() = title.isNotBlank() && text.isNotBlank()
}
