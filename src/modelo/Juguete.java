package modelo;

public class Juguete {
    private int id;
    private String nombre;
    private String categoria;
    private String estado;   // Nuevo o Usado
    private String ubicacion;
    private String propietario;

    // Constructor
    public Juguete(int id, String nombre, String categoria, String estado, String ubicacion, String propietario) {
        this.id = id;
        this.nombre = nombre;
        this.categoria = categoria;
        this.estado = estado;
        this.ubicacion = ubicacion;
        this.propietario = propietario;
    }

    // Getters y Setters
    public int getId() { return id; }
    public String getNombre() { return nombre; }
    public String getCategoria() { return categoria; }
    public String getEstado() { return estado; }
    public String getUbicacion() { return ubicacion; }
    public String getPropietario() { return propietario; }

    public void setId(int id) { this.id = id; }
    public void setNombre(String nombre) { this.nombre = nombre; }
    public void setCategoria(String categoria) { this.categoria = categoria; }
    public void setEstado(String estado) { this.estado = estado; }
    public void setUbicacion(String ubicacion) { this.ubicacion = ubicacion; }
    public void setPropietario(String propietario) { this.propietario = propietario; }
}
