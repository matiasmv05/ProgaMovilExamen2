# ProgaMovilExamen2

Examen práctico grupal de **Programación de Dispositivos Móviles (UCB)**.
App Kotlin Multiplatform (Android + iOS) con Compose Multiplatform, Koin y MVVM.

## 👥 Integrantes

| Integrante | Consigna / parte implementada |
|---|---|
| Maite Arauco | E25-5 · Notificaciones internas básicas con NotificationManager |
| _(completar)_ | _(completar)_ |
| _(completar)_ | _(completar)_ |

## 🧩 Combinación elegida

- **E25-5 · Notificaciones locales con NotificationManager**: canal, permiso `POST_NOTIFICATIONS`, botón de disparo, `PendingIntent` y notificación personalizada.
- _(completar con las consignas de los demás integrantes)_

## ▶️ Instrucciones

### Requisitos
- Android Studio con Android SDK (compileSdk 37) y JDK 21 (el que usa el daemon de Gradle).
- Emulador o dispositivo con Android 7.0 (API 24) o superior. Para probar el permiso de notificaciones se recomienda Android 13+ (API 33+).
- `androidApp/google-services.json` de Firebase (no se sube al repo; ver [docs/firebase-setup.md](./docs/firebase-setup.md)).

### Compilar y ejecutar
1. Abrir **la carpeta raíz** del proyecto en Android Studio (**File → Open**) y esperar el **Gradle Sync**.
2. Si Gradle no encuentra el SDK, crear `local.properties` en la raíz con `sdk.dir=C\:/Users/<usuario>/AppData/Local/Android/Sdk`.
3. Elegir la configuración **androidApp** y el emulador, y presionar ▶️ Run.

Desde la terminal:

```bash
./gradlew :androidApp:assembleDebug    # compilar el APK (Windows: .\gradlew.bat)
./gradlew :androidApp:installDebug     # instalar en el emulador abierto
./gradlew :shared:testAndroidHostTest  # tests
./gradlew :shared:detekt               # análisis estático
```

iOS: abrir [/iosApp](./iosApp) en Xcode y ejecutar desde ahí (requiere macOS).

### Probar las notificaciones locales (E25-5)
1. Tocar el botón flotante **Notificaciones** (abajo a la derecha).
2. Editar el título/texto o dejar el valor por defecto ("Hola desde la app") y tocar **Enviar notificación**.
3. En Android 13+ aparece el diálogo de permiso:
   - **Permitir**: aparece la notificación en la barra de estado.
   - **No permitir**: la app no falla y muestra un aviso.
4. Salir al inicio y tocar la notificación: se abre la app.
5. Para repetir el diálogo de permiso: `adb shell pm reset-permissions -p org.ucb.appp1` o reinstalar la app.

## 🛠️ Decisiones técnicas

### E25-5 · Notificaciones locales
- **Paquete propio** `org.ucb.appp1.localnotifications`, para no generar conflictos con las demás features. Solo se agregó una línea en `AndroidManifest.xml` (permiso) y otra en `App.kt` (punto de entrada).
- **`expect/actual`**: `rememberHelloNotificationTrigger` se declara en `commonMain`. En Android pide el permiso y publica con `NotificationManagerCompat`; en iOS es un stub que solo avisa.
- **Canal** `local_notifications_channel` con `IMPORTANCE_HIGH`, creado solo en Android 8+ (en versiones anteriores no existen los canales).
- **Permiso** pedido con `ActivityResultContracts.RequestPermission` solo en Android 13+ (`TIRAMISU`), que es donde existe `POST_NOTIFICATIONS`.
- **PendingIntent** con `FLAG_IMMUTABLE` (obligatorio desde Android 12 y más seguro). Abre la Activity principal mediante `getLaunchIntentForPackage`, porque el módulo `shared` no conoce `MainActivity`.
- **MVVM sin Koin**: `LocalNotificationsViewModel` expone un `StateFlow` y se crea con `viewModel { }`, para no modificar `SharedModules.kt`.
- **Icono** vectorial monocromo propio (`ic_stat_hello.xml`), como exige Android para la barra de estado.
- **Id de notificación fijo** (`25_005`): si se envía otra vez, reemplaza a la anterior en lugar de acumularse.

_(completar con las decisiones técnicas de las demás consignas)_

## 📏 Reglas y entregables del examen

- Todos los integrantes deben poder explicar cualquier parte del código.
- Se permite documentación oficial; no repositorios de ejemplo.
- El proyecto debe compilar y ejecutarse.

**Entregables**
- Proyecto Android compilable (Kotlin).
- `README.md`: integrantes, combinación elegida, instrucciones y decisiones técnicas.
- Demo en vivo (2–3 min), en caso de que el proyecto no compile o no funcione cuando lo revise el docente.

## 📁 Estructura

- [/shared](./shared/src): código compartido.
  - [commonMain](./shared/src/commonMain/kotlin): código común a todas las plataformas.
  - [androidMain](./shared/src/androidMain/kotlin) / [iosMain](./shared/src/iosMain/kotlin): código específico de cada plataforma (`actual`).
- [/androidApp](./androidApp): punto de entrada Android (`MainActivity`, `MainApplication`, manifest).
- [/iosApp](./iosApp/iosApp): punto de entrada iOS (SwiftUI).
