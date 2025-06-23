package dao;

import modelo.Donacion;
import modelo.Juguete;
import modelo.Persona;
import conexion.Conexion;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DonacionDAO {

    public void agregarDonacion(Donacion donacion) {
        String sql = "INSERT INTO donaciones (id_juguete, id_persona, fecha_donacion) VALUES (?, ?, ?)";

        try (Connection con = Conexion.obtenerConexion();
             PreparedStatement stmt = con.prepareStatement(sql)) {

            stmt.setInt(1, donacion.getJuguete().getId());
            stmt.setInt(2, donacion.getPersona().getId());
            stmt.setString(3, donacion.getFechaDonacion());

            stmt.executeUpdate();
            System.out.println("Donación registrada exitosamente");

        } catch (SQLException e) {
            System.out.println("Error al registrar la donación: " + e.getMessage());
        }
    }

    public List<Donacion> obtenerDonaciones() {
        List<Donacion> donaciones = new ArrayList<>();
        String sql = "SELECT * FROM donaciones";

        try (Connection con = Conexion.obtenerConexion();
             PreparedStatement stmt = con.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Juguete juguete = new Juguete(rs.getInt("id_juguete"), "", "", "", "", "");  // Cargar juguete
                Persona persona = new Persona(rs.getInt("id_persona"), "", 0, "", "", "");  // Cargar persona
                Donacion donacion = new Donacion(
                        rs.getInt("id"),
                        juguete,
                        persona,
                        rs.getString("fecha_donacion")
                );
                donaciones.add(donacion);
            }

        } catch (SQLException e) {
            System.out.println("Error al obtener las donaciones: " + e.getMessage());
        }
        return donaciones;
    }

    public void eliminarDonacion(int id) {
        String sql = "DELETE FROM donaciones WHERE id = ?";

        try (Connection con = Conexion.obtenerConexion();
             PreparedStatement stmt = con.prepareStatement(sql)) {

            stmt.setInt(1, id);
            stmt.executeUpdate();
            System.out.println("Donación eliminada exitosamente");

        } catch (SQLException e) {
            System.out.println("Error al eliminar la donación: " + e.getMessage());
        }
    }
}
