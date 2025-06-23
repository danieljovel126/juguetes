package dao;

import modelo.Persona;
import conexion.Conexion;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PersonaDAO {

    // Método para agregar una persona
    public void agregarPersona(Persona persona) {
        String sql = "INSERT INTO personas (nombre, edad, direccion, telefono, correo) VALUES (?, ?, ?, ?, ?)";

        try (Connection con = Conexion.obtenerConexion();
             PreparedStatement stmt = con.prepareStatement(sql)) {

            stmt.setString(1, persona.getNombre());
            stmt.setInt(2, persona.getEdad());
            stmt.setString(3, persona.getDireccion());
            stmt.setString(4, persona.getTelefono());
            stmt.setString(5, persona.getCorreo());

            stmt.executeUpdate();
            System.out.println("Persona agregada exitosamente");

        } catch (SQLException e) {
            System.out.println("Error al agregar la persona: " + e.getMessage());
        }
    }

    // Método para obtener todas las personas
    public List<Persona> obtenerPersonas() {
        List<Persona> personas = new ArrayList<>();
        String sql = "SELECT * FROM personas";

        try (Connection con = Conexion.obtenerConexion();
             PreparedStatement stmt = con.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Persona persona = new Persona(
                        rs.getInt("id"),
                        rs.getString("nombre"),
                        rs.getInt("edad"),
                        rs.getString("direccion"),
                        rs.getString("telefono"),
                        rs.getString("correo")
                );
                personas.add(persona);
            }

        } catch (SQLException e) {
            System.out.println("Error al obtener las personas: " + e.getMessage());
        }
        return personas;
    }

    // Método para eliminar una persona por su ID
    public void eliminarPersona(int id) {
        String sql = "DELETE FROM personas WHERE id = ?";

        try (Connection con = Conexion.obtenerConexion();
             PreparedStatement stmt = con.prepareStatement(sql)) {

            stmt.setInt(1, id);  // Establecer el parámetro de ID
            stmt.executeUpdate();  // Ejecutar la eliminación
            System.out.println("Persona eliminada exitosamente");

        } catch (SQLException e) {
            System.out.println("Error al eliminar la persona: " + e.getMessage());
        }
    }
}
