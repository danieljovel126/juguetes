package vista;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import modelo.Persona;
import controlador.ControladorDonacion;
import modelo.Juguete;

public class VentanaPrincipalAWT extends Frame {

    private static final long serialVersionUID = 1L;
    private java.awt.List listaVisualJuguetes, listaPersonas;
    private Button btnAgregar, btnEliminar, btnAgregarPersona, btnDonar, btnRefrescar;
    private Button btnEditarJuguete, btnEditarPersona, btnVerDetalles;
    private TextField txtBuscarJuguete, txtBuscarPersona;
    private Label lblContadorJuguetes, lblContadorPersonas, lblEstado;
    private Choice filtroEstado;
    private ControladorDonacion controlador;
    
    // Colores personalizados
    private final Color COLOR_PRIMARIO = new Color(63, 81, 181);
    private final Color COLOR_SECUNDARIO = new Color(33, 150, 243);
    private final Color COLOR_ACENTO = new Color(255, 193, 7);
    private final Color COLOR_FONDO = new Color(245, 245, 245);
    private final Color COLOR_PANEL = new Color(255, 255, 255);
    private final Color COLOR_TEXTO = new Color(33, 33, 33);
    private final Color COLOR_EXITO = new Color(76, 175, 80);
    private final Color COLOR_PELIGRO = new Color(244, 67, 54);

    public VentanaPrincipalAWT() {
        inicializarVentana();
        inicializarControlador();
        crearComponentes();
        configurarLayout();
        configurarEventos();
        cargarDatosIniciales();
        configurarCierreVentana();
    }

    private void inicializarVentana() {
        setTitle("Sistema de Donaciones de Juguetes - Gestión Integral");
        setSize(1000, 700);
        setLocationRelativeTo(null);
        setBackground(COLOR_FONDO);
        setResizable(true);
        

        try {
           
        } catch (Exception e) {

        }
    }

    private void inicializarControlador() {
        controlador = new ControladorDonacion(this);
    }

    private void crearComponentes() {
        // Listas principales
        listaVisualJuguetes = new java.awt.List(10, false);
        listaPersonas = new java.awt.List(10, false);
        
        // Personalizar listas
        personalizarLista(listaVisualJuguetes);
        personalizarLista(listaPersonas);

        // Botones principales con iconos textuales
        btnAgregar = crearBoton("Agregar Juguete", COLOR_EXITO);
        btnEliminar = crearBoton("Eliminar", COLOR_PELIGRO);
        btnEditarJuguete = crearBoton("Editar", COLOR_SECUNDARIO);
        btnAgregarPersona = crearBoton("Agregar Persona", COLOR_EXITO);
        btnEditarPersona = crearBoton("Editar Persona", COLOR_SECUNDARIO);
        btnDonar = crearBoton("Registrar Donación", COLOR_ACENTO);
        btnRefrescar = crearBoton("Refrescar", COLOR_PRIMARIO);
        btnVerDetalles = crearBoton("Ver Detalles", COLOR_SECUNDARIO);

        // Campos de búsqueda
        txtBuscarJuguete = new TextField(20);
        txtBuscarPersona = new TextField(20);
        personalizarTextField(txtBuscarJuguete, "Buscar juguete...");
        personalizarTextField(txtBuscarPersona, "Buscar persona...");

        // Filtro de estado
        filtroEstado = new Choice();
        filtroEstado.add("Todos los estados");
        filtroEstado.add("Nuevo");
        filtroEstado.add("Usado");
        filtroEstado.add("Dañado");
        filtroEstado.setBackground(Color.WHITE);
        filtroEstado.setFont(new Font("Arial", Font.PLAIN, 12));

        // Labels informativos
        lblContadorJuguetes = crearLabel("Juguetes: 0", COLOR_TEXTO);
        lblContadorPersonas = crearLabel("Personas: 0", COLOR_TEXTO);
        lblEstado = crearLabel("Sistema listo", COLOR_EXITO);
    }

    private Button crearBoton(String texto, Color color) {
        Button btn = new Button(texto);
        btn.setBackground(color);
        btn.setForeground(Color.WHITE);
        btn.setFont(new Font("Arial", Font.BOLD, 11));
        
        // Efecto hover simulado
        btn.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseEntered(java.awt.event.MouseEvent e) {
                btn.setBackground(color.brighter());
            }
            
            @Override
            public void mouseExited(java.awt.event.MouseEvent e) {
                btn.setBackground(color);
            }
        });
        
        return btn;
    }

    private void personalizarLista(java.awt.List lista) {
        lista.setBackground(Color.WHITE);
        lista.setForeground(COLOR_TEXTO);
        lista.setFont(new Font("Monospaced", Font.PLAIN, 12));
    }

    private void personalizarTextField(TextField textField, String placeholder) {
        textField.setBackground(Color.WHITE);
        textField.setForeground(COLOR_TEXTO);
        textField.setFont(new Font("Arial", Font.PLAIN, 12));
        
        // Simular placeholder
        textField.addFocusListener(new java.awt.event.FocusAdapter() {
            @Override
            public void focusGained(java.awt.event.FocusEvent e) {
                if (textField.getText().equals(placeholder)) {
                    textField.setText("");
                    textField.setForeground(COLOR_TEXTO);
                }
            }
            
            @Override
            public void focusLost(java.awt.event.FocusEvent e) {
                if (textField.getText().isEmpty()) {
                    textField.setText(placeholder);
                    textField.setForeground(Color.GRAY);
                }
            }
        });
        
        textField.setText(placeholder);
        textField.setForeground(Color.GRAY);
    }

    private Label crearLabel(String texto, Color color) {
        Label label = new Label(texto);
        label.setForeground(color);
        label.setFont(new Font("Arial", Font.BOLD, 12));
        return label;
    }

    private void configurarLayout() {
        setLayout(new BorderLayout(10, 10));

        // Panel superior con título y estado
        Panel panelSuperior = new Panel(new BorderLayout());
        panelSuperior.setBackground(COLOR_PRIMARIO);
        Label titulo = new Label("SISTEMA DE DONACIONES DE JUGUETES", Label.CENTER);
        titulo.setFont(new Font("Arial", Font.BOLD, 16));
        titulo.setForeground(Color.WHITE);
        panelSuperior.add(titulo, BorderLayout.CENTER);
        panelSuperior.add(lblEstado, BorderLayout.EAST);
        
        // Panel principal dividido
        Panel panelPrincipal = new Panel(new GridLayout(1, 2, 15, 0));
        panelPrincipal.setBackground(COLOR_FONDO);
        
        // Panel de juguetes mejorado
        Panel panelJuguetes = crearPanelJuguetes();
        
        // Panel de personas mejorado
        Panel panelPersonas = crearPanelPersonas();
        
        panelPrincipal.add(panelJuguetes);
        panelPrincipal.add(panelPersonas);

        // Panel inferior con botones globales
        Panel panelInferior = new Panel(new FlowLayout());
        panelInferior.setBackground(COLOR_FONDO);
        panelInferior.add(btnRefrescar);
        panelInferior.add(btnVerDetalles);
        panelInferior.add(btnDonar);

        add(panelSuperior, BorderLayout.NORTH);
        add(panelPrincipal, BorderLayout.CENTER);
        add(panelInferior, BorderLayout.SOUTH);

        // Crear menú mejorado
        crearMenuMejorado();
    }

    private Panel crearPanelJuguetes() {
        Panel panel = new Panel(new BorderLayout(5, 5));
        panel.setBackground(COLOR_PANEL);
        
        // Encabezado
        Panel encabezado = new Panel(new BorderLayout());
        encabezado.setBackground(COLOR_SECUNDARIO);
        Label titulo = new Label("GESTIÓN DE JUGUETES", Label.CENTER);
        titulo.setFont(new Font("Arial", Font.BOLD, 14));
        titulo.setForeground(Color.WHITE);
        encabezado.add(titulo, BorderLayout.CENTER);
        encabezado.add(lblContadorJuguetes, BorderLayout.EAST);
        
        // Panel de búsqueda y filtros
        Panel panelBusqueda = new Panel(new BorderLayout(5, 5));
        panelBusqueda.setBackground(COLOR_PANEL);
        panelBusqueda.add(new Label("Buscar:"), BorderLayout.WEST);
        panelBusqueda.add(txtBuscarJuguete, BorderLayout.CENTER);
        
        Panel panelFiltro = new Panel(new BorderLayout(5, 5));
        panelFiltro.setBackground(COLOR_PANEL);
        panelFiltro.add(new Label("Filtrar:"), BorderLayout.WEST);
        panelFiltro.add(filtroEstado, BorderLayout.CENTER);
        
        Panel panelControles = new Panel(new GridLayout(2, 1, 5, 5));
        panelControles.add(panelBusqueda);
        panelControles.add(panelFiltro);
        
        // Panel de botones
        Panel panelBotones = new Panel(new GridLayout(1, 3, 5, 5));
        panelBotones.setBackground(COLOR_PANEL);
        panelBotones.add(btnAgregar);
        panelBotones.add(btnEditarJuguete);
        panelBotones.add(btnEliminar);
        
        panel.add(encabezado, BorderLayout.NORTH);
        panel.add(panelControles, BorderLayout.NORTH);
        panel.add(listaVisualJuguetes, BorderLayout.CENTER);
        panel.add(panelBotones, BorderLayout.SOUTH);
        
        return panel;
    }

    private Panel crearPanelPersonas() {
        Panel panel = new Panel(new BorderLayout(5, 5));
        panel.setBackground(COLOR_PANEL);
        
        // Encabezado
        Panel encabezado = new Panel(new BorderLayout());
        encabezado.setBackground(COLOR_ACENTO);
        Label titulo = new Label("GESTIÓN DE PERSONAS", Label.CENTER);
        titulo.setFont(new Font("Arial", Font.BOLD, 14));
        titulo.setForeground(Color.BLACK);
        encabezado.add(titulo, BorderLayout.CENTER);
        encabezado.add(lblContadorPersonas, BorderLayout.EAST);
        
        // Panel de búsqueda
        Panel panelBusqueda = new Panel(new BorderLayout(5, 5));
        panelBusqueda.setBackground(COLOR_PANEL);
        panelBusqueda.add(new Label("Buscar:"), BorderLayout.WEST);
        panelBusqueda.add(txtBuscarPersona, BorderLayout.CENTER);
        
        // Panel de botones
        Panel panelBotones = new Panel(new GridLayout(1, 2, 5, 5));
        panelBotones.setBackground(COLOR_PANEL);
        panelBotones.add(btnAgregarPersona);
        panelBotones.add(btnEditarPersona);
        
        panel.add(encabezado, BorderLayout.NORTH);
        panel.add(panelBusqueda, BorderLayout.NORTH);
        panel.add(listaPersonas, BorderLayout.CENTER);
        panel.add(panelBotones, BorderLayout.SOUTH);
        
        return panel;
    }

    private void crearMenuMejorado() {
        MenuBar menuBar = new MenuBar();
        
        // Menú Archivo
        Menu menuArchivo = new Menu("Archivo");
        MenuItem exportarItem = new MenuItem("Exportar Datos");
        MenuItem importarItem = new MenuItem("Importar Datos");
        MenuItem salirItem = new MenuItem("Salir");
        
        menuArchivo.add(exportarItem);
        menuArchivo.add(importarItem);
        menuArchivo.addSeparator();
        menuArchivo.add(salirItem);
        
        // Menú Ver
        Menu menuVer = new Menu("Ver");
        MenuItem estadisticasItem = new MenuItem("Estadísticas");
        MenuItem reportesItem = new MenuItem("Reportes");
        
        menuVer.add(estadisticasItem);
        menuVer.add(reportesItem);
        
        // Menú Ayuda
        Menu menuAyuda = new Menu("Ayuda");
        MenuItem acercaItem = new MenuItem("Acerca de");
        MenuItem manualItem = new MenuItem("Manual de usuario");
        
        menuAyuda.add(manualItem);
        menuAyuda.add(acercaItem);
        
        menuBar.add(menuArchivo);
        menuBar.add(menuVer);
        menuBar.add(menuAyuda);
        
        setMenuBar(menuBar);
        
        // Configurar eventos del menú
        salirItem.addActionListener(e -> confirmarSalida());
        estadisticasItem.addActionListener(e -> mostrarEstadisticas());
    }

    private void configurarEventos() {
        // Eventos de botones principales
        btnAgregar.addActionListener(e -> {
            actualizarEstado("Abriendo formulario de juguete...");
            new FormularioJugueteAWT(VentanaPrincipalAWT.this, null).setVisible(true);
        });

        btnEliminar.addActionListener(e -> eliminarJugueteSeleccionado());
        
        btnEditarJuguete.addActionListener(e -> editarJugueteSeleccionado());

        btnAgregarPersona.addActionListener(e -> {
            actualizarEstado("Abriendo formulario de persona...");
            new FormularioPersonaAWT(VentanaPrincipalAWT.this, null).setVisible(true);
        });
        
        btnEditarPersona.addActionListener(e -> editarPersonaSeleccionada());

        btnDonar.addActionListener(e -> {
            actualizarEstado("Abriendo formulario de donación...");
            new FormularioDonacionAWT(VentanaPrincipalAWT.this).setVisible(true);
        });

        btnRefrescar.addActionListener(e -> {
            actualizarEstado("Refrescando datos...");
            cargarDatosIniciales();
            actualizarEstado("Datos actualizados correctamente");
        });

        btnVerDetalles.addActionListener(e -> mostrarDetallesSeleccionados());

        // Eventos de búsqueda en tiempo real
        txtBuscarJuguete.addTextListener(e -> filtrarJuguetes());
        txtBuscarPersona.addTextListener(e -> filtrarPersonas());
        
        // Evento de filtro de estado
        filtroEstado.addItemListener(e -> filtrarJuguetes());
        
        // Doble clic en listas para editar
        listaVisualJuguetes.addActionListener(e -> editarJugueteSeleccionado());
        listaPersonas.addActionListener(e -> editarPersonaSeleccionada());
    }

    private void eliminarJugueteSeleccionado() {
        int selectedIndex = listaVisualJuguetes.getSelectedIndex();
        if (selectedIndex >= 0) {
            String item = listaVisualJuguetes.getItem(selectedIndex);
            if (confirmarEliminacion("¿Está seguro de eliminar el juguete: " + item + "?")) {
                controlador.eliminarJuguete(selectedIndex + 1);
                controlador.cargarJuguetes();
                actualizarEstado("Juguete eliminado correctamente");
            }
        } else {
            mostrarMensajeError("Por favor selecciona un juguete para eliminar.");
        }
    }

    private void editarJugueteSeleccionado() {
        int selectedIndex = listaVisualJuguetes.getSelectedIndex();
        if (selectedIndex >= 0) {

            actualizarEstado("Editando juguete seleccionado...");

        } else {
            mostrarMensajeAdvertencia("Por favor selecciona un juguete para editar.");
        }
    }

    private void editarPersonaSeleccionada() {
        int selectedIndex = listaPersonas.getSelectedIndex();
        if (selectedIndex >= 0) {
            actualizarEstado("Editando persona seleccionada...");

        } else {
            mostrarMensajeAdvertencia("Por favor selecciona una persona para editar.");
        }
    }

    private void filtrarJuguetes() {
        String textoBusqueda = txtBuscarJuguete.getText().toLowerCase();
        String estadoFiltro = filtroEstado.getSelectedItem();
        

        actualizarEstado("Aplicando filtros de búsqueda...");
    }

    private void filtrarPersonas() {
        String textoBusqueda = txtBuscarPersona.getText().toLowerCase();

        actualizarEstado("Buscando personas...");
    }

    private void mostrarDetallesSeleccionados() {
        StringBuilder detalles = new StringBuilder();
        detalles.append("DETALLES DEL SISTEMA\n\n");
        
        if (listaVisualJuguetes.getSelectedIndex() >= 0) {
            detalles.append("Juguete seleccionado:\n");
            detalles.append(listaVisualJuguetes.getSelectedItem()).append("\n\n");
        }
        
        if (listaPersonas.getSelectedIndex() >= 0) {
            detalles.append("Persona seleccionada:\n");
            detalles.append(listaPersonas.getSelectedItem()).append("\n\n");
        }
        
        detalles.append("Total de juguetes: ").append(listaVisualJuguetes.getItemCount()).append("\n");
        detalles.append("Total de personas: ").append(listaPersonas.getItemCount()).append("\n");
        
        mostrarDialogoInformativo("Detalles del Sistema", detalles.toString());
    }

    private void mostrarEstadisticas() {
        StringBuilder stats = new StringBuilder();
        stats.append("ESTADÍSTICAS DEL SISTEMA\n\n");
        stats.append("Resumen General:\n");
        stats.append("Total de juguetes registrados: ").append(listaVisualJuguetes.getItemCount()).append("\n");
        stats.append("Total de personas registradas: ").append(listaPersonas.getItemCount()).append("\n");
        stats.append("Promedio de edad: [Calcular]\n");
        stats.append("Juguetes por estado: [Implementar]\n\n");
        stats.append("Estado del sistema: Operativo\n");
        stats.append("Última actualización: ").append(new java.util.Date().toString());
        
        mostrarDialogoInformativo("Estadísticas", stats.toString());
    }

    private boolean confirmarEliminacion(String mensaje) {
        Dialog dialogo = new Dialog(this, "Confirmar Eliminación", true);
        dialogo.setLayout(new BorderLayout(10, 10));
        dialogo.setBackground(COLOR_FONDO);
        
        Label lblMensaje = new Label(mensaje, Label.CENTER);
        lblMensaje.setFont(new Font("Arial", Font.PLAIN, 12));
        
        Panel panelBotones = new Panel(new FlowLayout());
        Button btnSi = crearBoton("Sí, eliminar", COLOR_PELIGRO);
        Button btnNo = crearBoton("Cancelar", COLOR_SECUNDARIO);
        
        final boolean[] resultado = {false};
        
        btnSi.addActionListener(e -> {
            resultado[0] = true;
            dialogo.dispose();
        });
        
        btnNo.addActionListener(e -> dialogo.dispose());
        
        panelBotones.add(btnSi);
        panelBotones.add(btnNo);
        
        dialogo.add(lblMensaje, BorderLayout.CENTER);
        dialogo.add(panelBotones, BorderLayout.SOUTH);
        dialogo.setSize(400, 150);
        dialogo.setLocationRelativeTo(this);
        dialogo.setVisible(true);
        
        return resultado[0];
    }

    private void confirmarSalida() {
        if (confirmarEliminacion("¿Está seguro de que desea salir del sistema?")) {
            System.exit(0);
        }
    }

    private void mostrarMensajeError(String mensaje) {
        mostrarMensajePersonalizado("Error", mensaje, COLOR_PELIGRO);
    }

    private void mostrarMensajeAdvertencia(String mensaje) {
        mostrarMensajePersonalizado("Advertencia", mensaje, COLOR_ACENTO);
    }

    private void mostrarMensajeExito(String mensaje) {
        mostrarMensajePersonalizado("Éxito", mensaje, COLOR_EXITO);
    }

    private void mostrarMensajePersonalizado(String titulo, String mensaje, Color color) {
        Dialog dialogo = new Dialog(this, titulo, true);
        dialogo.setLayout(new BorderLayout(10, 10));
        dialogo.setBackground(COLOR_FONDO);
        
        Panel panelTitulo = new Panel();
        panelTitulo.setBackground(color);
        Label lblTitulo = new Label(titulo, Label.CENTER);
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 14));
        lblTitulo.setForeground(Color.WHITE);
        panelTitulo.add(lblTitulo);
        
        Label lblMensaje = new Label(mensaje, Label.CENTER);
        lblMensaje.setFont(new Font("Arial", Font.PLAIN, 12));
        
        Button btnAceptar = crearBoton("Aceptar", color);
        btnAceptar.addActionListener(e -> dialogo.dispose());
        
        Panel panelBoton = new Panel(new FlowLayout());
        panelBoton.add(btnAceptar);
        
        dialogo.add(panelTitulo, BorderLayout.NORTH);
        dialogo.add(lblMensaje, BorderLayout.CENTER);
        dialogo.add(panelBoton, BorderLayout.SOUTH);
        dialogo.setSize(350, 180);
        dialogo.setLocationRelativeTo(this);
        dialogo.setVisible(true);
    }

    private void mostrarDialogoInformativo(String titulo, String contenido) {
        Dialog dialogo = new Dialog(this, titulo, true);
        dialogo.setLayout(new BorderLayout(10, 10));
        dialogo.setBackground(COLOR_FONDO);
        
        TextArea txtArea = new TextArea(contenido, 15, 50, TextArea.SCROLLBARS_VERTICAL_ONLY);
        txtArea.setEditable(false);
        txtArea.setBackground(Color.WHITE);
        txtArea.setFont(new Font("Monospaced", Font.PLAIN, 11));
        
        Button btnCerrar = crearBoton("Cerrar", COLOR_SECUNDARIO);
        btnCerrar.addActionListener(e -> dialogo.dispose());
        
        Panel panelBoton = new Panel(new FlowLayout());
        panelBoton.add(btnCerrar);
        
        dialogo.add(txtArea, BorderLayout.CENTER);
        dialogo.add(panelBoton, BorderLayout.SOUTH);
        dialogo.setSize(600, 400);
        dialogo.setLocationRelativeTo(this);
        dialogo.setVisible(true);
    }

    private void actualizarEstado(String mensaje) {
        lblEstado.setText(mensaje);
        lblEstado.setForeground(COLOR_EXITO);
        
        // Crear un timer simple para limpiar el mensaje después de 3 segundos
        new Thread(() -> {
            try {
                Thread.sleep(3000);
                lblEstado.setText("Sistema listo");
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }).start();
    }

    private void cargarDatosIniciales() {
        controlador.cargarJuguetes();
        controlador.cargarPersonas();
        actualizarContadores();
    }

    private void actualizarContadores() {
        lblContadorJuguetes.setText("Juguetes: " + listaVisualJuguetes.getItemCount());
        lblContadorPersonas.setText("Personas: " + listaPersonas.getItemCount());
    }

    private void configurarCierreVentana() {
        addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosing(java.awt.event.WindowEvent windowEvent) {
                confirmarSalida();
            }
        });
    }

    // Métodos públicos para el controlador
    public void cargarJuguetes(java.util.List<Juguete> juguetes) {
        listaVisualJuguetes.removeAll();
        for (Juguete juguete : juguetes) {
            String estado = juguete.getEstado();
            String icono = obtenerIconoEstado(estado);
            listaVisualJuguetes.add(String.format("%s %03d: %-20s [%s]", 
                icono, juguete.getId(), juguete.getNombre(), estado));
        }
        actualizarContadores();
        actualizarEstado("Juguetes cargados: " + juguetes.size());
    }

    public void cargarPersonas(java.util.List<Persona> personas) {
        listaPersonas.removeAll();
        for (Persona persona : personas) {
            String icono = persona.getEdad() < 18 ? "👶" : "👤";
            listaPersonas.add(String.format("%s %03d: %-20s (%d años)", 
                icono, persona.getId(), persona.getNombre(), persona.getEdad()));
        }
        actualizarContadores();
        actualizarEstado("Personas cargadas: " + personas.size());
    }

    private String obtenerIconoEstado(String estado) {
        switch (estado.toLowerCase()) {
            case "nuevo": return "✨";
            case "usado": return "♻️";
            case "dañado": return "🔧";
            default: return "🎁";
        }
    }

    // Método para mostrar mensajes simples (compatibilidad con código original)
    private void mostrarMensaje(String mensaje) {
        mostrarMensajePersonalizado("ℹInformación", mensaje, COLOR_SECUNDARIO);
    }
}