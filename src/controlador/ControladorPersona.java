package controlador;

import dao.PersonaDAO;
import modelo.Persona;
import vista.VentanaPrincipalAWT;

import java.util.List;

public class ControladorPersona {

    private VentanaPrincipalAWT ventana;
    private PersonaDAO personaDAO;

    // Constructor
    public ControladorPersona(VentanaPrincipalAWT ventana) {
        this.ventana = ventana;
        this.personaDAO = new PersonaDAO();
    }

    // Cargar todas las personas en la ventana
    public void cargarPersonas() {
        List<Persona> personas = personaDAO.obtenerPersonas();  // Obtener todas las personas de la base de datos
        ventana.cargarPersonas(personas);  // Llamar al método de la vista para cargar las personas
    }

    // Agregar una nueva persona
    public void agregarPersona(Persona persona) {
        personaDAO.agregarPersona(persona);  // Llamar al DAO para agregar la persona
        cargarPersonas();  // Recargar la lista después de agregar
    }

    // Editar una persona existente
    public void editarPersona(Persona persona) {
        personaDAO.editarPersona(persona);  // Llamar al DAO para editar la persona en la base de datos
        cargarPersonas();  // Recargar la lista después de editar
    }

    // Eliminar una persona por ID
    public void eliminarPersona(int id) {
        personaDAO.eliminarPersona(id);  // Eliminar la persona de la base de datos
        cargarPersonas();  // Recargar la lista después de eliminar
    }

    // Obtener una persona por su índice
    public Persona obtenerPersonaPorIndice(int index) {
        return personaDAO.obtenerPersonas().get(index);  // Obtener la persona en el índice especificado
    }
}