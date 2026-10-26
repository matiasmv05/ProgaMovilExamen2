# Configuración y Flujo de Localización (Loco / localise.biz)

## 1. Configuración Inicial
1. Crear una cuenta en [localise.biz](https://localise.biz/).
2. Ir a **Developer tools** -> **API keys** y obtener la **Export Key** (clave de solo lectura).
3. Configurar la clave en tu archivo local `local.properties` o en `~/.gradle/gradle.properties`:
   ```properties
   LOCO_API_KEY=tu_export_key_aqui
   ```

## 2. Sincronización de Traducciones
Ejecutar la tarea de Gradle:
```bash
./gradlew downloadLocaliseTranslations
```
Esta tarea descargará las traducciones en `shared/src/commonMain/composeResources/` en las carpetas correspondientes (`values/`, `values-es-rES/`, `values-en-rUS/`, etc.).

## 3. Cambio de Idioma en Caliente
El cambio de idioma en caliente dentro de la app utiliza el patrón `LocalAppLocale` en Compose Multiplatform, permitiendo alternar entre Español, Inglés y el idioma del sistema en tiempo real sin reiniciar la aplicación.
