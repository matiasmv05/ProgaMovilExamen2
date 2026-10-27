package org.ucb.appp1.localnotifications.platform

import android.Manifest
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.core.content.ContextCompat
import org.ucb.appp1.shared.R

object HelloNotificationPublisher {

    const val CHANNEL_ID = "local_notifications_channel"
    private const val CHANNEL_NAME = "Notificaciones locales"
    private const val CHANNEL_DESCRIPTION = "Avisos generados dentro de la app"

    // Id fijo: si se envía otra vez, reemplaza a la anterior en vez de apilarse
    const val NOTIFICATION_ID = 25_005
    private const val ACCENT_COLOR = 0xFF6750A4.toInt()

    // RÚBRICA: Canal - solo existe en Android 8+ (O); en versiones anteriores se ignora
    fun ensureChannel(context: Context) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel(
                CHANNEL_ID,
                CHANNEL_NAME,
                NotificationManager.IMPORTANCE_HIGH
            ).apply { description = CHANNEL_DESCRIPTION }
            // Crear un canal que ya existe no hace nada, por eso es seguro llamarlo siempre
            context.getSystemService(NotificationManager::class.java)
                .createNotificationChannel(channel)
        }
    }

    fun hasPostPermission(context: Context): Boolean =
        Build.VERSION.SDK_INT < Build.VERSION_CODES.TIRAMISU ||
            ContextCompat.checkSelfPermission(
                context,
                Manifest.permission.POST_NOTIFICATIONS
            ) == PackageManager.PERMISSION_GRANTED

    // RÚBRICA: Disparo - construye y publica la notificación con NotificationManager
    fun show(context: Context, title: String, text: String): HelloNotificationResult {
        val manager = NotificationManagerCompat.from(context)
        val blockedReason = when {
            !hasPostPermission(context) -> HelloNotificationResult.PERMISSION_DENIED
            // En Android 12 o menor el usuario también puede apagarlas desde Ajustes
            !manager.areNotificationsEnabled() -> HelloNotificationResult.BLOCKED
            else -> null
        }
        if (blockedReason != null) return blockedReason

        ensureChannel(context)

        // RÚBRICA: Personalización - icono propio, título, texto y color de acento
        val notification = NotificationCompat.Builder(context, CHANNEL_ID)
            .setSmallIcon(R.drawable.ic_stat_hello)
            .setContentTitle(title)
            .setContentText(text)
            .setStyle(NotificationCompat.BigTextStyle().bigText(text))
            .setColor(ACCENT_COLOR)
            // Para Android 7 o menor (sin canales) la prioridad se define aquí
            .setPriority(NotificationCompat.PRIORITY_HIGH)
            .setContentIntent(openAppPendingIntent(context))
            .setAutoCancel(true)
            .build()

        manager.notify(NOTIFICATION_ID, notification)
        return HelloNotificationResult.SENT
    }

    // RÚBRICA: PendingIntent - abre la Activity principal (la del LAUNCHER) al tocar la notificación
    private fun openAppPendingIntent(context: Context): PendingIntent? {
        val launchIntent = context.packageManager
            .getLaunchIntentForPackage(context.packageName)
            ?.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP or Intent.FLAG_ACTIVITY_CLEAR_TOP)
            ?: return null
        return PendingIntent.getActivity(
            context,
            NOTIFICATION_ID,
            launchIntent,
            PendingIntent.FLAG_IMMUTABLE or PendingIntent.FLAG_UPDATE_CURRENT
        )
    }
}
