CREATE DATABASE tienda_dev;

USE tienda_dev;

# PAIS, DEPARTAMENTO, MUNICIPIO
CREATE TABLE pais
(
    pais_id            INT PRIMARY KEY NOT NULL AUTO_INCREMENT,
    nombre             VARCHAR(75)     NOT NULL UNIQUE,
    fecha_creacion     DATE            NOT NULL,
    fecha_modificacion DATE            NULL,
    estado             TINYINT         NOT NULL
);
CREATE TABLE departamento
(
    departamento_id    INT PRIMARY KEY NOT NULL AUTO_INCREMENT,
    nombre             VARCHAR(75)     NOT NULL UNIQUE,
    pais_id            INT             NOT NULL,
    fecha_creacion     DATE            NOT NULL,
    fecha_modificacion DATE            NULL,
    estado             TINYINT         NOT NULL,
    CONSTRAINT fk_departamento_pais FOREIGN KEY (pais_id) REFERENCES pais (pais_id)
);
CREATE TABLE municipio
(
    municipio_id       INT PRIMARY KEY NOT NULL AUTO_INCREMENT,
    nombre             VARCHAR(75)     NOT NULL,
    departamento_id    INT             NOT NULL,
    fecha_creacion     DATE            NOT NULL,
    fecha_modificacion DATE            NULL,
    estado             TINYINT         NOT NULL,
    CONSTRAINT fk_municipio_departamento FOREIGN KEY (departamento_id) REFERENCES departamento (departamento_id)
);



