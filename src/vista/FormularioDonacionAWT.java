package vista;

import java.awt.*;
import modelo.Juguete;
import modelo.Persona;
import modelo.Donacion;
import controlador.ControladorDonacion;
 // Importa la clase Date de SQL para la conversión

public class FormularioDonacionAWT extends Dialog {

    private java.awt.List listaJuguetes, listaPersonas;
    private Button btnDonar, btnCancelar;
    private ControladorDonacion controlador;
    private static final long serialVersionUID = 1L;

    public FormularioDonacionAWT(Frame parent) {
        super(parent, "Registrar Donación", true);
        controlador = new ControladorDonacion((VentanaPrincipalAWT) parent);

        setSize(400, 300);
        setLayout(new GridLayout(4, 2));

        listaJuguetes = new java.awt.List();
        listaPersonas = new java.awt.List();

        // Cargar los juguetes y personas en las listas
        controlador.cargarJuguetes();
        controlador.cargarPersonas();

        btnDonar = new Button("Donar");
        btnCancelar = new Button("Cancelar");

        btnDonar.addActionListener(e -> donarJuguete());
        btnCancelar.addActionListener(e -> dispose());

        add(new Label("Selecciona un Juguete"));
        add(listaJuguetes);
        add(new Label("Selecciona una Persona"));
        add(listaPersonas);

        Panel panelBotones = new Panel();
        panelBotones.add(btnDonar);
        panelBotones.add(btnCancelar);
        add(panelBotones);
    }

    private void donarJuguete() {
        int indiceJuguete = listaJuguetes.getSelectedIndex();
        int indicePersona = listaPersonas.getSelectedIndex();

        if (indiceJuguete >= 0 && indicePersona >= 0) {
            // Obtener los objetos Persona y Juguete desde el controlador
            Juguete juguete = controlador.obtenerJuguetePorIndice(indiceJuguete);
            Persona persona = controlador.obtenerPersonaPorIndice(indicePersona);

            // Crear la donación pasando los IDs de persona y juguete
            // Conversión de LocalDate a java.sql.Date
            java.sql.Date fecha = java.sql.Date.valueOf(java.time.LocalDate.now());

            Donacion donacion = new Donacion(0, juguete.getId(), persona.getId(), fecha, 1, ""); 
            controlador.registrarDonacion(donacion);
            dispose();  // Cerrar el formulario
        } else {
            mostrarMensaje("Selecciona un juguete y una persona para realizar la donación.");
        }
    }

    private void mostrarMensaje(String mensaje) {
        Dialog dialogo = new Dialog(this, "Aviso", true);
        dialogo.setLayout(new FlowLayout());
        dialogo.setSize(300, 100);
        dialogo.add(new Label(mensaje));
        Button btnOk = new Button("OK");
        btnOk.addActionListener(e -> dialogo.dispose());
        dialogo.add(btnOk);
        dialogo.setLocationRelativeTo(this);
        dialogo.setVisible(true);
    }
}
