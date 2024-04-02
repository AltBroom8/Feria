/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 * Author:  usuario
 * Created: 26-feb-2024
 */

-- Crear la base de datos si no existe
--CREATE SCHEMA PRACTICA10;

-- Usar la base de datos
SET SCHEMA PRACTICA10;

-- Eliminar las tablas si existen
DROP TABLE socio;
DROP TABLE producto;
DROP TABLE carrito;

-- Crear la tabla socio (y automáticamente el esquema si no existe)
CREATE TABLE socio (
    ID INT PRIMARY KEY GENERATED ALWAYS AS IDENTITY,
    USERNAME VARCHAR(255) NOT NULL,
    PASSWORD VARCHAR(255) NOT NULL
);

-- Crear la tabla producto
CREATE TABLE producto (
    ID INT PRIMARY KEY GENERATED ALWAYS AS IDENTITY,
    NOMBRE VARCHAR(255) NOT NULL,
    DESCRIPCION VARCHAR(255) NOT NULL,
    FOTO BLOB,
    PRECIO DOUBLE NOT NULL,
    CANTIDAD INT,
    ID_CARRITO INT
);

-- Crear la tabla carrito
CREATE TABLE carrito (
    ID INT PRIMARY KEY GENERATED ALWAYS AS IDENTITY,
    ID_USUARIO INT,
    FOREIGN KEY (ID_USUARIO) REFERENCES socio(ID)
);

CREATE TABLE DetalleCarrito (
    ID INT PRIMARY KEY,
    ID_Carrito INT,
    ID_Producto INT,
    Cantidad INT,
    FOREIGN KEY (ID_Carrito) REFERENCES Carrito(ID),
    FOREIGN KEY (ID_Producto) REFERENCES Producto(ID)
);
-- Insertar datos en la tabla socio
INSERT INTO socio (username,password) VALUES
('admin','1234'),
('PacoGutierrez', 'Feri@SevillA2024'),
('LuciaGonzalez', 'Segur@Contras3na'),
('AntonioMartinez', 'C0digoPr0tegido'),
('IsabelLopez', 'Privacidad#2024'),
('FranciscoRamirez', 'Acc3s0Segur0'),
('ElenaSerrano', 'C0ntraseñaFu3rte'),
('MiguelHernandez', 'C1aveC0mplejA'),
('AnaCabrera', 'Segura2024!'),
('DavidFernandez', 'C0ntraseñaR0busta'),
('CarmenGomez', 'Fer1@Contras3na'),
('JavierOrtega', 'S3guridad2024'),
('LauraJimenez', 'C0dificaci0nFu3rte'),
('ManuelaPerez', 'Acc3s0Priv@d0'),
('JoseRuiz', 'C0ntraseñaR3sistente'),
('SaraTorres', 'Fer1@Segur0'),
('PedroMorales', 'SeguridadTotal!'),
('SilviaCastro', 'C0ntr@señaFuerte'),
('RaulDiaz', 'Fer1@C0dificada'),
('NataliaVega', 'SeguraYFuerte2024'),
('JuanFuentes', 'C0ntraseñaPr0t3gida');

-- Insertar datos en la tabla producto
INSERT INTO producto (NOMBRE, DESCRIPCION, PRECIO) VALUES
('Cerveza Cruzcampo','La Cruzcampo Pilsen es una cerveza que se adhiere 
al estilo Pilsen, caracterizado por su perfil de sabor refrescante y equilibrado.',
2.75),
('Jarra de Rebujito','Una bebida refrescante y popular en ferias andaluzas, 
especialmente en la Feria de Abril en Sevilla. Se hace mezclando vino blanco (preferiblemente fino o manzanilla) 
con refresco de limón y hielo.',7.90),
('Kalimotxo','Una bebida popular en algunas ferias, especialmente entre los jóvenes. 
Consiste en mezclar vino tinto con refresco de cola.',5.60),
('Sangría','Una mezcla de vino tinto, frutas, azúcar y a veces un toque de licor,
 es una bebida fresca y popular en eventos festivos.',4.60),
('Tinto de verano','Similar al calimocho pero más ligero, se hace mezclando vino
 tinto con gaseosa o limonada y se sirve con hielo.',2.90);



