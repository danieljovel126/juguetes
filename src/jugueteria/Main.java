package jugueteria;

import conexion.Conexion;
import vista.VentanaPrincipalAWT;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

/**
 * Clase principal para inicializar la aplicación de Juguetería
 * Sistema de gestión de donaciones de juguetes
 */
public class Main {

    public static void main(String[] args) {
        // Configurar el Look and Feel del sistema
        configurarLookAndFeel();
        
        // Inicializar la base de datos
        inicializarBaseDatos();
        
        // Ejecutar la aplicación en el hilo de eventos de Swing
        SwingUtilities.invokeLater(() -> {
            try {
                // Crear e inicializar la ventana principal
                VentanaPrincipalAWT ventanaPrincipal = new VentanaPrincipalAWT();
                ventanaPrincipal.setVisible(true);
                
                System.out.println("=== SISTEMA DE JUGUETERÍA INICIADO ===");
                System.out.println("Aplicación ejecutándose correctamente...");
                
            } catch (Exception e) {
                System.err.println("Error al inicializar la aplicación: " + e.getMessage());
                e.printStackTrace();
                mostrarErrorYSalir("No se pudo inicializar la aplicación", e);
            }
        });
    }

    /**
     * Configura el Look and Feel de la aplicación
     */
    private static void configurarLookAndFeel() {
        try {
            // Intentar usar el Look and Feel del sistema operativo
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            System.out.println("Look and Feel configurado: " + UIManager.getLookAndFeel().getName());
            
        } catch (ClassNotFoundException | InstantiationException | 
                 IllegalAccessException | UnsupportedLookAndFeelException e) {
            
            System.err.println("No se pudo configurar el Look and Feel del sistema, usando el predeterminado");
            
            // Si falla, intentar con Nimbus (más moderno)
            try {
                for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
                    if ("Nimbus".equals(info.getName())) {
                        UIManager.setLookAndFeel(info.getClassName());
                        System.out.println("Look and Feel configurado: Nimbus");
                        break;
                    }
                }
            } catch (Exception ex) {
                System.err.println("Usando Look and Feel predeterminado");
            }
        }
    }

    /**
     * Inicializa la base de datos y crea las tablas necesarias
     */
    private static void inicializarBaseDatos() {
        try {
            System.out.println("Inicializando base de datos...");
            
            // Probar la conexión
            if (Conexion.probarConexion()) {
                System.out.println("✓ Conexión a base de datos establecida");
                
                // Crear las tablas si no existen
                Conexion.crearTablas();
                System.out.println("✓ Tablas de base de datos verificadas/creadas");
                
                // Insertar datos de prueba si es necesario
                insertarDatosDePrueba();
                
            } else {
                throw new RuntimeException("No se pudo establecer conexión con la base de datos");
            }
            
        } catch (Exception e) {
            System.err.println("Error al inicializar la base de datos: " + e.getMessage());
            mostrarErrorYSalir("Error de Base de Datos", e);
        }
    }

    /**
     * Inserta algunos datos de prueba si la base de datos está vacía
     */
    private static void insertarDatosDePrueba() {
        try {
            // Lógica para insertar datos de prueba, si es necesario
            System.out.println("Verificando datos de prueba...");
            
            // Aquí puedes agregar lógica para insertar datos de prueba si la base de datos está vacía
        } catch (Exception e) {
            System.err.println("Error al insertar datos de prueba: " + e.getMessage());
            // No es crítico, la aplicación puede continuar
        }
    }

    /**
     * Muestra un error crítico y termina la aplicación
     */
    private static void mostrarErrorYSalir(String mensaje, Exception e) {
        System.err.println("=== ERROR CRÍTICO ===");
        System.err.println(mensaje);
        System.err.println("Detalles del error:");
        e.printStackTrace();
        
        System.exit(1); // Termina la aplicación
    }

    /**
     * Hook para manejar el cierre de la aplicación
     */
    static {
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            System.out.println("=== CERRANDO SISTEMA DE JUGUETERÍA ===");
            System.out.println("Aplicación terminada correctamente.");
        }));
    }
}
