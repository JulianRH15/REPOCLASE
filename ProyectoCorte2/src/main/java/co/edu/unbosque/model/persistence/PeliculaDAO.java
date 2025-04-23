package co.edu.unbosque.model.persistence;

import java.util.ArrayList;

import java.util.List;

import com.google.gson.Gson;

import co.edu.unbosque.model.PeliculaDTO;

public class PeliculaDAO {
    private final Gson gson = new Gson();
    
    public void add(PeliculaDTO pelicula) {
        String url = "http://localhost:8082/pelicula/crearconjson";
        String json = gson.toJson(pelicula);
        ExternalHttpRequestHandler.doPost(url, json);
    }
    
    public List<PeliculaDTO> findAll(String url) {
        return ExternalHttpRequestHandler.getAllPeliculas(url);
    }
    
    public void eliminar(String endpoint) {
        ExternalHttpRequestHandler.doDelete(endpoint);
    }
    
    public void actualizar(String endpoint, PeliculaDTO pelicula) {
        String json = gson.toJson(pelicula);
        ExternalHttpRequestHandler.doPut(endpoint, json);
    }
}