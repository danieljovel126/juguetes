package modelo;

public class Persona {
	private int id;
	private String nombre;
	private int edad;
	private String direccion;
	private String telefono;
	private String correo;
	
	public Persona(int id, String nombre, int edad, String direccion, String telefono, String correo) {
		this.id = id;
		this.nombre = nombre;
		this.edad = edad;
		this.direccion = direccion;
		this.telefono = telefono;
		this.correo = correo;
		
	}
	
	//Getters y Setters
	public int getId() { return id; }
	public String getNombre() {return nombre;}
	public int getEdad() {return edad;}
	public String getDireccion() {return direccion;}
	public String getTelefono() {return telefono;}
	public String getCorreo() {return correo;}
	
	public void setId(int id) {this.id = id;}
	public void setNombre(String nombre) {this.nombre = nombre;}
	public void setEdad(int edad) {this.edad = edad;}
	public void setDireccion(String direccion) {this.direccion = direccion;}
	public void setTelefono(String telefono) {this.telefono = telefono;}
	public void setCorreo(String correo) {this.correo = correo;}

}
