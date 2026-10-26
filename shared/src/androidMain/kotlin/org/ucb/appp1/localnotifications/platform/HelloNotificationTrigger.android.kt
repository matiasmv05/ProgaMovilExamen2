package org.ucb.appp1.localnotifications.platform

import android.Manifest
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.ui.platform.LocalContext

@Composable
actual fun rememberHelloNotificationTrigger(
    onResult: (HelloNotificationResult) -> Unit
): (title: String, text: String) -> Unit {
    val context = LocalContext.current.applicationContext
    val currentOnResult by rememberUpdatedState(onResult)
    // Guarda lo que el usuario quería enviar mientras se muestra el diálogo de permiso
    val pending = remember { mutableStateOf<Pair<String, String>?>(null) }

    // RÚBRICA: Permiso - diálogo del sistema; si se niega, solo se avisa y la app sigue funcionando
    val permissionLauncher = rememberLauncherForActivityResult(
        ActivityResultContracts.RequestPermission()
    ) { granted ->
        val (title, text) = pending.value ?: return@rememberLauncherForActivityResult
        pending.value = null
        currentOnResult(
            if (granted) {
                HelloNotificationPublisher.show(context, title, text)
            } else {
                HelloNotificationResult.PERMISSION_DENIED
            }
        )
    }

    return remember(context, permissionLauncher) {
        { title, text ->
            // RÚBRICA: Permiso - POST_NOTIFICATIONS solo se pide en Android 13+ (TIRAMISU)
            if (HelloNotificationPublisher.hasPostPermission(context)) {
                currentOnResult(HelloNotificationPublisher.show(context, title, text))
            } else {
                pending.value = title to text
                permissionLauncher.launch(Manifest.permission.POST_NOTIFICATIONS)
            }
        }
    }
}
