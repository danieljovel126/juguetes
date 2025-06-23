package controlador;

import dao.PersonaDAO;
import modelo.Persona;
import vista.VentanaPrincipalAWT;

import java.util.List;

public class ControladorPersona {

    private VentanaPrincipalAWT ventana;
    private PersonaDAO personaDAO;

    public ControladorPersona(VentanaPrincipalAWT ventana) {
        this.ventana = ventana;
        this.personaDAO = new PersonaDAO();
    }

    // Cargar todas las personas de la base de datos
    public void cargarPersonas() {
        List<Persona> personas = personaDAO.obtenerPersonas();
        ventana.cargarPersonas(personas);
    }

    // Agregar una nueva persona a la base de datos
    public void agregarPersona(Persona persona) {
        personaDAO.agregarPersona(persona);
        cargarPersonas();
    }

    // Editar una persona existente
    public void editarPersona(Persona persona) {
        personaDAO.agregarPersona(persona);  // Reutilizamos agregar para la edición
        cargarPersonas();
    }

    // Eliminar una persona de la base de datos
    public void eliminarPersona(int id) {
        personaDAO.eliminarPersona(id);
        cargarPersonas();
    }

    // Obtener una persona por su índice de la lista
    public Persona obtenerPersonaPorIndice(int index) {
        return personaDAO.obtenerPersonas().get(index);  // Devuelve la persona en el índice especificado
    }
}
