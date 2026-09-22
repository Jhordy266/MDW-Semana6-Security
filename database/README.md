# Base de datos - Biblioteca Security

La base de datos del proyecto fue desarrollada utilizando MySQL.

## Base de datos

biblioteca_security

## Tablas

### roles

Almacena los roles utilizados por Spring Security:

- ROLE_USER
- ROLE_ADMIN

### usuarios

Almacena los usuarios que podrán iniciar sesión en el sistema.

Cada usuario está asociado a un rol.

### libros

Almacena la información de los libros disponibles en el sistema.

Campos principales:

- título
- autor
- categoría
- estado

## Relación

La tabla usuarios se relaciona con la tabla roles mediante el campo rol_id.

Un rol puede pertenecer a varios usuarios.

## Configuración local de MySQL

Usuario:

root

Contraseña:

root

## Ejecución

Ejecutar el archivo:

biblioteca_security.sql

El script crea la base de datos, las tablas, las relaciones, los roles y los libros iniciales.