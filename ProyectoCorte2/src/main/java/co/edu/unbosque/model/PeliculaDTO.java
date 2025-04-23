package co.edu.unbosque.model;

import java.util.Date;

public class PeliculaDTO {
	private Integer id;
	private String nombre;
	private String duracion;
	private String tipo;
	private String fechaEstreno;
	private String descripcion;
	private int edadRecomendada;
	private String url;
	
	public PeliculaDTO() {
		super();
		// TODO Auto-generated constructor stub
	}
	public PeliculaDTO(String nombre, String duracion, String tipo, String fechaEstreno, String descripcion,
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
		return "{\r\n" +
		        "  \"nombre\": \"" + nombre + "\",\r\n" +
		        "  \"duracion\": " + duracion + ",\r\n" +
		        "  \"tipo\": \"" + tipo + "\",\r\n" +
		        "  \"fechaEstreno\": \"" + fechaEstreno + "\",\r\n" +
		        "  \"descripcion\": \"" + descripcion + "\",\r\n" +
		        "  \"edadRecomendada\": " + edadRecomendada + ",\r\n" +
		        "  \"url\": \"" + url + "\"\r\n" +
		        "}";
	}
}
