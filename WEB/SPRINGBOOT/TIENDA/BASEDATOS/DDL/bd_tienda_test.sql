CREATE DATABASE tienda_test;

USE tienda_test;

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

# GENERO
CREATE TABLE genero
(
    genero_id          INT PRIMARY KEY NOT NULL AUTO_INCREMENT,
    nombre             VARCHAR(9)      NOT NULL UNIQUE,
    fecha_creacion     DATE            NOT NULL,
    fecha_modificacion DATE            NULL,
    estado             TINYINT         NOT NULL
);

# USUARIO, PROVEEDOR, CLIENTE
CREATE TABLE usuario
(
    usuario_id         INT PRIMARY KEY                                NOT NULL AUTO_INCREMENT,
    nombre             VARCHAR(50)                                    NOT NULL,
    apellido           VARCHAR(50)                                    NOT NULL,
    genero_id          INT                                            NOT NULL,
    fecha_nacimiento   DATE                                           NOT NULL,
    dpi                VARCHAR(13)                                    NOT NULL,
    departamento_id    INT                                            NOT NULL,
    municipio_id       INT                                            NOT NULL,
    direccion          VARCHAR(50)                                    NOT NULL,
    telefono           VARCHAR(12)                                    NOT NULL,
    correo_electronico VARCHAR(50)                                    NOT NULL UNIQUE,
    contrasenia        VARCHAR(75)                                    NOT NULL,
    rol                ENUM ('ADMINISTRADOR', 'DIGITADOR', 'CLIENTE') NOT NULL,
    fecha_creacion     DATE                                           NOT NULL,
    fecha_modificacion DATE                                           NULL,
    estado             TINYINT                                        NOT NULL,
    CONSTRAINT fk_usuario_genero FOREIGN KEY (genero_id) REFERENCES genero (genero_id),
    CONSTRAINT fk_usuario_departamento FOREIGN KEY (departamento_id) REFERENCES departamento (departamento_id),
    CONSTRAINT fk_usuario_municipio FOREIGN KEY (municipio_id) REFERENCES municipio (municipio_id)
);
CREATE TABLE proveedor
(
    proveedor_id       INT PRIMARY KEY NOT NULL AUTO_INCREMENT,
    nombre             VARCHAR(50)     NOT NULL,
    apellido           VARCHAR(50)     NOT NULL,
    genero_id          INT             NOT NULL,
    fecha_nacimiento   DATE            NOT NULL,
    dpi                VARCHAR(13)     NOT NULL,
    departamento_id    INT             NOT NULL,
    municipio_id       INT             NOT NULL,
    direccion          VARCHAR(50)     NOT NULL,
    telefono           VARCHAR(12)     NOT NULL,
    correo_electronico VARCHAR(50)     NOT NULL UNIQUE,
    creado_por         INT             NOT NULL,
    fecha_creacion     DATE            NOT NULL,
    modificado_por     INT             NULL,
    fecha_modificacion DATE            NULL,
    estado             TINYINT         NOT NULL,
    CONSTRAINT fk_proveedor_genero FOREIGN KEY (genero_id) REFERENCES genero (genero_id),
    CONSTRAINT fk_proveedor_departamento FOREIGN KEY (departamento_id) REFERENCES departamento (departamento_id),
    CONSTRAINT fk_proveedor_municipio FOREIGN KEY (municipio_id) REFERENCES municipio (municipio_id),
    CONSTRAINT fk_proveedor_usuario_c FOREIGN KEY (creado_por) REFERENCES usuario (usuario_id),
    CONSTRAINT fk_proveedor_usuario_m FOREIGN KEY (modificado_por) REFERENCES usuario (usuario_id)
);

# CATEGORIA, MARCA, PRODUCTO
CREATE TABLE categoria
(
    categoria_id       INT PRIMARY KEY NOT NULL AUTO_INCREMENT,
    nombre             VARCHAR(50)     NOT NULL UNIQUE,
    descripcion        TEXT            NOT NULL,
    creado_por         INT             NOT NULL,
    fecha_creacion     DATE            NOT NULL,
    modificado_por     INT,
    fecha_modificacion DATE,
    estado             TINYINT         NOT NULL,
    CONSTRAINT fk_categoria_usuario_c FOREIGN KEY (creado_por) REFERENCES usuario (usuario_id),
    CONSTRAINT fk_categoria_usuario_m FOREIGN KEY (modificado_por) REFERENCES usuario (usuario_id)
);
CREATE TABLE marca
(
    marca_id           INT PRIMARY KEY NOT NULL AUTO_INCREMENT,
    nombre             VARCHAR(50)     NOT NULL UNIQUE,
    descripcion        TEXT            NOT NULL,
    creado_por         INT             NOT NULL,
    fecha_creacion     DATE            NOT NULL,
    modificado_por     INT,
    fecha_modificacion DATE,
    estado             TINYINT         NOT NULL,
    CONSTRAINT fk_marca_usuario_c FOREIGN KEY (creado_por) REFERENCES usuario (usuario_id),
    CONSTRAINT fk_marca_usuario_m FOREIGN KEY (modificado_por) REFERENCES usuario (usuario_id)
);
CREATE TABLE producto
(
    producto_id        INT PRIMARY KEY NOT NULL AUTO_INCREMENT,
    nombre             VARCHAR(50)     NOT NULL UNIQUE,
    descripcion        TEXT            NOT NULL,
    stock              INT             NOT NULL,
    precio_unitario    DECIMAL(10, 2)  NOT NULL,
    categoria_id       INT             NOT NULL,
    marca_id           INT             NOT NULL,
    creado_por         INT             NOT NULL,
    fecha_creacion     DATE            NOT NULL,
    modificado_por     INT,
    fecha_modificacion DATE,
    estado             TINYINT         NOT NULL,
    CONSTRAINT fk_producto_categoria FOREIGN KEY (categoria_id) REFERENCES categoria (categoria_id),
    CONSTRAINT fk_producto_marca FOREIGN KEY (marca_id) REFERENCES marca (marca_id),
    CONSTRAINT fk_producto_usuario_c FOREIGN KEY (creado_por) REFERENCES usuario (usuario_id),
    CONSTRAINT fk_producto_usuario_m FOREIGN KEY (modificado_por) REFERENCES usuario (usuario_id)
);

