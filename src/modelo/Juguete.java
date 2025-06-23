package modelo;

public class Juguete {

    private int id;
    private String nombre;
    private String tipo;
    private String estado;  // Estado como String
    private String descripcion;  // Descripción como String
    private String propietario;  // Propietario como String

    // Constructor completo
    public Juguete(int id, String nombre, String tipo, String estado, String descripcion, String propietario) {
        this.id = id;
        this.nombre = nombre;
        this.tipo = tipo;
        this.estado = estado;
        this.descripcion = descripcion;
        this.propietario = propietario;
    }

    // Getters y Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getPropietario() {
        return propietario;
    }

    public void setPropietario(String propietario) {
        this.propietario = propietario;
    }

    // Método toString para mostrar información del juguete
    @Override
    public String toString() {
        return nombre + " (" + tipo + ")";
    }
}
