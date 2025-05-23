package co.edu.unbosque.model.persistence;

import java.util.List;

public interface CRUDOperation <E, D>{
	
    public void add(D nuevoDato);
	
	public void eliminar(String url);
	
	public void eliminar(int posicion);
	
	public void actualizar(String url, D nuevoDato);
	
	public List<E> findAll(String url);
	
	public D buscarUno(int posicion);


}
