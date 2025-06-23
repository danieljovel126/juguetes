package controlador;

import dao.DonacionDAO;
import dao.JugueteDAO;
import dao.PersonaDAO;
import modelo.Donacion;
import modelo.Juguete;
import modelo.Persona;
import vista.VentanaPrincipalAWT;

import java.util.List;

public class ControladorDonacion {

    private VentanaPrincipalAWT ventana;
    private JugueteDAO jugueteDAO;
    private PersonaDAO personaDAO;
    private DonacionDAO donacionDAO;
    private ControladorPersona controladorPersona;  // Añadimos ControladorPersona

    public ControladorDonacion(VentanaPrincipalAWT ventana) {
        this.ventana = ventana;
        this.jugueteDAO = new JugueteDAO();
        this.personaDAO = new PersonaDAO();
        this.donacionDAO = new DonacionDAO();
        this.controladorPersona = new ControladorPersona(ventana);  // Inicializamos ControladorPersona
    }

    // Obtener persona por índice
    public Persona obtenerPersonaPorIndice(int index) {
        return controladorPersona.obtenerPersonaPorIndice(index);  // Llamamos al método de ControladorPersona
    }

    // Registrar una donación en la base de datos
    public void registrarDonacion(Donacion donacion) {
        donacionDAO.agregarDonacion(donacion);  // Llamar al DAO para registrar la donación
    }

    // Cargar la lista de juguetes desde la base de datos
    public void cargarJuguetes() {
        List<Juguete> juguetes = jugueteDAO.obtenerJuguetes();  // Obtener todos los juguetes de la base de datos
        ventana.cargarJuguetes(juguetes);  // Pasar los juguetes a la vista
    }

    // Cargar la lista de personas desde la base de datos
    public void cargarPersonas() {
        List<Persona> personas = personaDAO.obtenerPersonas();  // Obtener todas las personas de la base de datos
        ventana.cargarPersonas(personas);  // Pasar las personas a la vista
    }

    // Agregar un nuevo juguete a la base de datos y recargar la lista
    public void agregarJuguete(Juguete juguete) {
        jugueteDAO.agregarJuguete(juguete);
        cargarJuguetes();  // Recargar la lista de juguetes después de agregar
    }

    // Eliminar un juguete de la base de datos y recargar la lista
    public void eliminarJuguete(int id) {
        jugueteDAO.eliminarJuguete(id);
        cargarJuguetes();  // Recargar la lista después de eliminar
    }

    // Agregar una nueva persona a la base de datos y recargar la lista
    public void agregarPersona(Persona persona) {
        personaDAO.agregarPersona(persona);
        cargarPersonas();  // Recargar la lista de personas después de agregar
    }

    // Eliminar una persona de la base de datos y recargar la lista
    public void eliminarPersona(int id) {
        personaDAO.eliminarPersona(id);
        cargarPersonas();  // Recargar la lista después de eliminar
    }

    // Obtener un juguete por su índice de la lista
    public Juguete obtenerJuguetePorIndice(int index) {
        return jugueteDAO.obtenerJuguetes().get(index);  // Devuelve el juguete en el índice especificado
    }
}
