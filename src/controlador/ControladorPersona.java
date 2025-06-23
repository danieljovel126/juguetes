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

    public void cargarPersonas() {
        List<Persona> personas = personaDAO.obtenerPersonas();
        ventana.cargarPersonas(personas);
    }

    public void agregarPersona(Persona persona) {
        personaDAO.agregarPersona(persona);
        cargarPersonas();
    }

    public void editarPersona(Persona persona) {
        personaDAO.agregarPersona(persona);  // Reutilizamos agregar para la edición
        cargarPersonas();
    }

    public void eliminarPersona(int id) {
        personaDAO.eliminarPersona(id);
        cargarPersonas();
    }
}
