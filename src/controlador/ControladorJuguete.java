package controlador;

import dao.JugueteDAO;
import modelo.Juguete;
import vista.VentanaPrincipalAWT;

import java.util.List;

public class ControladorJuguete {

    private VentanaPrincipalAWT ventana;
    private JugueteDAO jugueteDAO;

    public ControladorJuguete(VentanaPrincipalAWT ventana) {
        this.ventana = ventana;
        this.jugueteDAO = new JugueteDAO();
    }

    public void cargarJuguetes() {
        List<Juguete> juguetes = jugueteDAO.obtenerJuguetes();  // Obtener todos los juguetes desde la base de datos
        ventana.cargarJuguetes(juguetes);  // Pasar los juguetes a la vista
    }

    public void agregarJuguete(Juguete juguete) {
        jugueteDAO.agregarJuguete(juguete);
        cargarJuguetes();
    }

    public void editarJuguete(Juguete juguete) {
        jugueteDAO.agregarJuguete(juguete);  // Para la edición, reutilizamos agregar
        cargarJuguetes();
    }

    public void eliminarJuguete(int id) {
        jugueteDAO.eliminarJuguete(id);
        cargarJuguetes();
    }

    // **Este método debe estar aquí en ControladorJuguete.java**
    public Juguete obtenerJuguetePorIndice(int index) {
        return jugueteDAO.obtenerJuguetes().get(index);  // Devuelve el juguete en el índice especificado
    }
}
