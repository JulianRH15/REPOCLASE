package co.edu.unbosque.bean;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Date;
import java.util.ArrayList;
import java.util.List;

import co.edu.unbosque.model.PeliculaDTO;
import co.edu.unbosque.model.persistence.PeliculaDAO;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Named;

@Named("PeliculaBean")
@ViewScoped
public class PeliculaBean implements Serializable {

	private static final long serialVersionUID = 1L;
	private List<PeliculaDTO> listaPeliculas;
	private Integer id;
	private String nombre;
	private String duracion;
	private String tipo;
	private String fechaEstreno;
	private String descripcion;
	private int edadRecomendada;
	private String url;
	private LocalDate getFecha;
	private PeliculaDAO pDAO ;
	private int index = -1;
	private PeliculaDTO peliculaSeleccionada;


	public PeliculaDTO getPeliculaSeleccionada() {
	    return peliculaSeleccionada;
	}

	public void setPeliculaSeleccionada(PeliculaDTO peliculaSeleccionada) {
	    this.peliculaSeleccionada = peliculaSeleccionada;
	}


	public PeliculaBean() {
        listaPeliculas = new ArrayList<>();
        pDAO = new PeliculaDAO();
        mostrarTodo();
    }

    public void guardar() {
        if (id == null) {
            PeliculaDTO nuevaPelicula = new PeliculaDTO(nombre, duracion, tipo, fechaEstreno, 
                                                      descripcion, edadRecomendada, url);
            pDAO.add(nuevaPelicula);
            FacesContext.getCurrentInstance().addMessage(null,
                new FacesMessage(FacesMessage.SEVERITY_INFO, "Éxito", "Película creada correctamente"));
        } else {
            actualizar();
        }
        
        limpiarCampos();
        mostrarTodo();
    }
    public void eliminarPelicula(PeliculaDTO pelicula) {
        try {
            String endpoint = "http://localhost:8082/pelicula/eliminarporid/" + pelicula.getId();
            pDAO.eliminar(endpoint);
            
            FacesContext.getCurrentInstance().addMessage(null,
                new FacesMessage(FacesMessage.SEVERITY_INFO, "Éxito", 
                               "Película eliminada correctamente"));
            
            mostrarTodo();
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null,
                new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", 
                               "No se pudo eliminar la película: " + e.getMessage()));
        }
    }

    public void actualizar() {
        try {
            if (peliculaSeleccionada != null) {
                peliculaSeleccionada.setNombre(nombre);
                peliculaSeleccionada.setDuracion(duracion);
                peliculaSeleccionada.setTipo(tipo);
                peliculaSeleccionada.setFechaEstreno(fechaEstreno);
                peliculaSeleccionada.setDescripcion(descripcion);
                peliculaSeleccionada.setEdadRecomendada(edadRecomendada);
                peliculaSeleccionada.setUrl(url);
                
                String endpoint = "http://localhost:8082/pelicula/actualizar";
                pDAO.actualizar(endpoint, peliculaSeleccionada);
                
                FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_INFO, "Éxito", 
                                   "Película actualizada correctamente"));
                
                prepararNuevoRegistro();
                mostrarTodo();
            }
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null,
                new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", 
                               "No se pudo actualizar la película: " + e.getMessage()));
        }
    }

    public void cargarPelicula(PeliculaDTO pelicula) {
        this.peliculaSeleccionada = pelicula;
        this.id = pelicula.getId();
        this.nombre = pelicula.getNombre();
        this.url = pelicula.getUrl();
        this.duracion = pelicula.getDuracion();
        this.tipo = pelicula.getTipo();
        this.fechaEstreno = pelicula.getFechaEstreno();
        this.edadRecomendada = pelicula.getEdadRecomendada();
        this.descripcion = pelicula.getDescripcion();
    }

    public void mostrarTodo() {
        listaPeliculas = pDAO.findAll("http://localhost:8082/pelicula/mostrartodo");
    }

 

    public void prepararNuevoRegistro() {
        limpiarCampos();
        this.peliculaSeleccionada = null;
        this.id = null;
    }

    private void limpiarCampos() {
        nombre = "";
        duracion = "";
        tipo = "";
        fechaEstreno = null;
        descripcion = "";
        edadRecomendada = 0;
        url = "";
    }

	public LocalDate getGetFecha() {
		return getFecha;
	}

	public void setGetFecha(LocalDate getFecha) {
		this.getFecha = getFecha;
	}

	public List<List<PeliculaDTO>> getGruposDePeliculas(int tamañoGrupo) {
	    List<List<PeliculaDTO>> grupos = new ArrayList<>();
	    
	    if (listaPeliculas == null || listaPeliculas.isEmpty()) {
	        return grupos;
	    }
	    
	    for (int i = 0; i < listaPeliculas.size(); i += tamañoGrupo) {
	        int fin = Math.min(i + tamañoGrupo, listaPeliculas.size());
	        List<PeliculaDTO> grupo = listaPeliculas.subList(i, fin);
	        grupos.add(grupo);
	    }
	    
	    return grupos;
	}
	public List<PeliculaDTO> getListaPeliculas() {
		return listaPeliculas;
	}

	public void setListaPeliculas(List<PeliculaDTO> listaPeliculas) {
		this.listaPeliculas = listaPeliculas;
	}
	public boolean esUrlYoutube(String url) {
	    if (url == null || url.isEmpty()) {
	        return false;
	    }
	    return url.contains("youtube.com") || url.contains("youtu.be");
	}

	public String getEmbedUrlYoutube(String url) {
	    if (!esUrlYoutube(url)) {
	        return url; 
	    }
	    
	    try {
	        String videoId = "";
	        if (url.contains("v=")) {
	            videoId = url.substring(url.indexOf("v=") + 2);
	            if (videoId.contains("&")) {
	                videoId = videoId.substring(0, videoId.indexOf('&'));
	            }
	        } else if (url.contains("youtu.be/")) {
	            videoId = url.substring(url.indexOf("youtu.be/") + 9);
	        }
	        
	        return "https://www.youtube.com/embed/" + videoId + "?autoplay=0&controls=1&showinfo=0&rel=0";
	    } catch (Exception e) {
	        return url; 
	    }
	}
	
	public void eliminarPeliculaSeleccionada() {
	    try {
	        if (peliculaSeleccionada != null) {
	            pDAO.eliminar("http://localhost:8082/pelicula/eliminarporid/" + peliculaSeleccionada.getId());
	            listaPeliculas.remove(peliculaSeleccionada);
	            FacesContext.getCurrentInstance().addMessage(null, 
	                new FacesMessage(FacesMessage.SEVERITY_INFO, "Éxito", "Película eliminada correctamente"));
	            limpiarCampos();
	        }
	    } catch (Exception e) {
	        FacesContext.getCurrentInstance().addMessage(null, 
	            new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "No se pudo eliminar la película"));
	        e.printStackTrace();
	    }
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

	public PeliculaDAO getpDAO() {
		return pDAO;
	}

	public void setpDAO(PeliculaDAO pDAO) {
		this.pDAO = pDAO;
	}

	public int getIndex() {
		return index;
	}

	public void setIndex(int index) {
		this.index = index;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	
	
}