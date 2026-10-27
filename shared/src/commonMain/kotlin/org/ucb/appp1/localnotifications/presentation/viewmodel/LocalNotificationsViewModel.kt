package org.ucb.appp1.localnotifications.presentation.viewmodel

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import org.ucb.appp1.localnotifications.platform.HelloNotificationResult

class LocalNotificationsViewModel : ViewModel() {

    private val _state = MutableStateFlow(LocalNotificationsState())
    val state = _state.asStateFlow()

    // RÚBRICA: Personalización - el usuario edita el título y el texto antes de enviar
    fun onTitleChange(value: String) {
        _state.update { it.copy(title = value, statusMessage = null) }
    }

    fun onTextChange(value: String) {
        _state.update { it.copy(text = value, statusMessage = null) }
    }

    fun onSendResult(result: HelloNotificationResult) {
        val message = when (result) {
            HelloNotificationResult.SENT -> "Notificación enviada. Revisa la barra de estado."
            HelloNotificationResult.PERMISSION_DENIED ->
                "Permiso denegado: no se puede mostrar la notificación. Puedes activarlo en Ajustes."
            HelloNotificationResult.BLOCKED -> "Las notificaciones de la app están desactivadas en Ajustes."
            HelloNotificationResult.UNSUPPORTED -> "Esta plataforma no soporta notificaciones locales."
        }
        _state.update { it.copy(statusMessage = message) }
    }
}
