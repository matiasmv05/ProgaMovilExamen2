package org.ucb.appp1.localnotifications.platform

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberUpdatedState

// Stub: la consigna es solo para Android (NotificationManager); en iOS solo avisa
@Composable
actual fun rememberHelloNotificationTrigger(
    onResult: (HelloNotificationResult) -> Unit
): (title: String, text: String) -> Unit {
    val currentOnResult by rememberUpdatedState(onResult)
    return remember { { _, _ -> currentOnResult(HelloNotificationResult.UNSUPPORTED) } }
}
