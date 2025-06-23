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

    public ControladorDonacion(VentanaPrincipalAWT ventana) {
        this.ventana = ventana;
        this.donacionDAO = new DonacionDAO();
        this.jugueteDAO = new JugueteDAO();
        this.personaDAO = new PersonaDAO();
    }

    public void cargarJuguetes() {
        List<Juguete> juguetes = jugueteDAO.obtenerJuguetes();
        for (Juguete juguete : juguetes) {
            ventana.agregarJugueteALaLista(juguete);  // Cargar juguetes en la lista
        }
    }

    public void cargarPersonas() {
        List<Persona> personas = personaDAO.obtenerPersonas();
        for (Persona persona : personas) {
            ventana.agregarPersonaALaLista(persona);  // Cargar personas en la lista
        }
    }

    public Juguete obtenerJuguetePorIndice(int index) {
        return jugueteDAO.obtenerJuguetes().get(index);  // Obtener juguete por índice
    }

    public Persona obtenerPersonaPorIndice(int index) {
        return personaDAO.obtenerPersonas().get(index);  // Obtener persona por índice
    }

    public void registrarDonacion(Donacion donacion) {
        donacionDAO.agregarDonacion(donacion);
    }
}
