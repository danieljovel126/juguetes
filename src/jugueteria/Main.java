package jugueteria;  // Asegúrate de que esté en el paquete correcto

import vista.VentanaPrincipalAWT;

public class Main {

    public static void main(String[] args) {
        // Crear una nueva instancia de VentanaPrincipalAWT
        VentanaPrincipalAWT ventanaPrincipal = new VentanaPrincipalAWT();
        
        // Configurar la ventana
        ventanaPrincipal.setVisible(true);  // Hacer visible la ventana
        ventanaPrincipal.setLocationRelativeTo(null);  // Centrar la ventana en la pantalla
    }
}
