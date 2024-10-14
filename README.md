# Gestión de Usuarios con Java Swing y Base de Datos

Este proyecto es una aplicación de escritorio construida en Java usando Swing para la interfaz gráfica y JDBC para la conexión con una base de datos MySQL. La aplicación permite gestionar una tabla de usuarios, realizando operaciones CRUD (Crear, Leer, Actualizar, Borrar).

## Funcionalidades

La aplicación permite las siguientes acciones sobre la base de datos:

1. **Conexión con la Base de Datos (BBDD)**:
   - La conexión con la base de datos se realiza a través de la clase `DBConnection`, que administra la conexión JDBC a una base de datos MySQL llamada "ejemplo2_db".
   - La aplicación se conecta automáticamente al iniciar el programa y permite interactuar con la tabla "Usuarios".

2. **Leer la Tabla**:
   - Se pueden visualizar todos los registros de la tabla de usuarios en la interfaz gráfica. El programa utiliza una `JTable` que muestra las columnas `ID`, `Nombre` y `Email`.
   - Los datos se cargan automáticamente al iniciar la aplicación y se actualizan en tiempo real tras cada operación.

3. **Insertar un Nuevo Usuario**:
   - La aplicación permite agregar un nuevo usuario ingresando su nombre y email en los campos de texto correspondientes y presionando el botón "Insertar".
   - Al hacerlo, se ejecuta una consulta `INSERT` en la base de datos y la tabla se actualiza para reflejar el nuevo registro.

4. **Actualizar los Datos de un Usuario**:
   - Se puede actualizar la información de un usuario existente proporcionando su `ID` y los nuevos valores para `Nombre` y `Email`.
   - Al presionar el botón "Actualizar", se ejecuta una consulta `UPDATE` en la base de datos y la tabla muestra los datos actualizados.

5. **Borrar el Registro de un Usuario**:
   - Para eliminar un usuario, basta con ingresar su `ID` y presionar el botón "Borrar".
   - Esto ejecuta una consulta `DELETE` en la base de datos, eliminando el registro y actualizando la tabla en la interfaz.

## Requisitos

- Java JDK 8 o superior.
- MySQL Server para cargar el Script .sql incluído (u otro sistema de gestión de bases de datos MySQL compatible con JDBC)
- Biblioteca de conector JDBC para MySQL (gestionado por Maven en este proyecto).

## Configuración opcional

Aunque el Script está incluído en el proyecto, puedes configurar tu base de datos en MySQL y crear la tabla de usuarios con la siguiente estructura:

```sql
CREATE TABLE usuarios (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(50) NOT NULL,
    email VARCHAR(100) NOT NULL
);
