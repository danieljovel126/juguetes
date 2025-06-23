package vista;

import java.awt.*;
import java.awt.event.*;
import java.util.List;
import dao.JugueteDAO;
import modelo.Juguete;
import controlador.ControladorJuguete;

public class VentanaPrincipalAWT extends Frame {

    private java.awt.List listaVisualJuguetes;
    private Button btnAgregar, btnEliminar;
    private ControladorJuguete controlador;

    public VentanaPrincipalAWT() {
        setTitle("Donaciones de Juguetes");
        setSize(600, 400);
        setLayout(new BorderLayout());

        controlador = new ControladorJuguete(this);  // El controlador maneja la lógica

        listaVisualJuguetes = new java.awt.List();  // Lista visual para mostrar los juguetes

        btnAgregar = new Button("Agregar Juguete");
        btnEliminar = new Button("Eliminar Juguete");

        // Acción para agregar un juguete
        btnAgregar.addActionListener(e -> new FormularioJugueteAWT(this, null).setVisible(true));

        // Acción para eliminar un juguete
        btnEliminar.addActionListener(e -> {
            int index = listaVisualJuguetes.getSelectedIndex();
            if (index >= 0) {
                Juguete juguete = controlador.obtenerJuguetePorIndice(index);
                controlador.eliminarJuguete(juguete.getId());
            } else {
                mostrarMensaje("Selecciona un juguete para eliminar.");
            }
        });

        // Panel de botones inferior
        Panel panelBotones = new Panel(new FlowLayout());
        panelBotones.add(btnAgregar);
        panelBotones.add(btnEliminar);

        // Panel principal
        Panel panelPrincipal = new Panel(new BorderLayout());
        panelPrincipal.add(listaVisualJuguetes, BorderLayout.CENTER);
        panelPrincipal.add(panelBotones, BorderLayout.SOUTH);

        add(panelPrincipal);

        // Cargar los juguetes al iniciar
        cargarJuguetes();

        // Cerrar correctamente
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent we) {
                System.exit(0);
            }
        });
    }

    // Método que solicita al controlador cargar los juguetes desde la base de datos
    public void cargarJuguetes() {
        controlador.cargarJuguetes();
    }

    // Este método es llamado por el controlador para actualizar la lista visual
    public void cargarJuguetes(List<Juguete> juguetes) {
        listaVisualJuguetes.removeAll();  // Limpiar la lista antes de cargar los nuevos
        for (Juguete juguete : juguetes) {
            listaVisualJuguetes.add(juguete.getNombre());  // Agregar cada nombre de juguete a la lista
        }
    }

    // Método para mostrar mensajes emergentes
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

    public static void main(String[] args) {
        new VentanaPrincipalAWT().setVisible(true);
    }
}
