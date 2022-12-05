# Vinilos App
**Nota: Se tuvo que realizar un commit despues de la fecha de entrega debido a que se acabaron los creditos en la cuenta de gcp que se uso para realizar el depligue del backend y al realizarl nuevamente direccion de la api cambio**
Vinilos es una aplicación desarrollada en dos frentes, por un lado tenemos la parte front, construida en Android nativo, por otro lado el backend de la aplicación está desarrollado en Nest.js y desplegada en Heroku.

Vinilos, es una biblioteca de música, en donde podrá encontrar álbumes, canciones de esos albúmenes y comentarios realizados a las respectivas canciones.

Para visualizar o trabajar en la aplicación podrá:
- Para visualizar la app a través de la APK, podrá instalarla en su dispositivo Android desde el archivo [/APK/app-debug.apk](/APK/app-debug.apk)  , debe tener cuenta que deberá dar permisos para instalar aplicación de terceros.
- Para acceder al codigo fuente podrá clonar este repositorio, para esto debe tener en cuenta lo siguiente:
    1. Un espacio prudente en su disco de almacenamiento, debido a que son proyectos Android y pueden llegar hacer pesados.
    2. Descargar **Java Development Kit (JDK)**, de esta manera podrá no solo interactuar con la aplicación, sino trabajar en ella. Recomendamos descargar la versión **[JDK 11](https://openjdk.org/projects/jdk/11/)**,
       pues se tuvo inconvenientes con versiones inferiores a la 11 y la versión más actual.
    3. Descargar **Android Studio**, puede hacerlo dando clic a la siguiente [URL](https://developer.android.com/studio?hl=es-419&gclid=CjwKCAjwtp2bBhAGEiwAOZZTuPBonzLazwuNdiRverwgye5Sk6vMCq2Sc6z1BvHzDYuWcZ1Payd9sRoC6OYQAvD_BwE&gclsrc=aw.ds),
       si ya lo tiene puede omitir este paso.
    4. Clone el proyecto.
    5. Importe el proyecto en Android Studio.
    6. Asegúrese de construir el proyecto, es decir, que todas las dependencias llamadas en el archivo `build.gradle` se encuentren descargadas.
    7. Para ejecutar la aplicación solo bastará con ir a la clase `SplashActivity`, clic derecho y clic en Run.
 - _Nota: Para cambiar el idioma de la aplicacion de ingles o español debe cambiar el idioma del dispositivo_


