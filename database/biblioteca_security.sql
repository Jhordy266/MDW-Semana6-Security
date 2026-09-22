CREATE DATABASE IF NOT EXISTS biblioteca_security
CHARACTER SET utf8mb4
COLLATE utf8mb4_unicode_ci;

USE biblioteca_security;

CREATE TABLE IF NOT EXISTS roles (
                                     id BIGINT AUTO_INCREMENT PRIMARY KEY,
                                     nombre VARCHAR(50) NOT NULL UNIQUE
    );

CREATE TABLE IF NOT EXISTS usuarios (
                                        id BIGINT AUTO_INCREMENT PRIMARY KEY,
                                        username VARCHAR(50) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL,
    nombre VARCHAR(100) NOT NULL,
    rol_id BIGINT NOT NULL,

    CONSTRAINT fk_usuario_rol
    FOREIGN KEY (rol_id)
    REFERENCES roles(id)
    );

CREATE TABLE IF NOT EXISTS libros (
                                      id BIGINT AUTO_INCREMENT PRIMARY KEY,
                                      titulo VARCHAR(150) NOT NULL,
    autor VARCHAR(120) NOT NULL,
    categoria VARCHAR(100) NOT NULL,
    estado VARCHAR(30) NOT NULL
    );

INSERT IGNORE INTO roles (id, nombre)
VALUES
(1, 'ROLE_USER'),
(2, 'ROLE_ADMIN');

INSERT INTO libros (titulo, autor, categoria, estado)
SELECT
    'El Principito',
    'Antoine de Saint-Exupéry',
    'Literatura',
    'DISPONIBLE'
    WHERE NOT EXISTS (
    SELECT 1
    FROM libros
    WHERE titulo = 'El Principito'
);

INSERT INTO libros (titulo, autor, categoria, estado)
SELECT
    'Clean Code',
    'Robert C. Martin',
    'Programación',
    'DISPONIBLE'
    WHERE NOT EXISTS (
    SELECT 1
    FROM libros
    WHERE titulo = 'Clean Code'
);

INSERT INTO libros (titulo, autor, categoria, estado)
SELECT
    'Don Quijote de la Mancha',
    'Miguel de Cervantes',
    'Literatura',
    'DISPONIBLE'
    WHERE NOT EXISTS (
    SELECT 1
    FROM libros
    WHERE titulo = 'Don Quijote de la Mancha'
);