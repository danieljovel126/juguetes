package vista;

import controlador.ControladorDonacion;
import controlador.ControladorJuguete;
import controlador.ControladorPersona;
import modelo.Persona;
import modelo.Juguete;
import modelo.Donacion;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class VentanaPrincipalAWT extends Frame {

    private static final long serialVersionUID = 1L;
    
    private ControladorPersona controladorPersona;
    private ControladorJuguete controladorJuguete;
    private ControladorDonacion controladorDonacion;

    // Componentes de la ventana
    private Button btnAgregarPersona, btnAgregarJuguete, btnAgregarDonacion;
    private Button btnVerPersonas, btnVerJuguetes, btnVerDonaciones;
    private Button btnEliminarPersona, btnEliminarJuguete, btnEliminarDonacion;
    private Button btnActualizarPersona, btnActualizarJuguete, btnActualizarDonacion;

    private java.awt.List listaPersonas, listaJuguetes, listaDonaciones;  // Usamos java.awt.List para la interfaz gráfica

    public VentanaPrincipalAWT() {
        setTitle("Sistema de Donación de Juguetes");
        setSize(600, 400);
        setLayout(new BorderLayout());

        // Inicializar controladores
        controladorPersona = new ControladorPersona(this);
        controladorJuguete = new ControladorJuguete(this);
        controladorDonacion = new ControladorDonacion(this);

        // Panel de botones de acción
        Panel panelBotones = new Panel();
        panelBotones.setLayout(new GridLayout(4, 3));

        btnAgregarPersona = new Button("Agregar Persona");
        btnAgregarJuguete = new Button("Agregar Juguete");
        btnAgregarDonacion = new Button("Agregar Donación");

        btnVerPersonas = new Button("Ver Personas");
        btnVerJuguetes = new Button("Ver Juguetes");
        btnVerDonaciones = new Button("Ver Donaciones");

        btnEliminarPersona = new Button("Eliminar Persona");
        btnEliminarJuguete = new Button("Eliminar Juguete");
        btnEliminarDonacion = new Button("Eliminar Donación");

        btnActualizarPersona = new Button("Actualizar Persona");
        btnActualizarJuguete = new Button("Actualizar Juguete");
        btnActualizarDonacion = new Button("Actualizar Donación");

        // Añadir botones al panel
        panelBotones.add(btnAgregarPersona);
        panelBotones.add(btnAgregarJuguete);
        panelBotones.add(btnAgregarDonacion);
        panelBotones.add(btnVerPersonas);
        panelBotones.add(btnVerJuguetes);
        panelBotones.add(btnVerDonaciones);
        panelBotones.add(btnEliminarPersona);
        panelBotones.add(btnEliminarJuguete);
        panelBotones.add(btnEliminarDonacion);
        panelBotones.add(btnActualizarPersona);
        panelBotones.add(btnActualizarJuguete);
        panelBotones.add(btnActualizarDonacion);

        // Crear las listas de personas, juguetes y donaciones usando java.awt.List
        listaPersonas = new java.awt.List();
        listaJuguetes = new java.awt.List();
        listaDonaciones = new java.awt.List();

        // Panel para las listas
        Panel panelListas = new Panel();
        panelListas.setLayout(new GridLayout(1, 3));
        panelListas.add(listaPersonas);
        panelListas.add(listaJuguetes);
        panelListas.add(listaDonaciones);

        // Añadir componentes a la ventana
        add(panelBotones, BorderLayout.NORTH);
        add(panelListas, BorderLayout.CENTER);

        // Definir las acciones de los botones
        btnVerPersonas.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                listaPersonas.removeAll();  // Limpiar la lista antes de cargar
                controladorPersona.cargarPersonas();  // Cargar personas desde el controlador
            }
        });

        btnVerJuguetes.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                listaJuguetes.removeAll();  // Limpiar la lista antes de cargar
                controladorJuguete.cargarJuguetes();  // Cargar juguetes desde el controlador
            }
        });

        btnVerDonaciones.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                listaDonaciones.removeAll();  // Limpiar la lista antes de cargar
                controladorDonacion.cargarDonaciones();  // Cargar donaciones desde el controlador
            }
        });

        setVisible(true);  // Hacer visible la ventana
    }

    // Métodos para agregar elementos a las listas
    public void agregarPersonaALaLista(Persona persona) {
        listaPersonas.add(persona.toString());  // Muestra la persona en la lista
    }

    public void agregarJugueteALaLista(Juguete juguete) {
        listaJuguetes.add(juguete.toString());  // Muestra el juguete en la lista
    }

    public void agregarDonacionALaLista(Donacion donacion) {
        listaDonaciones.add(donacion.toString());  // Muestra la donación en la lista
    }

    // Método en VentanaPrincipalAWT para cargar las personas
    public void cargarPersonas(List<Persona> personas) {
        listaPersonas.removeAll();
        for (Persona persona : personas) {
            listaPersonas.add(persona.toString());  // Añadir personas a la lista
        }
    }

    // Método en VentanaPrincipalAWT para cargar los juguetes
    public void cargarJuguetes(List<Juguete> juguetes) {
        listaJuguetes.removeAll();  // Limpiar la lista antes de cargar
        for (Juguete juguete : juguetes) {
            listaJuguetes.add(juguete.toString());  // Añadir juguetes a la lista
        }
    }

    // Método en VentanaPrincipalAWT para cargar las donaciones
    public void cargarDonaciones(List<Donacion> donaciones) {
        listaDonaciones.removeAll();  // Limpiar la lista antes de cargar
        for (Donacion donacion : donaciones) {
            listaDonaciones.add(donacion.toString());  // Añadir donaciones a la lista
        }
    }

    public static void main(String[] args) {
        new VentanaPrincipalAWT();  // Crear la ventana principal
    }
    
}
