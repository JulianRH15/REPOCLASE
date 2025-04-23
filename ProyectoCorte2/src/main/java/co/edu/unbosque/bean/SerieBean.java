package co.edu.unbosque.bean;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import co.edu.unbosque.model.SerieDTO;
import co.edu.unbosque.model.persistence.SerieDAO;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Named;

@Named("SerieBean")
@ViewScoped
public class SerieBean implements Serializable {

    private static final long serialVersionUID = 1L;
    private List<SerieDTO> listaSeries;
    private Integer id;
    private String nombre;
    private String duracionCapitulo;
    private int cantidadCapitulo;
    private String tipo;
    private String fechaEstreno;
    private String descripcion;
    private int edadRecomendada;
    private String url;
    private SerieDAO sDAO;
    private int index = -1;
    private SerieDTO serieSeleccionada;

    public SerieBean() {
        listaSeries = new ArrayList<>();
        sDAO = new SerieDAO();
        mostrarTodo();
    }

    public void guardar() {
        try {
            SerieDTO nuevaSerie = new SerieDTO(nombre, duracionCapitulo, cantidadCapitulo, tipo, 
                                             fechaEstreno, descripcion, edadRecomendada, url);
            sDAO.add(nuevaSerie);
            
            FacesContext.getCurrentInstance().addMessage(null,
                new FacesMessage(FacesMessage.SEVERITY_INFO, "Ingrese datos", "Serie guardada correctamente"));
            
            prepararNuevoRegistro();
            mostrarTodo();
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null,
                new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "No se pudo guardar la serie"));
        }
    }

    public void mostrarTodo() {
        listaSeries = sDAO.findAll("http://localhost:8082/serie/mostrartodo");
    }

    public void eliminarSerie(SerieDTO serie) {
        try {
            sDAO.eliminar("http://localhost:8082/serie/eliminarporid/" + serie.getId());
            FacesContext.getCurrentInstance().addMessage(null,
                new FacesMessage(FacesMessage.SEVERITY_INFO, "Éxito", "Serie eliminada correctamente"));
            mostrarTodo(); 
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null,
                new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "No se pudo eliminar la serie"));
        }
    }

    public void actualizar() {
        try {
            serieSeleccionada.setNombre(nombre);
            serieSeleccionada.setUrl(url);
            serieSeleccionada.setDescripcion(descripcion);
            serieSeleccionada.setDuracionCapitulo(duracionCapitulo);
            serieSeleccionada.setCantidadCapitulo(cantidadCapitulo);
            serieSeleccionada.setEdadRecomendada(edadRecomendada);
            serieSeleccionada.setFechaEstreno(fechaEstreno);
            serieSeleccionada.setTipo(tipo);
            
            sDAO.actualizar("http://localhost:8082/serie/actualizar", serieSeleccionada);
            
            FacesContext.getCurrentInstance().addMessage(null,
                new FacesMessage(FacesMessage.SEVERITY_INFO, "Éxito", "Serie actualizada correctamente"));
            prepararNuevoRegistro();
            mostrarTodo();
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null,
                new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "No se pudo actualizar la serie"));
        }
    }

    public void cargarSerie(SerieDTO serie) {
        this.serieSeleccionada = serie;
        this.nombre = serie.getNombre();
        this.url = serie.getUrl();
        this.duracionCapitulo = serie.getDuracionCapitulo();
        this.cantidadCapitulo = serie.getCantidadCapitulo();
        this.tipo = serie.getTipo();
        this.fechaEstreno = serie.getFechaEstreno();
        this.edadRecomendada = serie.getEdadRecomendada();
        this.descripcion = serie.getDescripcion();
        this.index = serie.getId();
    }

    public void prepararNuevoRegistro() {
        limpiarCampos();
        this.index = -1;
        this.serieSeleccionada = null;
    }

    private void limpiarCampos() {
        nombre = "";
        duracionCapitulo = "";
        cantidadCapitulo = 0;
        tipo = "";
        fechaEstreno = null;
        descripcion = "";
        edadRecomendada = 0;
        url = "";
    }
    public Date getFechaEstrenoAsDate() {
        if (fechaEstreno == null || fechaEstreno.isEmpty()) {
            return null;
        }
        try {
            return new SimpleDateFormat("yyyy-MM-dd").parse(fechaEstreno);
        } catch (ParseException e) {
            return null;
        }
    }

    public void setFechaEstrenoAsDate(Date date) {
        if (date != null) {
            this.fechaEstreno = new SimpleDateFormat("yyyy-MM-dd").format(date);
        } else {
            this.fechaEstreno = null;
        }
    }
    
    public List<List<SerieDTO>> getGruposDeSeries(int tamañoGrupo) {
        List<List<SerieDTO>> grupos = new ArrayList<>();
        for (int i = 0; i < listaSeries.size(); i += tamañoGrupo) {
            int fin = Math.min(i + tamañoGrupo, listaSeries.size());
            grupos.add(listaSeries.subList(i, fin));
        }
        return grupos;
    }
    
    public boolean esUrlYoutube(String url) {
        return url != null && (url.contains("youtube.com") || url.contains("youtu.be"));
    }
    
    public String getEmbedUrlYoutube(String url) {
        if (url == null) return "";
        
        if (url.contains("embed")) return url;
        
        String videoId = "";
        if (url.contains("v=")) {
            videoId = url.substring(url.indexOf("v=") + 2);
            if (videoId.contains("&")) {
                videoId = videoId.substring(videoId.indexOf('.'), videoId.indexOf("&"));
            }
        } else if (url.contains("youtu.be/")) {
            videoId = url.substring(url.indexOf("youtu.be/") + 9);
        }
        
        return "https://www.youtube.com/embed/" + videoId + "?autoplay=0&controls=0&showinfo=0&rel=0";
    }

 
    public SerieDTO getSerieSeleccionada() {
        return serieSeleccionada;
    }

    public void setSerieSeleccionada(SerieDTO serieSeleccionada) {
        this.serieSeleccionada = serieSeleccionada;
    }

   

    public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public void setFechaEstreno(String fechaEstreno) {
		this.fechaEstreno = fechaEstreno;
	}

	public List<SerieDTO> getListaSeries() {
        return listaSeries;
    }

    public void setListaSeries(List<SerieDTO> listaSeries) {
        this.listaSeries = listaSeries;
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

    public String getDuracionCapitulo() {
        return duracionCapitulo;
    }

    public void setDuracionCapitulo(String duracionCapitulo) {
        this.duracionCapitulo = duracionCapitulo;
    }

    public int getCantidadCapitulo() {
        return cantidadCapitulo;
    }

    public void setCantidadCapitulo(int cantidadCapitulo) {
        this.cantidadCapitulo = cantidadCapitulo;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
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

    public SerieDAO getsDAO() {
        return sDAO;
    }

    public void setsDAO(SerieDAO sDAO) {
        this.sDAO = sDAO;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }
}