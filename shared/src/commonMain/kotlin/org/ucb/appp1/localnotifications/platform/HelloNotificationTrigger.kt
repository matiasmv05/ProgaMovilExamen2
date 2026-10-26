package org.ucb.appp1.localnotifications.platform

import androidx.compose.runtime.Composable

/**
 * Devuelve una función (título, texto) que publica la notificación local.
 * Cada plataforma decide cómo pedir permiso y cómo mostrarla.
 */
@Composable
expect fun rememberHelloNotificationTrigger(
    onResult: (HelloNotificationResult) -> Unit
): (title: String, text: String) -> Unit
