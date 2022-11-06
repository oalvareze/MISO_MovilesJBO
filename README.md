# Vinilos App

Vinilos es una aplicación desarrollada en dos frentes, por un lado tenemos la parte front, construida en Android nativo, por otro lado el backend de la aplicación está desarrollado en Nest.js y desplegada en Heroku.

Vinilos, es una biblioteca de música, en donde podrá encontrar álbumes, canciones de esos albúmenes y comentarios realizados a las respectivas canciones.

Para visualizar o trabajar en la aplicación podrá:
- Para visualizar la app a través de la APK, podrá instalarla en su dispositivo Android, debe tener cuenta que deberá dar permisos para instalar aplicación de terceros.
- Para acceder al codigo fuente podrá clonar este repositorio, para esto debe tener en cuenta lo siguiente:
    1. Un espacio prudente en su disco de almacenamiento, debido a que son proyectos Android y pueden llegar hacer pesados.
    2. Descargar **Java Development Kit (JDK)**, de esta manera podrá no solo interactuar con la aplicación, sino trabajar en ella. Recomendamos descargar la versión **[JDK 11](https://openjdk.org/projects/jdk/11/)**,
       pues se tuvo inconvenientes con versiones inferiores a la 11 y la versión más actual.
    3. Descargar **Android Studio**, puede hacerlo dando clic a la siguiente [URL](https://developer.android.com/studio?hl=es-419&gclid=CjwKCAjwtp2bBhAGEiwAOZZTuPBonzLazwuNdiRverwgye5Sk6vMCq2Sc6z1BvHzDYuWcZ1Payd9sRoC6OYQAvD_BwE&gclsrc=aw.ds),
       si ya lo tiene puede omitir este paso.
    4. Clone el proyecto.
    5. Importe el proyecto en Android Studio.
    6. Asegúrese de construir el proyecto, es decir, que todas las dependencias llamadas en el archivo `build.gradle` se encuentren descargadas.
    7. Para ejecutar la aplicación solo bastará con ir a la clase `MainActivity`, clic derecho y clic en Run.

**Nota**: Puede que la primera vez que se arranque el proyecto, presente lentitud o fallo, esto es debido a que el backend se encuentra desplegado en Heroku de forma gratuita, por políticas de Heroku los proyectos _free_ tienen 
sus limitantes, una de esas es apagar la máquina después de cierto tiempo de inactividad.
