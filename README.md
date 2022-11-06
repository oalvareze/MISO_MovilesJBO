# Vinilos App

Vinlos es una aplicación desarrollada en dos frentes, por una lado tenemos la parte front, construida
en Android nativo, por otro lado el backend de la aplicación esta desarrollado en Nest.js y 
desplegada en Heroku.

Vinilos, es una biblioteca de musica, en donde podra encontrar albums, canciones de esos albumnes y
comentarios realizados a las respectivas canciones.

Hay dos maneas de utilizar Vinilos:
- La primera forma es a traves de la APK, podra instalarla en su dispositivo Android, debe tener cuenta 
que debera dar
permisos para instalar aplicación de terceros.
- La segunda forma es clonando este repositorio, para esto debe tener en cuenta lo siguiente:
    1. Un espacio prudente en su disco de almacenamiento, debido a que son proyectos Android y pueden
llegar hacer pesados.
    2. Descargar **Java Development Kit (JDK)**, de esta manera podrá no solo interactuar con la aplicación, 
       si no trabajar en ella. Recomendamos descargar la versión **[JDK 11](https://openjdk.org/projects/jdk/11/)**, 
pues se tuvo inconvenientes con versiones inferiores a la 11 y la versión más actual.  
    3. Descargar **Android Studio**, puede hacerlo dando clic a la siguiente [URL](https://developer.android.com/studio?hl=es-419&gclid=CjwKCAjwtp2bBhAGEiwAOZZTuPBonzLazwuNdiRverwgye5Sk6vMCq2Sc6z1BvHzDYuWcZ1Payd9sRoC6OYQAvD_BwE&gclsrc=aw.ds),
       si ya lo tiene puede omitir este paso.
    4. Clone el proyecto.
    5. Importe el proyecto en Android Studio.
    6. Asegurese de construir el proyecto, es decir, que todas las dependencias llamadas en el 
archivo `build.gradle` se encuentren descargardas.
    7. Para ejecutar la aplicación solo bastara con ir a la clase `MainActivity`, clic derecho y  
clic en Run.

**Nota**: Puede que la primera vez que se arranque el proyecto, presente lentitud o fallo, esto es debido 
a que el backend se encuentra desplegado en Heroku de forma gratuita, por politicas de heroku los 
proyectos _free_ tienen sus limitantes, una de esas es apagar la maquina despues de cierto tiempo 
de inactividad. 
