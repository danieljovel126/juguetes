package conexion;

import java.sql.*;

public class Conexion {

    private static final String URL = "jdbc:mysql://localhost:3306/jugueteria"; // Cambia "jugueteria" por el nombre de tu base de datos
    private static final String USUARIO = "root";  // Cambia "root" por tu usuario de MySQL
    private static final String CONTRASENA = "";  // Cambia "password" por tu contraseña de MySQL

    // Método para probar la conexión
    public static boolean probarConexion() {
        try (Connection conn = DriverManager.getConnection(URL, USUARIO, CONTRASENA)) {
            return conn != null;
        } catch (SQLException e) {
            System.err.println("Error al conectar con la base de datos: " + e.getMessage());
            return false;
        }
    }

    // Método para crear las tablas necesarias si no existen
    public static void crearTablas() {
        // Sentencia para crear la tabla de juguetes
        String sqlCrearTablaJuguetes = "CREATE TABLE IF NOT EXISTS juguetes ("
                                      + "id INT AUTO_INCREMENT PRIMARY KEY,"
                                      + "nombre VARCHAR(255) NOT NULL,"
                                      + "tipo VARCHAR(100),"
                                      + "edad_recomendada INT,"
                                      + "descripcion TEXT"
                                      + ");";
        
        // Sentencia para crear la tabla de personas
        String sqlCrearTablaPersonas = "CREATE TABLE IF NOT EXISTS personas ("
                                      + "id INT AUTO_INCREMENT PRIMARY KEY,"
                                      + "nombre VARCHAR(255) NOT NULL,"
                                      + "apellido VARCHAR(255),"
                                      + "email VARCHAR(255),"
                                      + "telefono VARCHAR(50),"
                                      + "direccion TEXT"
                                      + ");";
        
        // Sentencia para crear la tabla de donaciones
        String sqlCrearTablaDonaciones = "CREATE TABLE IF NOT EXISTS donaciones ("
                                        + "id INT AUTO_INCREMENT PRIMARY KEY,"
                                        + "id_juguete INT,"
                                        + "id_persona INT,"
                                        + "fecha DATE,"
                                        + "FOREIGN KEY (id_juguete) REFERENCES juguetes(id),"
                                        + "FOREIGN KEY (id_persona) REFERENCES personas(id)"
                                        + ");";

        // Ejecutar cada sentencia de manera individual
        try (Connection conn = DriverManager.getConnection(URL, USUARIO, CONTRASENA);
             Statement stmt = conn.createStatement()) {

            // Crear la tabla de juguetes
            stmt.executeUpdate(sqlCrearTablaJuguetes);
            System.out.println("✓ Tabla 'juguetes' creada o verificada.");
            
            // Crear la tabla de personas
            stmt.executeUpdate(sqlCrearTablaPersonas);
            System.out.println("✓ Tabla 'personas' creada o verificada.");
            
            // Crear la tabla de donaciones
            stmt.executeUpdate(sqlCrearTablaDonaciones);
            System.out.println("✓ Tabla 'donaciones' creada o verificada.");

        } catch (SQLException e) {
            System.err.println("Error al crear tablas: " + e.getMessage());
        }
    }
}
