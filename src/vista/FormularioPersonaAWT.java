package vista;

import java.awt.*;
import java.awt.event.*;
import modelo.Persona;
import controlador.ControladorPersona;

public class FormularioPersonaAWT extends Dialog {

    private TextField txtNombre, txtEdad, txtDireccion, txtTelefono, txtCorreo;
    private Button btnGuardar, btnCancelar;
    private ControladorPersona controlador;
    private Persona persona;

    public FormularioPersonaAWT(Frame parent, Persona persona) {
        super(parent, persona == null ? "Agregar Persona" : "Editar Persona", true);
        this.persona = persona;
        controlador = new ControladorPersona((VentanaPrincipalAWT) parent);

        setSize(400, 300);
        setLayout(new GridLayout(6, 2));

        txtNombre = new TextField(persona != null ? persona.getNombre() : "");
        txtEdad = new TextField(persona != null ? String.valueOf(persona.getEdad()) : "");
        txtDireccion = new TextField(persona != null ? persona.getDireccion() : "");
        txtTelefono = new TextField(persona != null ? persona.getTelefono() : "");
        txtCorreo = new TextField(persona != null ? persona.getCorreo() : "");

        btnGuardar = new Button("Guardar");
        btnCancelar = new Button("Cancelar");

        btnGuardar.addActionListener(e -> guardarPersona());
        btnCancelar.addActionListener(e -> dispose());

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

    private void guardarPersona() {
        if (persona == null) {
            // Si es una nueva persona
            persona = new Persona(0, txtNombre.getText(), Integer.parseInt(txtEdad.getText()), txtDireccion.getText(),
                    txtTelefono.getText(), txtCorreo.getText());
            controlador.agregarPersona(persona);
        } else {
            // Si estamos editando
            persona.setNombre(txtNombre.getText());
            persona.setEdad(Integer.parseInt(txtEdad.getText()));
            persona.setDireccion(txtDireccion.getText());
            persona.setTelefono(txtTelefono.getText());
            persona.setCorreo(txtCorreo.getText());
            controlador.editarPersona(persona);
        }
        dispose();  // Cerrar formulario
    }
}
