# ProgaMovilExamen2

Proyecto Kotlin Multiplatform (Android / iOS) con arquitectura limpia (**Clean Architecture + MVVM**).

## 📌 Ejercicio Seleccionado: E50-5
* **Título**: Firestore + Notificación interna al agregar documento.

## 🛠️ Funcionalidades y Características
1. **Cloud Firestore**: Conexión con la colección `tareas`.
2. **Listener en Tiempo Real**: Detecta automáticamente nuevos documentos añadidos.
3. **Notificación Interna**: Alerta emergente (*heads-up*) `"Nueva tarea: X"`.
4. **PendingIntent**: Al hacer clic en la notificación, abre la app en la pantalla de tareas.
5. **Silenciar Persistente**: Botón para silenciar notificaciones respaldado por `SharedPreferences`.

## Comandos Útiles
* **Android**: `./gradlew :androidApp:assembleDebug`
