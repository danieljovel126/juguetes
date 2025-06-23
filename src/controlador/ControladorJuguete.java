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
        List<Juguete> juguetes = jugueteDAO.obtenerJuguetes();
        ventana.cargarJuguetes(juguetes);
    }

    public void agregarJuguete(Juguete juguete) {
        jugueteDAO.agregarJuguete(juguete);
        cargarJuguetes();
    }

    public void editarJuguete(Juguete juguete) {
        jugueteDAO.agregarJuguete(juguete);  // Reutilizamos agregar para la edición
        cargarJuguetes();
    }

    public void eliminarJuguete(int id) {
        jugueteDAO.eliminarJuguete(id);
        cargarJuguetes();
    }

    public Juguete obtenerJuguetePorIndice(int index) {
        // Recupera el juguete desde la lista cargada (en un entorno real esto debería ser más robusto)
        return jugueteDAO.obtenerJuguetes().get(index);
    }
}
