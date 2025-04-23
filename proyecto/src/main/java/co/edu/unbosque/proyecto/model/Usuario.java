package co.edu.unbosque.proyecto.model;




import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "usuario")
public class Usuario {
	private @Id @GeneratedValue(strategy = GenerationType.IDENTITY) Integer id;
	private String nombre;
	private String clave;
	private String fechaNacimiento;
	private String correo;
	private int edad;
	private int documento;

	public Usuario() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Usuario(String nombre, String clave, String fechaNacimiento, String correo, int edad, int documento) {
		super();
		this.nombre = nombre;
		this.clave = clave;
		this.fechaNacimiento = fechaNacimiento;
		this.correo = correo;
		this.edad = edad;
		this.documento = documento;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getClave() {
		return clave;
	}

	public void setClave(String clave) {
		this.clave = clave;
	}

	public String getFechaNacimiento() {
		return fechaNacimiento;
	}

	public void setFechaNacimiento(String fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}

	public String getCorreo() {
		return correo;
	}

	public void setCorreo(String correo) {
		this.correo = correo;
	}

	public int getEdad() {
		return edad;
	}

	public void setEdad(int edad) {
		this.edad = edad;
	}

	public int getDocumento() {
		return documento;
	}

	public void setDocumento(int documento) {
		this.documento = documento;
	}

	@Override
	public String toString() {
		return "Usuario [id=" + id + ", nombre=" + nombre + ", clave=" + clave + ", fechaNacimiento=" + fechaNacimiento
				+ ", correo=" + correo + ", edad=" + edad + ", documento=" + documento + "]";
	}
}
