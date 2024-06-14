#INFORMACION en curso mysql del canal Códigos de programacion-MR
#Para ejecutar un comando usar ctrl + Enter o el boton con simbolo de rayo
CREATE DATABASE prueba1 CHARACTER SET utf8 COLLATE utf8_spanish_ci; #crea una DB

USE prueba1; #necesario para crear y gestionar tablas dentro de la tabla seleccionada, 
#si no se especifica que base de datos se usará, saldrá un error que indica que no se 
#seleccionó una DB para crear o gestionar tablas dentro de un DB
DROP DATABASE prueba1;#borra una DB
#creación de tablas

CREATE TABLE productos(
#columnas 
idProducto INT AUTO_INCREMENT PRIMARY KEY,
nombre VARCHAR (50) NOT NULL, 
precio DECIMAL (10,2) NOT NULL,
fecha_alta DATE DEFAULT '1111-01-01'
);

#GESTIÓN DE TABLAS

SHOW TABLES;
SHOW COLUMNS FROM productos;
SHOW CREATE TABLE productos;

#cambio de nombre a la tabla de productos -> producto

RENAME TABLE productos TO producto; #renombrar tabla

#para agregar columnas 

ALTER TABLE producto ADD existencias INT NOT NULL; #recordar que not null significa que no se admiten datos tipo nulo en los espacios
ALTER TABLE producto DROP COLUMN existencias; #Para borrar una columna se especifica el nombre de la columna

#Para modificar una columna
ALTER TABLE producto CHANGE precio precios DECIMAL(18,2);
#cambiando ahora de precios -> precio
ALTER TABLE producto CHANGE precios precio DECIMAL(18,2) NOT NULL;

DROP TABLE producto;#borra la tabla especificada
SHOW TABLES;