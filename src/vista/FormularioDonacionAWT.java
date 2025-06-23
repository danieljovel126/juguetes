package vista;

import java.awt.*;
import modelo.Juguete;
import modelo.Persona;
import modelo.Donacion;
import controlador.ControladorDonacion;

public class FormularioDonacionAWT extends Dialog {

    private java.awt.List listaJuguetes, listaPersonas;
    private Button btnDonar, btnCancelar;
    private ControladorDonacion controlador;

    private static final long serialVersionUID = 1L;  // Agregar esta línea en todas las clases serializables

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

        // Agregar los componentes a la interfaz
        add(new Label("Selecciona un Juguete"));
        add(listaJuguetes);
        add(new Label("Selecciona una Persona"));
        add(listaPersonas);

        Panel panelBotones = new Panel();
        panelBotones.add(btnDonar);
        panelBotones.add(btnCancelar);
        add(panelBotones);
    }

    // Método para realizar la donación
    private void donarJuguete() {
        int indiceJuguete = listaJuguetes.getSelectedIndex();
        int indicePersona = listaPersonas.getSelectedIndex();

        if (indiceJuguete >= 0 && indicePersona >= 0) {
            Juguete juguete = controlador.obtenerJuguetePorIndice(indiceJuguete);  // Obtener el juguete desde ControladorDonacion
            Persona persona = controlador.obtenerPersonaPorIndice(indicePersona);  // Aquí es donde necesitas usar ControladorPersona

            // Crear la donación
            Donacion donacion = new Donacion(0, juguete, persona, java.time.LocalDate.now().toString());
            controlador.registrarDonacion(donacion);
            dispose();  // Cerrar el formulario
        } else {
            mostrarMensaje("Selecciona un juguete y una persona para realizar la donación.");
        }
    }

    // Mostrar mensaje de advertencia si no se seleccionan ambos elementos
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

