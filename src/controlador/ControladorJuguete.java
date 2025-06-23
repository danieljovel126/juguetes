package controlador;

import dao.JugueteDAO;
import modelo.Juguete;
import vista.VentanaPrincipalAWT;

import java.util.List;

public class ControladorJuguete {

    private VentanaPrincipalAWT ventana;
    private JugueteDAO jugueteDAO;

    // Constructor
    public ControladorJuguete(VentanaPrincipalAWT ventana) {
        this.ventana = ventana;
        this.jugueteDAO = new JugueteDAO();
    }

    // Cargar todos los juguetes en la ventana
    public void cargarJuguetes() {
        List<Juguete> juguetes = jugueteDAO.obtenerJuguetes();  // Obtener todos los juguetes de la base de datos
        ventana.cargarJuguetes(juguetes);  // Llamar al método de la vista para cargar los juguetes
    }

    // Agregar un nuevo juguete
    public void agregarJuguete(Juguete juguete) {
        jugueteDAO.agregarJuguete(juguete);  // Llamar al DAO para agregar el juguete
        cargarJuguetes();  // Recargar la lista después de agregar
    }

    // Editar un juguete existente
    public void editarJuguete(Juguete juguete) {
        jugueteDAO.actualizarJuguete(juguete);  // Llamar al DAO para editar el juguete en la base de datos
        cargarJuguetes();  // Recargar la lista después de editar
    }

    // Eliminar un juguete por ID
    public void eliminarJuguete(int id) {
        jugueteDAO.eliminarJuguete(id);  // Eliminar el juguete de la base de datos
        cargarJuguetes();  // Recargar la lista después de eliminar
    }

    // Obtener un juguete por su índice
    public Juguete obtenerJuguetePorIndice(int index) {
        return jugueteDAO.obtenerJuguetes().get(index);  // Obtener el juguete en el índice especificado
    }
}

