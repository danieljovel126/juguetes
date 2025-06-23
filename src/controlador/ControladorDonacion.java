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
    private DonacionDAO donacionDAO;
    private JugueteDAO jugueteDAO;
    private PersonaDAO personaDAO;

    // Constructor
    public ControladorDonacion(VentanaPrincipalAWT ventana) {
        this.ventana = ventana;
        this.donacionDAO = new DonacionDAO();
        this.jugueteDAO = new JugueteDAO();
        this.personaDAO = new PersonaDAO();
    }

    // Cargar los juguetes en la ventana
    public void cargarJuguetes() {
        List<Juguete> juguetes = jugueteDAO.obtenerJuguetes();  // Obtener todos los juguetes de la base de datos
        for (Juguete juguete : juguetes) {
            ventana.agregarJugueteALaLista(juguete);  // Pasar el juguete a la lista en la interfaz
        }
    }

    // Cargar las personas en la ventana
    public void cargarPersonas() {
        List<Persona> personas = personaDAO.obtenerPersonas();  // Obtener todas las personas de la base de datos
        for (Persona persona : personas) {
            ventana.agregarPersonaALaLista(persona);  // Pasar la persona a la lista en la interfaz
        }
    }

    // Obtener un juguete por su índice
    public Juguete obtenerJuguetePorIndice(int index) {
        return jugueteDAO.obtenerJuguetes().get(index);  // Obtener el juguete en el índice especificado
    }

    // Obtener una persona por su índice
    public Persona obtenerPersonaPorIndice(int index) {
        return personaDAO.obtenerPersonas().get(index);  // Obtener la persona en el índice especificado
    }

    // Registrar una nueva donación en la base de datos
    public void registrarDonacion(Donacion donacion) {
        donacionDAO.agregarDonacion(donacion);  // Llamar al DAO para registrar la donación
    }

 // Método en ControladorDonacion para cargar las donaciones
    public void cargarDonaciones() {
        List<Donacion> donaciones = donacionDAO.obtenerDonaciones();  // Obtener todas las donaciones de la base de datos
        ventana.cargarDonaciones(donaciones);  // Pasar la lista de donaciones a la ventana para que se muestren
    }
}