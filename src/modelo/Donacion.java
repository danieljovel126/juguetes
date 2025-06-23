package modelo;

import java.util.Date;

/**
 * Clase modelo para representar una Donación en el sistema de juguetería
 */
public class Donacion {
    
    private int id;
    private int idPersona;
    private int idJuguete;
    private Date fechaDonacion;
    private int cantidad;
    private String observaciones;
    
    // Referencias a objetos relacionados (opcional, para facilitar el uso)
    private Persona persona;
    private Juguete juguete;
    
    // Constructor vacío
    public Donacion() {
        this.fechaDonacion = new Date(); // Fecha actual por defecto
        this.cantidad = 1; // Cantidad por defecto
    }
    
    // Constructor con parámetros básicos
    public Donacion(int idPersona, int idJuguete, int cantidad) {
        this();
        this.idPersona = idPersona;
        this.idJuguete = idJuguete;
        this.cantidad = cantidad;
    }
    
    // Constructor con parámetros completos
    public Donacion(int idPersona, int idJuguete, Date fechaDonacion, int cantidad, String observaciones) {
        this.idPersona = idPersona;
        this.idJuguete = idJuguete;
        this.fechaDonacion = fechaDonacion;
        this.cantidad = cantidad;
        this.observaciones = observaciones;
    }
    
    // Constructor completo con ID
    public Donacion(int id, int idPersona, int idJuguete, Date fechaDonacion, int cantidad, String observaciones) {
        this.id = id;
        this.idPersona = idPersona;
        this.idJuguete = idJuguete;
        this.fechaDonacion = fechaDonacion;
        this.cantidad = cantidad;
        this.observaciones = observaciones;
    }
    
    // Getters y Setters
    public int getId() {
        return id;
    }
    
    public void setId(int id) {
        this.id = id;
    }
    
    public int getIdPersona() {
        return idPersona;
    }
    
    public void setIdPersona(int idPersona) {
        this.idPersona = idPersona;
    }
    
    public int getIdJuguete() {
        return idJuguete;
    }
    
    public void setIdJuguete(int idJuguete) {
        this.idJuguete = idJuguete;
    }
    
    public Date getFechaDonacion() {
        return fechaDonacion;
    }
    
    public void setFechaDonacion(Date fechaDonacion) {
        this.fechaDonacion = fechaDonacion;
    }
    
    public int getCantidad() {
        return cantidad;
    }
    
    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }
    
    public String getObservaciones() {
        return observaciones;
    }
    
    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }
    
    // Getters y Setters para objetos relacionados
    public Persona getPersona() {
        return persona;
    }
    
    public void setPersona(Persona persona) {
        this.persona = persona;
        if (persona != null) {
            this.idPersona = persona.getId();
        }
    }
    
    public Juguete getJuguete() {
        return juguete;
    }
    
    public void setJuguete(Juguete juguete) {
        this.juguete = juguete;
        if (juguete != null) {
            this.idJuguete = juguete.getId();
        }
    }
    
    // Método toString para mostrar información de la donación
    @Override
    public String toString() {
        // Validar si persona y juguete están inicializados antes de intentar acceder a sus métodos
        String personaNombre = persona != null ? persona.getNombreCompleto() : "Persona ID: " + idPersona;
        String jugueteNombre = juguete != null ? juguete.getNombre() : "Juguete ID: " + idJuguete;

        return String.format("Donación: %s donó %d %s el %s", 
            personaNombre, cantidad, jugueteNombre, 
            fechaDonacion != null ? fechaDonacion.toString() : "fecha no definida");
    }
    
    // Método para obtener información resumida
    public String getResumen() {
        return String.format("ID: %d - Cantidad: %d - Fecha: %s", 
                           id, cantidad, 
                           fechaDonacion != null ? fechaDonacion.toString() : "N/A");
    }
    
    // Método equals para comparar donaciones
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        
        Donacion donacion = (Donacion) obj;
        return id == donacion.id;
    }
    
    // Método hashCode
    @Override
    public int hashCode() {
        return Integer.hashCode(id);
    }
}