package modelo;

public class Persona {
    
    private int id;
    private String nombre;
    private String apellido;
    private String email;
    private String telefono;
    private String direccion;
    private int edad;  // Añadimos el campo edad
    
    public Persona() {
    }
    
    // Constructor completo (con ID)
    public Persona(int id, String nombre, String apellido, String email, String telefono, String direccion, int edad) {
        this.id = id;
        this.nombre = nombre;
        this.apellido = apellido;
        this.email = email;
        this.telefono = telefono;
        this.direccion = direccion;
        this.edad = edad;  // Inicializamos el campo edad
    }
    
    // Constructor sin ID (para nueva persona, sin ID)
    public Persona(String nombre, String apellido, String email, String telefono, String direccion, int edad) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.email = email;
        this.telefono = telefono;
        this.direccion = direccion;
        this.edad = edad;  // Inicializamos el campo edad
    }
    
    // Getters y Setters
    public int getId() {
        return id;
    }
    
    public String getNombreCompleto() {
        return nombre + " " + apellido;
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
    
    public String getApellido() {
        return apellido;
    }
    
    public void setApellido(String apellido) {
        this.apellido = apellido;
    }
    
    public String getEmail() {
        return email;
    }
    
    public void setEmail(String email) {
        this.email = email;
    }
    
    public String getTelefono() {
        return telefono;
    }
    
    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }
    
    public String getDireccion() {
        return direccion;
    }
    
    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }
    
    public int getEdad() {
        return edad;
    }
    
    public void setEdad(int edad) {
        this.edad = edad;
    }

    // Método toString
    @Override
    public String toString() {
        return nombre + " " + apellido;  // Mostrar nombre completo de la persona
    }
}
