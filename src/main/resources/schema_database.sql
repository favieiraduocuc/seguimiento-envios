CREATE TABLE envios (
    id_envio INT PRIMARY KEY AUTO_INCREMENT,
    codigo VARCHAR(50),
    estado VARCHAR(100),
    ubicacion VARCHAR(100)
);
COMMIT;

INSERT INTO envios ( codigo ,estado , ubicacion) VALUES ('INTL123', 'En tránsito', 'Chile');
INSERT INTO envios ( codigo ,estado , ubicacion) VALUES ('INTL456', 'Entregado', 'Argentina');
INSERT INTO envios ( codigo ,estado , ubicacion) VALUES ('INTL789', 'En aduana', 'México');
COMMIT;

CREATE TABLE usuario (
    id_Usuario INT PRIMARY KEY AUTO_INCREMENT,
    nombre VARCHAR(30),
    email VARCHAR(30) UNIQUE,
    password VARCHAR(30),
    fecha_creacion DATE,
    fecha_actualizacion DATE,
    ultimo_login DATE,
    token BINARY(36) UNIQUE,
    activo TINYINT DEFAULT 0 -- Nueva columna "activo" con valor predeterminado 0
);
CREATE TABLE telefono (
    id_telefono INT PRIMARY KEY AUTO_INCREMENT,
    numero INT,
    cod_ciudad INT,
    country_code INT,
    id_Usuario_Telefono INT,
    FOREIGN KEY (id_Usuario_Telefono) REFERENCES usuario(id_Usuario)
);
INSERT INTO usuario (nombre, email, password, fecha_creacion, fecha_actualizacion, ultimo_login, token, activo)
VALUES
  ('Usuario1', 'usuario1@example.com', 'password1', NOW(), NOW(), NOW(), UUID(), 1),
  ('Usuario2', 'usuario2@example.com', 'password2', NOW(), NOW(), NOW(), UUID(), 1),
  ('Usuario3', 'usuario3@example.com', 'password3', NOW(), NOW(), NOW(), UUID(), 0),
  ('Usuario4', 'usuario4@example.com', 'password4', NOW(), NOW(), NOW(), UUID(), 0),
  ('Usuario5', 'usuario5@example.com', 'password5', NOW(), NOW(), NOW(), UUID(), 1);

-- Insertar teléfonos aleatorios asociados a los usuarios
INSERT INTO telefono (numero, cod_ciudad, country_code, id_Usuario_Telefono)
VALUES
  (123456789, 1, 1, 1),
  (987654321, 2, 1, 2),
  (555555555, 3, 1, 3),
  (999888777, 4, 1, 4),
  (111222333, 5, 1, 5);
  