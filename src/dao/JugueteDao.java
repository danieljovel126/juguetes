package dao;

import modelo.Juguete;
import conexion.Conexion;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class JugueteDAO {

    public void agregarJuguete(Juguete juguete) {
        String sql = "INSERT INTO juguetes (nombre, categoria, estado, ubicacion, propietario) VALUES (?, ?, ?, ?, ?)";

        try (Connection con = Conexion.obtenerConexion();
             PreparedStatement stmt = con.prepareStatement(sql)) {

            stmt.setString(1, juguete.getNombre());
            stmt.setString(2, juguete.getCategoria());
            stmt.setString(3, juguete.getEstado());
            stmt.setString(4, juguete.getUbicacion());
            stmt.setString(5, juguete.getPropietario());

            stmt.executeUpdate();
            System.out.println("Juguete agregado exitosamente");

        } catch (SQLException e) {
            System.out.println("Error al agregar el juguete: " + e.getMessage());
        }
    }

    public List<Juguete> obtenerJuguetes() {
        List<Juguete> juguetes = new ArrayList<>();
        String sql = "SELECT * FROM juguetes";

        try (Connection con = Conexion.obtenerConexion();
             PreparedStatement stmt = con.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Juguete juguete = new Juguete(
                        rs.getInt("id"),
                        rs.getString("nombre"),
                        rs.getString("categoria"),
                        rs.getString("estado"),
                        rs.getString("ubicacion"),
                        rs.getString("propietario")
                );
                juguetes.add(juguete);
            }

        } catch (SQLException e) {
            System.out.println("Error al obtener los juguetes: " + e.getMessage());
        }
        return juguetes;
    }

    public void eliminarJuguete(int id) {
        String sql = "DELETE FROM juguetes WHERE id = ?";

        try (Connection con = Conexion.obtenerConexion();
             PreparedStatement stmt = con.prepareStatement(sql)) {

            stmt.setInt(1, id);
            stmt.executeUpdate();
            System.out.println("Juguete eliminado exitosamente");

        } catch (SQLException e) {
            System.out.println("Error al eliminar el juguete: " + e.getMessage());
        }
    }
}
