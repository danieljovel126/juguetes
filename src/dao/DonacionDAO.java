package dao;

import modelo.Donacion;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DonacionDAO {
    
    // Método para obtener la conexión con la base de datos
    private Connection getConnection() throws SQLException {
        // Conexión a MySQL
        return DriverManager.getConnection("jdbc:mysql://localhost:3306/jugueteria", "root", "");  // Cambia según tus credenciales
    }
    
    // Obtener todas las donaciones
    public List<Donacion> obtenerDonaciones() {
        List<Donacion> donaciones = new ArrayList<>();
        String sql = "SELECT * FROM donaciones";
        
        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {
            
            while (rs.next()) {
                Donacion donacion = new Donacion();
                donacion.setId(rs.getInt("id"));
                donacion.setIdPersona(rs.getInt("id_persona"));
                donacion.setIdJuguete(rs.getInt("id_juguete"));
                donacion.setFechaDonacion(rs.getDate("fecha_donacion"));
                donacion.setCantidad(rs.getInt("cantidad"));
                donacion.setObservaciones(rs.getString("observaciones"));
                donaciones.add(donacion);
            }
        } catch (SQLException e) {
            System.err.println("Error al obtener donaciones: " + e.getMessage());
        }
        
        return donaciones;
    }
    
    // Agregar una nueva donación
    public void agregarDonacion(Donacion donacion) {
        String sql = "INSERT INTO donaciones (id_persona, id_juguete, fecha_donacion, cantidad, observaciones) VALUES (?, ?, ?, ?, ?)";
        
        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setInt(1, donacion.getIdPersona());
            pstmt.setInt(2, donacion.getIdJuguete());
            pstmt.setDate(3, new java.sql.Date(donacion.getFechaDonacion().getTime()));
            pstmt.setInt(4, donacion.getCantidad());
            pstmt.setString(5, donacion.getObservaciones());
            pstmt.executeUpdate();
            
        } catch (SQLException e) {
            System.err.println("Error al agregar donación: " + e.getMessage());
        }
    }
    
    // Actualizar una donación
    public void actualizarDonacion(Donacion donacion) {
        String sql = "UPDATE donaciones SET id_persona = ?, id_juguete = ?, fecha_donacion = ?, cantidad = ?, observaciones = ? WHERE id = ?";
        
        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setInt(1, donacion.getIdPersona());
            pstmt.setInt(2, donacion.getIdJuguete());
            pstmt.setDate(3, new java.sql.Date(donacion.getFechaDonacion().getTime()));
            pstmt.setInt(4, donacion.getCantidad());
            pstmt.setString(5, donacion.getObservaciones());
            pstmt.setInt(6, donacion.getId());
            pstmt.executeUpdate();
            
        } catch (SQLException e) {
            System.err.println("Error al actualizar donación: " + e.getMessage());
        }
    }
    
    // Eliminar una donación
    public void eliminarDonacion(int id) {
        String sql = "DELETE FROM donaciones WHERE id = ?";
        
        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setInt(1, id);
            pstmt.executeUpdate();
            
        } catch (SQLException e) {
            System.err.println("Error al eliminar donación: " + e.getMessage());
        }
    }
    
    // Obtener donación por ID
    public Donacion obtenerDonacionPorId(int id) {
        String sql = "SELECT * FROM donaciones WHERE id = ?";
        
        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setInt(1, id);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    Donacion donacion = new Donacion();
                    donacion.setId(rs.getInt("id"));
                    donacion.setIdPersona(rs.getInt("id_persona"));
                    donacion.setIdJuguete(rs.getInt("id_juguete"));
                    donacion.setFechaDonacion(rs.getDate("fecha_donacion"));
                    donacion.setCantidad(rs.getInt("cantidad"));
                    donacion.setObservaciones(rs.getString("observaciones"));
                    return donacion;
                }
            }
        } catch (SQLException e) {
            System.err.println("Error al obtener donación por ID: " + e.getMessage());
        }
        
        return null;
    }
    
    // Obtener donaciones por persona
    public List<Donacion> obtenerDonacionesPorPersona(int idPersona) {
        List<Donacion> donaciones = new ArrayList<>();
        String sql = "SELECT * FROM donaciones WHERE id_persona = ?";
        
        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setInt(1, idPersona);
            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    Donacion donacion = new Donacion();
                    donacion.setId(rs.getInt("id"));
                    donacion.setIdPersona(rs.getInt("id_persona"));
                    donacion.setIdJuguete(rs.getInt("id_juguete"));
                    donacion.setFechaDonacion(rs.getDate("fecha_donacion"));
                    donacion.setCantidad(rs.getInt("cantidad"));
                    donacion.setObservaciones(rs.getString("observaciones"));
                    donaciones.add(donacion);
                }
            }
        } catch (SQLException e) {
            System.err.println("Error al obtener donaciones por persona: " + e.getMessage());
        }
        
        return donaciones;
    }
}
