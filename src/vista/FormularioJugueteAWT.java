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

        txtNombre = new TextField(juguete != null ? juguete.getNombre() : "");
        txtCategoria = new TextField(juguete != null ? juguete.getCategoria() : "");
        txtEstado = new TextField(juguete != null ? juguete.getEstado() : "");
        txtUbicacion = new TextField(juguete != null ? juguete.getUbicacion() : "");
        txtPropietario = new TextField(juguete != null ? juguete.getPropietario() : "");

        btnGuardar = new Button("Guardar");
        btnCancelar = new Button("Cancelar");

        btnGuardar.addActionListener(e -> guardarJuguete());
        btnCancelar.addActionListener(e -> dispose());

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

        Panel panelBotones = new Panel();
        panelBotones.add(btnGuardar);
        panelBotones.add(btnCancelar);
        add(panelBotones);
    }

    // Método para guardar el juguete en la base de datos
    private void guardarJuguete() {
        if (juguete == null) {
            // Si es un nuevo juguete
            juguete = new Juguete(0, txtNombre.getText(), txtCategoria.getText(), txtEstado.getText(),
                    txtUbicacion.getText(), txtPropietario.getText());
            controlador.agregarJuguete(juguete);  // Llamar al controlador para agregar el juguete
        } else {
            // Si estamos editando
            juguete.setNombre(txtNombre.getText());
            juguete.setCategoria(txtCategoria.getText());
            juguete.setEstado(txtEstado.getText());
            juguete.setUbicacion(txtUbicacion.getText());
            juguete.setPropietario(txtPropietario.getText());
            controlador.editarJuguete(juguete);  // Llamar al controlador para editar el juguete
        }
        dispose();  // Cerrar formulario
    }
}
