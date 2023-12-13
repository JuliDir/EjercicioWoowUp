EJERCICIO ALERTAS
Patrones de software
- Patrón de arquitectura MVC (Modelo Vista Controlador) para dividir las capas de la aplicacion en Modelo de datos y la lógica del negocio
- Patrón de diseño Experto para conseguir una alta cohesión y dividir las responsabilidades de las clases
- Patrón de diseño Polimorfismo
Test
- Encapsule los test por cada Experto
- Cada Test corresponde a un caso de uso diferente

Modelo de datos (Diagrama de clases)
- Usuario: nombre, apellido, Temas
- Tema: nombre, descripcion, Usuarios
- Alerta: TipoAlerta, fechaHoraExpiracion, fechaHoraInicio, Tema
- TipoAlerta: nombre, descripcion 
- AlertaUsuario: Alerta, Usuario, fechaHoraAlerta, leida

Modelo de datos (MER): 
- Usuario: id_usuario, nombre, apellido
- UsuarioTema: fk_usuario, fk_tema
- Tema: id_tema, nombre, descripcion
- Alerta: id_alerta, fechaHoraExpiracion, fechaHoraInicio, fk_tipo_alerta, fk_tema
- TipoAlerta: nombre, descripcion 
- AlertaUsuario: fk_alerta, fk_usuario, fechaHoraAlerta, leida

NOTA: La tabla intermedia AlertaUsuario nace porque se debe guardar el atributo leida si es que el usuario lee la alerta y la fechaHora que se alertó al usuario
      La tabla intermedia UsuarioTema nace por la relacion n a n entre Usuario y Tema, por simplicidad en Java utilicé una relacion bidireccional

NOTA: Utilice listas estaticas en una clase llamada BDSimulada en java para simular una base de datos, pero al fin y al cabo son estructuras en memoria. 
      El metodo main no contiene lógica. Para los casos de prueba utilicé los test. 
  

EJERCICIO SQL
-- Crear la tabla Clientes
CREATE TABLE Clientes (
    ID INT PRIMARY KEY,
    Nombre VARCHAR(50),
    Apellido VARCHAR(50)
);

INSERT INTO Clientes (ID, Nombre, Apellido) VALUES
(1, 'Juan', 'Pérez'),
(2, 'María', 'Gómez'),
(3, 'Carlos', 'López'),
(4, 'Ana', 'Martínez');

-- Crear la tabla Ventas
CREATE TABLE Ventas (
    Fecha DATE,
    Sucursal VARCHAR(50),
    Numero_factura INT PRIMARY KEY,
    Importe DECIMAL(10, 2),
    Id_cliente INT,
    FOREIGN KEY (Id_cliente) REFERENCES Clientes(ID)
);

INSERT INTO Ventas (Fecha, Sucursal, Numero_factura, Importe, Id_cliente) VALUES
('2023-11-01', 'Sucursal A', 1, 50000.00, 1),
('2023-11-02', 'Sucursal B', 2, 30000.00, 2),
('2023-11-03', 'Sucursal A', 3, 70000.00, 1),
('2023-12-01', 'Sucursal B', 4, 40000.00, 3),
('2023-12-02', 'Sucursal A', 5, 60000.00, 1),
('2023-12-03', 'Sucursal B', 6, 80000.00, 2),
('2023-12-04', 'Sucursal B', 7, 180000.00, 4);

SELECT c.ID, c.Nombre, c.Apellido
FROM Clientes c
INNER JOIN Ventas v ON c.ID = v.Id_cliente
WHERE v.Fecha >= CURDATE() - INTERVAL 12 MONTH
GROUP BY c.ID, c.Nombre, c.Apellido
HAVING SUM(v.Importe) > 100000;