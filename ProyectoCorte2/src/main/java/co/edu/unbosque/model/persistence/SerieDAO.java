package co.edu.unbosque.model.persistence;

import java.util.ArrayList;
import java.util.List;

import co.edu.unbosque.model.SerieDTO;

public class SerieDAO implements CRUDOperation<SerieDTO, SerieDTO>{
	List<SerieDTO> listaSeries = new ArrayList<SerieDTO>();

	public SerieDAO() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void add(SerieDTO nuevoDato) {
	listaSeries.add(nuevoDato);
	ExternalHttpRequestHandler.doPost("http://localhost:8082/serie/crearconjson", nuevoDato.toString());
	}

	@Override
	public void eliminar(String url) {
		listaSeries.remove(ExternalHttpRequestHandler.doDelete(url));
		
	}

	@Override
	public void eliminar(int posicion) {
		listaSeries.remove(posicion);
		
	}

	@Override
	public void actualizar(String url, SerieDTO nuevoDato) {
		//listaViajeros.add(ExternalHttpRequestHandler.doPut(url,nuevoDato.toString()));
		
	}

	@Override
	public List<SerieDTO> findAll(String url){
		return listaSeries = ExternalHttpRequestHandler.getAllSeries(url);
	}

	@Override
	public SerieDTO buscarUno(int posicion) {
		return listaSeries.get(posicion);
	}	
}
