package vista;

import java.awt.*;
import modelo.Juguete;
import controlador.ControladorJuguete;

public class FormularioJugueteAWT extends Dialog {

    private TextField txtNombre, txtCategoria, txtEstado, txtUbicacion, txtPropietario;
    private Button btnGuardar, btnCancelar;
    private ControladorJuguete controlador;
    private Juguete juguete;
    private static final long serialVersionUID = 1L;

    public FormularioJugueteAWT(Frame parent, Juguete juguete) {
        super(parent, juguete == null ? "Agregar Juguete" : "Editar Juguete", true);
        this.juguete = juguete;
        controlador = new ControladorJuguete((VentanaPrincipalAWT) parent);

        setSize(400, 300);
        setLayout(new GridLayout(6, 2));

        // Inicialización de los campos de texto (usando los valores existentes si estamos editando)
        txtNombre = new TextField(juguete != null ? juguete.getNombre() : "");
        txtCategoria = new TextField(juguete != null ? juguete.getTipo() : "");
        txtEstado = new TextField(juguete != null ? juguete.getEstado() : "");  // Estado es un String
        txtUbicacion = new TextField(juguete != null ? juguete.getDescripcion() : "");  // Descripción (ubicación)
        txtPropietario = new TextField(juguete != null ? juguete.getPropietario() : "");

        // Botones para guardar o cancelar
        btnGuardar = new Button("Guardar");
        btnCancelar = new Button("Cancelar");

        // Acciones de los botones
        btnGuardar.addActionListener(e -> guardarJuguete());
        btnCancelar.addActionListener(e -> dispose());

        // Añadir los componentes de la interfaz
        add(new Label("Nombre"));
        add(txtNombre);
        add(new Label("Categoría"));
        add(txtCategoria);
        add(new Label("Estado"));
        add(txtEstado);
        add(new Label("Ubicación"));
        add(txtUbicacion);
        add(new Label("Propietario"));
        add(txtPropietario);

        // Panel de botones
        Panel panelBotones = new Panel();
        panelBotones.add(btnGuardar);
        panelBotones.add(btnCancelar);
        add(panelBotones);
    }

    // Método para guardar el juguete
    private void guardarJuguete() {
        // Si es un nuevo juguete
        if (juguete == null) {
            // Ahora el estado y la ubicación son Strings
            juguete = new Juguete(0, txtNombre.getText(), txtCategoria.getText(), 
                    txtEstado.getText(),  // Estado es un String
                    txtUbicacion.getText(), // Ubicación también es un String
                    txtPropietario.getText());
            controlador.agregarJuguete(juguete);
        } else {
            // Si estamos editando
            juguete.setNombre(txtNombre.getText());
            juguete.setTipo(txtCategoria.getText());
            juguete.setEstado(txtEstado.getText());  // Actualizar estado
            juguete.setDescripcion(txtUbicacion.getText());  // Actualizar descripción (ubicación)
            juguete.setPropietario(txtPropietario.getText());  // Actualizar propietario
            controlador.editarJuguete(juguete);
        }
        dispose();  // Cerrar formulario
    }
}
