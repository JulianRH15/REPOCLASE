package co.edu.unbosque.bean;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;
import jakarta.inject.Named;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.component.UIComponent;
import jakarta.faces.context.FacesContext;
import jakarta.faces.validator.ValidatorException;
import jakarta.faces.view.ViewScoped;
import co.edu.unbosque.model.UsuarioDTO;
import co.edu.unbosque.model.persistence.UsuarioDAO;

@Named("UsuarioBean")
@ViewScoped
public class UsuarioBean implements Serializable {

	private static final long serialVersionUID = 1L;
	private List<UsuarioDTO> listaUsuarios;
	private Integer id;
	private String nombre;
	private String clave;
	private String fechaNacimiento;
	private String correo;
	private int documento;
	protected int index;
	private LocalDate getFecha;
	

	private UsuarioDAO uDAO;

	public UsuarioBean() {
		listaUsuarios = new ArrayList<>();
		uDAO = new UsuarioDAO();
		mostrarTodo();
	}

	public String guardar() {
	    try {
	        UsuarioDTO nuevoUsuario = new UsuarioDTO(nombre, clave, fechaNacimiento, correo, documento);
	        uDAO.add(nuevoUsuario);
	        limpiarCampos();
	        
	        String viewId = FacesContext.getCurrentInstance().getViewRoot().getViewId();
	        boolean esModoClaro = viewId.contains("Claro");
	        
	        return "login" + (esModoClaro ? "Claro" : "") + "?faces-redirect=true";
	    } catch(Exception e) {
	        return null;
	    }
	}

	public String iniciarSesion() {
	    UsuarioDTO usuarioEncontrado = uDAO.find(correo);
	    System.out.println(listaUsuarios.toString());
	    
	    if (usuarioEncontrado == null) {
	        FacesContext.getCurrentInstance().addMessage(null,
	                new FacesMessage(FacesMessage.SEVERITY_ERROR, "Correo y/o clave no validas", "Error"));
	        return null;
	    }

	    if (!usuarioEncontrado.getClave().equals(clave)) {
	        FacesContext.getCurrentInstance().addMessage(null,
	                new FacesMessage(FacesMessage.SEVERITY_ERROR, "Correo y/o clave no validas", "Contraseña incorrecta"));
	        return null;
	    }

	    FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("usuario", usuarioEncontrado);
	    
	    // Verificación del modo claro
	    String viewId = FacesContext.getCurrentInstance().getViewRoot().getViewId();
	    boolean esModoClaro = viewId.contains("Claro");
	    
	    return "pelicula" + (esModoClaro ? "Claro" : "") + "?faces-redirect=true";
	}
	public void mostrarTodo() {
		listaUsuarios = uDAO.findAll("http://localhost:8082/usuario/mostrartodo");
	}

	public void eliminarId() {
		uDAO.eliminar("http://localhost:8082/usuario/eliminarporid/" + id);
		limpiarCampos();
		mostrarTodo();
	}

	public void getFecha() {
		getFecha = LocalDate.now();
	}

	private void limpiarCampos() {
		nombre = "";
		clave = "";
		correo = "";
		fechaNacimiento = null;
		documento = 0;

	}

	public List<UsuarioDTO> getListaUsuarios() {
		return listaUsuarios;
	}

	
	public LocalDate getGetFecha() {
		return getFecha;
	}

	public void setGetFecha(LocalDate getFecha) {
		this.getFecha = getFecha;
	}

	public void setListaUsuarios(List<UsuarioDTO> listaUsuarios) {
		this.listaUsuarios = listaUsuarios;
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

}