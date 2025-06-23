package vista;

import java.awt.*;
import modelo.Persona;
import controlador.ControladorDonacion;
import modelo.Juguete;

public class VentanaPrincipalAWT extends Frame {

    private java.awt.List listaVisualJuguetes, listaPersonas;
    private Button btnAgregar, btnEliminar;
    private ControladorDonacion controlador;

    public VentanaPrincipalAWT() {
        setTitle("Donaciones de Juguetes");
        setSize(600, 400);
        setLayout(new BorderLayout());

        controlador = new ControladorDonacion(this);  // Controlador para gestionar la lógica

        // Crear las listas visuales
        listaVisualJuguetes = new java.awt.List();
        listaPersonas = new java.awt.List();

        // Crear botones
        btnAgregar = new Button("Agregar Juguete");
        btnEliminar = new Button("Eliminar Juguete");

        // Agregar listas al panel principal
        Panel panelPrincipal = new Panel(new BorderLayout());
        panelPrincipal.add(listaVisualJuguetes, BorderLayout.CENTER);  // Agregar la lista de juguetes
        panelPrincipal.add(listaPersonas, BorderLayout.WEST);  // Agregar la lista de personas

        // Agregar botones al panel de botones
        Panel panelBotones = new Panel(new FlowLayout());
        panelBotones.add(btnAgregar);
        panelBotones.add(btnEliminar);
        panelPrincipal.add(panelBotones, BorderLayout.SOUTH);  // Botones en la parte inferior

        add(panelPrincipal);  // Agregar panel principal a la ventana

        // Llamar a los métodos para cargar los datos
        controlador.cargarJuguetes();  // Cargar juguetes desde la base de datos
        controlador.cargarPersonas();  // Cargar personas desde la base de datos

        setVisible(true);  // Hacer visible la ventana
        setLocationRelativeTo(null);  // Centrar la ventana en la pantalla
    }

    // Método para agregar un juguete a la lista visual
    public void agregarJugueteALaLista(Juguete juguete) {
        listaVisualJuguetes.add(juguete.getNombre());  // Agregar el nombre del juguete a la lista visual
    }

    // Método para agregar una persona a la lista visual
    public void agregarPersonaALaLista(Persona persona) {
        listaPersonas.add(persona.getNombre());  // Agregar el nombre de la persona a la lista visual
    }

    // Método para cargar los juguetes en la lista
    public void cargarJuguetes(java.util.List<Juguete> juguetes) {
        listaVisualJuguetes.removeAll();  // Limpiar la lista de juguetes antes de cargar los nuevos
        System.out.println("Cargando juguetes...");  // Ver si entra en el método
        for (Juguete juguete : juguetes) {
            System.out.println("Juguete: " + juguete.getNombre());  // Ver cada juguete que se está agregando
            listaVisualJuguetes.add(juguete.getNombre());  // Agregar cada juguete a la lista visual
        }
    }

    // Método para cargar las personas en la lista
    public void cargarPersonas(java.util.List<Persona> personas) {
        listaPersonas.removeAll();  // Limpiar la lista de personas antes de cargar los nuevos
        for (Persona persona : personas) {
            listaPersonas.add(persona.getNombre());  // Agregar cada persona a la lista visual
        }
    }

    // Método principal para lanzar la ventana
    public static void main(String[] args) {
        new VentanaPrincipalAWT();  // Crear y mostrar la ventana principal
    }
    
}
