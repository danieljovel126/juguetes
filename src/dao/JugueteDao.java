package dao;

import modelo.Juguete;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class JugueteDAO {

    // Método para obtener la conexión con la base de datos
    private Connection getConnection() throws SQLException {
        // Conexión a MySQL
        return DriverManager.getConnection("jdbc:mysql://localhost:3306/jugueteria", "root", "");  // Cambia según tus credenciales
    }

    // Obtener todos los juguetes
    public List<Juguete> obtenerJuguetes() {
        List<Juguete> juguetes = new ArrayList<>();
        String sql = "SELECT * FROM juguetes";  // Consulta SQL para obtener todos los juguetes
        
        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {
            
            while (rs.next()) {
                Juguete juguete = new Juguete(
                    rs.getInt("id"), 
                    rs.getString("nombre"), 
                    rs.getString("tipo"), 
                    rs.getString("estado"),  // Cambié a String para el estado
                    rs.getString("descripcion"), 
                    rs.getString("propietario")  // Propietario como String
                );
                juguetes.add(juguete);  // Añadir el juguete a la lista
            }
        } catch (SQLException e) {
            System.err.println("Error al obtener juguetes: " + e.getMessage());
        }
        
        return juguetes;
    }
    
    // Agregar un nuevo juguete
    public void agregarJuguete(Juguete juguete) {
        String sql = "INSERT INTO juguetes (nombre, tipo, estado, descripcion, propietario) VALUES (?, ?, ?, ?, ?)";
        
        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setString(1, juguete.getNombre());
            pstmt.setString(2, juguete.getTipo());
            pstmt.setString(3, juguete.getEstado());  // Estado como String
            pstmt.setString(4, juguete.getDescripcion());
            pstmt.setString(5, juguete.getPropietario());  // Propietario como String
            pstmt.executeUpdate();  // Ejecutar la actualización en la base de datos
            
        } catch (SQLException e) {
            System.err.println("Error al agregar juguete: " + e.getMessage());
        }
    }
    
    // Actualizar un juguete
    public void actualizarJuguete(Juguete juguete) {
        String sql = "UPDATE juguetes SET nombre = ?, tipo = ?, estado = ?, descripcion = ?, propietario = ? WHERE id = ?";
        
        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setString(1, juguete.getNombre());
            pstmt.setString(2, juguete.getTipo());
            pstmt.setString(3, juguete.getEstado());  // Estado como String
            pstmt.setString(4, juguete.getDescripcion());
            pstmt.setString(5, juguete.getPropietario());  // Propietario como String
            pstmt.setInt(6, juguete.getId());
            pstmt.executeUpdate();  // Ejecutar la actualización en la base de datos
            
        } catch (SQLException e) {
            System.err.println("Error al actualizar juguete: " + e.getMessage());
        }
    }
    
    // Eliminar un juguete
    public void eliminarJuguete(int id) {
        String sql = "DELETE FROM juguetes WHERE id = ?";
        
        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setInt(1, id);  // Establecer el id del juguete a eliminar
            pstmt.executeUpdate();  // Ejecutar la eliminación en la base de datos
            
        } catch (SQLException e) {
            System.err.println("Error al eliminar juguete: " + e.getMessage());
        }
    }
    
    // Obtener un juguete por ID
    public Juguete obtenerJuguetePorId(int id) {
        String sql = "SELECT * FROM juguetes WHERE id = ?";
        
        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setInt(1, id);  // Establecer el id del juguete
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    Juguete juguete = new Juguete(
                        rs.getInt("id"),
                        rs.getString("nombre"),
                        rs.getString("tipo"),
                        rs.getString("estado"),  // Estado como String
                        rs.getString("descripcion"),
                        rs.getString("propietario")  // Propietario como String
                    );
                    return juguete;  // Devolver el juguete encontrado
                }
            }
        } catch (SQLException e) {
            System.err.println("Error al obtener juguete por ID: " + e.getMessage());
        }
        
        return null;  // Retornar null si no se encuentra el juguete
    }
}
