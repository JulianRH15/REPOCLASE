package co.edu.unbosque.proyecto.dto;


public class UsuarioDTO {
	private Integer id;
	private String nombre;
	private String clave;
	private String fechaNacimiento;
	private String correo;
	private int documento;

	public UsuarioDTO() {
		super();
		// TODO Auto-generated constructor stub
	}

	public UsuarioDTO(String nombre, String clave, String fechaNacimiento, String correo,int documento) {
		super();
		this.nombre = nombre;
		this.clave = clave;
		this.fechaNacimiento = fechaNacimiento;
		this.correo = correo;
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


	public int getDocumento() {
		return documento;
	}

	public void setDocumento(int documento) {
		this.documento = documento;
	}

	@Override
	public String toString() {
		return "UsuarioDTO [id=" + id + ", nombre=" + nombre + ", clave=" + clave + ", fechaNacimiento="
				+ fechaNacimiento + ", correo=" + correo +", documento=" + documento + "]";
	}
}
