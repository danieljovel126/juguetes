package dao;

import modelo.Persona;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PersonaDAO {

    private static final String URL = "jdbc:mysql://localhost:3306/jugueteria";
    private static final String USUARIO = "root";
    private static final String CONTRASENA = "";

    // Obtener todas las personas
    public List<Persona> obtenerPersonas() {
        List<Persona> personas = new ArrayList<>();
        String sql = "SELECT * FROM personas";

        try (Connection conn = DriverManager.getConnection(URL, USUARIO, CONTRASENA);
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {

            while (rs.next()) {
                Persona persona = new Persona();
                persona.setId(rs.getInt("id"));
                persona.setNombre(rs.getString("nombre"));
                persona.setApellido(rs.getString("apellido"));
                persona.setEmail(rs.getString("email"));
                persona.setTelefono(rs.getString("telefono"));
                persona.setDireccion(rs.getString("direccion"));
                personas.add(persona);
            }
        } catch (SQLException e) {
            System.err.println("Error al obtener personas: " + e.getMessage());
        }
        return personas;
    }

    // Agregar una persona
    public void agregarPersona(Persona persona) {
        String sql = "INSERT INTO personas (nombre, apellido, email, telefono, direccion) VALUES (?, ?, ?, ?, ?)";

        try (Connection conn = DriverManager.getConnection(URL, USUARIO, CONTRASENA);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, persona.getNombre());
            pstmt.setString(2, persona.getApellido());
            pstmt.setString(3, persona.getEmail());
            pstmt.setString(4, persona.getTelefono());
            pstmt.setString(5, persona.getDireccion());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Error al agregar persona: " + e.getMessage());
        }
    }

    // Editar una persona
    public void editarPersona(Persona persona) {
        String sql = "UPDATE personas SET nombre = ?, apellido = ?, email = ?, telefono = ?, direccion = ? WHERE id = ?";

        try (Connection conn = DriverManager.getConnection(URL, USUARIO, CONTRASENA);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, persona.getNombre());
            pstmt.setString(2, persona.getApellido());
            pstmt.setString(3, persona.getEmail());
            pstmt.setString(4, persona.getTelefono());
            pstmt.setString(5, persona.getDireccion());
            pstmt.setInt(6, persona.getId());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Error al editar persona: " + e.getMessage());
        }
    }

    // Eliminar una persona por ID
    public void eliminarPersona(int id) {
        String sql = "DELETE FROM personas WHERE id = ?";

        try (Connection conn = DriverManager.getConnection(URL, USUARIO, CONTRASENA);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, id);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Error al eliminar persona: " + e.getMessage());
        }
    }
}