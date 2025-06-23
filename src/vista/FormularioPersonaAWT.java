package vista;

import java.awt.*;
import modelo.Persona;
import controlador.ControladorPersona;
import javax.swing.JOptionPane;

public class FormularioPersonaAWT extends Dialog {

    private TextField txtNombre, txtEdad, txtDireccion, txtTelefono, txtCorreo;
    private Button btnGuardar, btnCancelar;
    private ControladorPersona controlador;
    private Persona persona;
    private static final long serialVersionUID = 1L;

    public FormularioPersonaAWT(Frame parent, Persona persona) {
        super(parent, persona == null ? "Agregar Persona" : "Editar Persona", true);
        this.persona = persona;
        controlador = new ControladorPersona((VentanaPrincipalAWT) parent);

        setSize(400, 300);
        setLayout(new GridLayout(6, 2));

        // Inicialización de los campos de texto (usando los valores existentes si estamos editando)
        txtNombre = new TextField(persona != null ? persona.getNombre() : "");
        txtEdad = new TextField(persona != null ? String.valueOf(persona.getEdad()) : "");  // Añadimos el campo Edad
        txtDireccion = new TextField(persona != null ? persona.getDireccion() : "");
        txtTelefono = new TextField(persona != null ? persona.getTelefono() : "");
        txtCorreo = new TextField(persona != null ? persona.getEmail() : "");

        // Botones para guardar o cancelar
        btnGuardar = new Button("Guardar");
        btnCancelar = new Button("Cancelar");

        // Acciones de los botones
        btnGuardar.addActionListener(e -> guardarPersona());
        btnCancelar.addActionListener(e -> dispose());

        // Añadir los componentes de la interfaz
        add(new Label("Nombre"));
        add(txtNombre);
        add(new Label("Edad"));
        add(txtEdad);
        add(new Label("Dirección"));
        add(txtDireccion);
        add(new Label("Teléfono"));
        add(txtTelefono);
        add(new Label("Correo"));
        add(txtCorreo);

        Panel panelBotones = new Panel();
        panelBotones.add(btnGuardar);
        panelBotones.add(btnCancelar);
        add(panelBotones);
    }

    // Método para guardar la persona en la base de datos
    private void guardarPersona() {
        // Verificación para asegurarse de que los campos obligatorios están completos
        if (txtNombre.getText().isEmpty() || txtEdad.getText().isEmpty() || txtDireccion.getText().isEmpty() ||
            txtTelefono.getText().isEmpty() || txtCorreo.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Todos los campos son obligatorios.", "Error", JOptionPane.ERROR_MESSAGE);
            return;  // Salir si algún campo obligatorio está vacío
        }

        try {
            // Intentar convertir la edad a número
            int edad = Integer.parseInt(txtEdad.getText());  // Conversión de texto a número

            // Si es una nueva persona
            if (persona == null) {
                // Asegúrate de que el apellido esté vacío si no es necesario
                persona = new Persona(0, txtNombre.getText(), "", txtCorreo.getText(), txtTelefono.getText(), txtDireccion.getText(), edad);
                controlador.agregarPersona(persona);
            } else {
                // Si estamos editando, actualiza la persona
                persona.setNombre(txtNombre.getText());
                persona.setEdad(edad);  // Conversión de texto a número
                persona.setDireccion(txtDireccion.getText());
                persona.setTelefono(txtTelefono.getText());
                persona.setEmail(txtCorreo.getText());
                controlador.editarPersona(persona);
            }
            dispose();  // Cerrar formulario
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "La edad debe ser un número válido.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
