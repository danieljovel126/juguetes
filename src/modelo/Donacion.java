package modelo;

public class Donacion {
    private int id;
    private Juguete juguete;
    private Persona persona;
    private String fechaDonacion;

    public Donacion(int id, Juguete juguete, Persona persona, String fechaDonacion) {
        this.id = id;
        this.juguete = juguete;
        this.persona = persona;
        this.fechaDonacion = fechaDonacion;
    }

    // Getters y Setters
    public int getId() { return id; }
    public Juguete getJuguete() { return juguete; }
    public Persona getPersona() { return persona; }
    public String getFechaDonacion() { return fechaDonacion; }

    public void setId(int id) { this.id = id; }
    public void setJuguete(Juguete juguete) { this.juguete = juguete; }
    public void setPersona(Persona persona) { this.persona = persona; }
    public void setFechaDonacion(String fechaDonacion) { this.fechaDonacion = fechaDonacion; }
}
