package org.ucb.appp1.localnotifications.presentation.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawing
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.material3.Button
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import org.ucb.appp1.localnotifications.platform.rememberHelloNotificationTrigger
import org.ucb.appp1.localnotifications.presentation.viewmodel.LocalNotificationsViewModel

/**
 * Punto de entrada: botón flotante que abre la pantalla de notificaciones encima de la actual.
 * Así no se reemplaza la pantalla de otras features.
 */
@Composable
fun LocalNotificationsEntry() {
    var open by remember { mutableStateOf(false) }
    Box(modifier = Modifier.fillMaxSize().windowInsetsPadding(WindowInsets.safeDrawing)) {
        if (open) {
            LocalNotificationsScreen(onClose = { open = false })
        } else {
            ExtendedFloatingActionButton(
                onClick = { open = true },
                modifier = Modifier.align(Alignment.BottomEnd).padding(16.dp)
            ) {
                Text("Notificaciones")
            }
        }
    }
}

@Composable
fun LocalNotificationsScreen(
    onClose: () -> Unit,
    viewModel: LocalNotificationsViewModel = viewModel { LocalNotificationsViewModel() }
) {
    val state by viewModel.state.collectAsState()
    val sendNotification = rememberHelloNotificationTrigger(onResult = viewModel::onSendResult)

    Surface(modifier = Modifier.fillMaxSize()) {
        Column(
            modifier = Modifier.fillMaxSize().padding(24.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp, Alignment.CenterVertically),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text("Notificaciones locales", style = MaterialTheme.typography.headlineSmall)
            OutlinedTextField(
                value = state.title,
                onValueChange = viewModel::onTitleChange,
                label = { Text("Título") },
                singleLine = true,
                modifier = Modifier.fillMaxWidth()
            )
            OutlinedTextField(
                value = state.text,
                onValueChange = viewModel::onTextChange,
                label = { Text("Texto") },
                modifier = Modifier.fillMaxWidth()
            )
            // RÚBRICA: Disparo - el botón publica la notificación con el título y texto escritos
            Button(
                onClick = { sendNotification(state.title, state.text) },
                enabled = state.canSend,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Enviar notificación")
            }
            state.statusMessage?.let { Text(it, style = MaterialTheme.typography.bodyMedium) }
            TextButton(onClick = onClose) {
                Text("Volver")
            }
        }
    }
}
