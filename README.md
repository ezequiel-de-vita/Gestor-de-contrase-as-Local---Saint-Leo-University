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



## Acualizacion modulo 5

En este módulo se continuó el desarrollo de la aplicación Password Manager. Se realizaron mejoras en la estructura general del proyecto y se avanzó en la implementación funcional de las pantallas principales. Ademas:

- Cambios previos (Módulos anteriores)
- Creación del proyecto en Android Studio.
- Definición de la idea general: gestor de contraseñas local.
- Diseño de wireframes en papel y Figma.
- Implementación inicial de la pantalla de Login.
- Configuración del repositorio en GitHub.
- Primera estructura básica del proyecto en Kotlin.

Cambios actuales (Módulo 5)

- Implementación de la pantalla principal “My Passwords”.
- Definición del flujo de navegación después del login.
- Organización del código en componentes más claros.
- Preparación del almacenamiento local de datos.
- Mejora en la estructura visual basada en los wireframes.

Ultimas modificaciones (modulo 8)

En esta etapa se continuó con el desarrollo funcional de la aplicación y se implementaron componentes clave para el almacenamiento seguro de las contraseñas.

Se incorporó el uso de Room como sistema de base de datos local para persistir la información dentro del dispositivo. A partir de esto se definieron las entidades y los DAO necesarios para almacenar usuarios y contraseñas.

También se implementó un sistema de cifrado de contraseñas utilizando AES, de manera que las contraseñas no se almacenan en texto plano dentro de la base de datos. Antes de guardarse, las contraseñas son cifradas y luego se descifran únicamente cuando la aplicación necesita mostrarlas al usuario.

Cambios realizados:

- Implementación de base de datos local con Room (SQLite).

- Creación de las entidades UserEntity y PasswordEntity.

- Implementación de los DAO (UserDao y PasswordDao) para manejar operaciones de base de datos.

- Desarrollo del sistema de cifrado y descifrado de contraseñas mediante AES.

- Integración del sistema de cifrado al momento de guardar nuevas contraseñas.

- Implementación de la pantalla que muestra la lista de contraseñas guardadas.

- Posibilidad de agregar nuevas contraseñas indicando sitio, usuario y contraseña.

- Visualización de contraseñas almacenadas con descifrado al momento de mostrarlas.

- Implementación de la opción para eliminar contraseñas guardadas.

- Mejora en la estructura del proyecto separando componentes de UI, base de datos y seguridad.

Con estas mejoras la aplicación ya permite registrar usuarios, iniciar sesión y gestionar contraseñas almacenadas localmente de forma cifrada dentro del dispositivo.
