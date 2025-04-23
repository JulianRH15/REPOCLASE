package co.edu.unbosque.proyecto.model;



import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "pelicula")
public class Pelicula {
	private @Id @GeneratedValue(strategy = GenerationType.IDENTITY) Integer id;
	private String nombre;
	private String duracion;
	private String tipo;
	private String fechaEstreno;
	private String descripcion;
	private int edadRecomendada;
	private String url;

	public Pelicula() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Pelicula(String nombre, String duracion, String tipo, String fechaEstreno, String descripcion,
			int edadRecomendada, String url) {
		super();
		this.nombre = nombre;
		this.duracion = duracion;
		this.tipo = tipo;
		this.fechaEstreno = fechaEstreno;
		this.descripcion = descripcion;
		this.edadRecomendada = edadRecomendada;
		this.url = url;
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

	public String getDuracion() {
		return duracion;
	}

	public void setDuracion(String duracion) {
		this.duracion = duracion;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public String getFechaEstreno() {
		return fechaEstreno;
	}

	public void setFechaEstreno(String fechaEstreno) {
		this.fechaEstreno = fechaEstreno;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public int getEdadRecomendada() {
		return edadRecomendada;
	}

	public void setEdadRecomendada(int edadRecomendada) {
		this.edadRecomendada = edadRecomendada;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	@Override
	public String toString() {
		return "Pelicula [id=" + id + ", nombre=" + nombre + ", duracion=" + duracion + ", tipo=" + tipo
				+ ", fechaEstreno=" + fechaEstreno + ", descripcion=" + descripcion + ", edadRecomendada="
				+ edadRecomendada + ", url=" + url + "]";
	}
}
