# Gestor de Contraseñas – Proyecto Android

## Descripción del proyecto

Este proyecto consiste en el desarrollo de una aplicación Android que funcione como un gestor de contraseñas simple. La aplicación permitirá al usuario guardar, organizar y administrar sus contraseñas de manera local desde el dispositivo móvil. La idea principal es resolver un problema cotidiano que nos pasa a la mayoría: tener demasiadas contraseñas y olvidarlas o guardarlas de forma poco segura.

El proyecto tiene un enfoque educativo y busca aplicar los conceptos vistos en la materia de desarrollo de aplicaciones con Android.

## Exposición del problema

Actualmente utilizamos muchas aplicaciones, sitios web y servicios digitales, cada uno con su propio usuario y contraseña. Esto provoca que muchas veces repitamos contraseñas, las olvidemos o las anotemos en lugares inseguros. Estas situaciones no solo generan molestias, sino que también representan un riesgo para la seguridad de la información personal.

La aplicación busca ofrecer una solución sencilla a este problema, centralizando las contraseñas en un solo lugar y protegiéndolas dentro del dispositivo.

## Plataforma

La aplicación será desarrollada para el sistema operativo **Android**, utilizando **Android Studio** como entorno de desarrollo y **Kotlin** como lenguaje de programación. Los datos se almacenarán de manera local utilizando **SQLite a través de Room**, sin dependencia de servicios externos ni conexión a internet.

## Interfaz de usuario e interfaz de administrador

La interfaz de usuario será simple e intuitiva, pensada para facilitar el uso diario de la aplicación. El usuario podrá acceder a una pantalla principal donde se mostrará la lista de contraseñas guardadas y contará con opciones para agregar, editar o eliminar registros.

La aplicación no contará con una interfaz de administrador separada, ya que está pensada para un solo usuario. El acceso a la información estará protegido mediante un método de seguridad básico, como un PIN o autenticación del dispositivo.

## Funcionalidad

Las principales funcionalidades de la aplicación serán:
- Agregar nuevas contraseñas asociadas a distintos servicios.
- Visualizar las contraseñas almacenadas de forma ordenada.
- Editar y eliminar registros existentes.
- Almacenar las contraseñas de manera segura utilizando criptografía AES.
- Funcionamiento completamente local, sin conexión a internet.

## Diseño (wireframes o esquemas de página)

El diseño de la aplicación se basará en una estructura simple:
- Pantalla de acceso (PIN o autenticación).
- Pantalla principal con listado de contraseñas.
- Pantalla para agregar o editar contraseñas.

Los wireframes serán esquemas básicos que representen la disposición de los elementos en cada pantalla, priorizando la claridad y facilidad de uso sobre un diseño complejo.

