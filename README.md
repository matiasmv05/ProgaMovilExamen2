# ProgaMovilExamen2

**Programación de Dispositivos Móviles — UCB**

Examen práctico grupal desarrollado en Kotlin Multiplatform para Android e iOS, utilizando Compose Multiplatform, Koin y MVVM.

## Integrantes

- Matias Diego Moron
- Carlos Andres Torrez
- Matias Mancilla
- Maite Arauco Porrez

## Ejercicios elegidos

| Proyecto | Ejercicio | Responsable |
|---|---|---|
| 1 | E50-5 — Firestore + Notificación interna al agregar documento | Por completar |
| 2 | E25-5 — Notificaciones internas básicas con NotificationManager | Maite Arauco Porrez |
| 3 | E25-4 — Realtime Database: contador global en tiempo real | Matias Mancilla |

Los tres ejercicios están implementados.

### Proyecto 1 — E50-5: Firestore y notificaciones

**Servicios:** Firebase Cloud Firestore y NotificationManager.

- Muestra las tareas almacenadas en Firestore.
- Detecta documentos nuevos mediante `documentChanges`.
- Muestra una notificación local «Nueva tarea: X».
- Abre la pantalla de tareas mediante `PendingIntent`.
- Permite silenciar las notificaciones y guarda la preferencia con `SharedPreferences`.

### Proyecto 2 — E25-5: Notificaciones locales

**Servicio:** NotificationManager.

- Crea un canal de notificaciones para Android 8 o superior.
- Solicita el permiso `POST_NOTIFICATIONS` en Android 13 o superior.
- Muestra una notificación «Hola desde la app» al pulsar un botón.
- Abre la aplicación mediante `PendingIntent`.
- Utiliza un ícono, título y texto personalizados.

### Proyecto 3 — E25-4: Contador en tiempo real

**Servicio:** Firebase Realtime Database.

- Lee el contador desde `/contador/valor`.
- Incrementa el valor con el botón «+1».
- Utiliza transacciones (`runTransaction`) para evitar conflictos.
- Actualiza el contador en tiempo real mediante `ValueEventListener`.
- Mantiene la persistencia offline con `setPersistenceEnabled(true)`.
- Muestra un mensaje cuando el contador se actualiza correctamente.

## Instrucciones

### Requisitos

- Android Studio con Android SDK 37 y JDK 21.
- Emulador o dispositivo con Android 7.0 o superior.
- Archivo `androidApp/google-services.json` configurado para Firebase. Ver [docs/firebase-setup.md](./docs/firebase-setup.md).

### Ejecución

1. Abrir la carpeta raíz del proyecto en Android Studio.
2. Esperar la sincronización de Gradle.
3. Seleccionar `androidApp` y un emulador o dispositivo.
4. Ejecutar la aplicación con **Run**.


./gradlew :shared:testAndroidHostTest
```
