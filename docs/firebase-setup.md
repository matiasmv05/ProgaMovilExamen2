# Configuración de Firebase Realtime Database

## 1. Pasos Manuales en la Consola de Firebase
1. Crear o seleccionar un proyecto en la [Consola de Firebase](https://console.firebase.google.com/).
2. Registrar la aplicación Android con el `applicationId` exacto: `org.ucb.appp1`.
3. Crear una instancia de **Realtime Database** en la consola.
4. **IMPORTANTE**: Crear primero la base de datos antes de descargar las credenciales para que el archivo contenga la propiedad `firebase_url`.
5. Descargar `google-services.json` e incluirlo en la carpeta `androidApp/`.

## 2. Reglas Mínimas de Seguridad
Publicar las siguientes reglas de solo lectura en la consola de Firebase Realtime Database:

```json
{
  "rules": {
    "app_dollar": {
      ".read": true,
      ".write": false
    }
  }
}
```

## 3. Verificación de la Conexión
1. Iniciar la aplicación Android.
2. Comprobar que en la pantalla se visualiza el valor en tiempo real cargado desde el nodo `app_dollar`.
3. Cambiar el valor en la consola de Firebase y verificar actualización automática sin reiniciar la app.
