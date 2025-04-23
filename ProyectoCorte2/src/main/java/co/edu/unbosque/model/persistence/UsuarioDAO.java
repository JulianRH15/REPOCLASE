package co.edu.unbosque.model.persistence;

import java.util.ArrayList;

import co.edu.unbosque.model.UsuarioDTO;

public class UsuarioDAO implements CRUDOperation<UsuarioDTO, UsuarioDTO>{
	static ArrayList<UsuarioDTO> listaUsuarios = new ArrayList<UsuarioDTO>();

	public UsuarioDAO() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void add(UsuarioDTO nuevoDato) {
	listaUsuarios.add(nuevoDato);
	ExternalHttpRequestHandler.doPost("http://localhost:8082/usuario/crearconjson", nuevoDato.toString());
	}

	@Override
	public void eliminar(String url) {
		listaUsuarios.remove(ExternalHttpRequestHandler.doDelete(url));
		
	}

	@Override
	public void eliminar(int posicion) {
		listaUsuarios.remove(posicion);
		
	}

	@Override
	public void actualizar(String url, UsuarioDTO nuevoDato) {
		//listaViajeros.add(ExternalHttpRequestHandler.doPut(url,nuevoDato.toString()));
		
	}

	@Override
	public ArrayList<UsuarioDTO> findAll(String url){
		return listaUsuarios = ExternalHttpRequestHandler.getAllUsuarios(url);
	}

	@Override
	public UsuarioDTO buscarUno(int posicion) {
		return listaUsuarios.get(posicion);
	}	

	public UsuarioDTO find(String toFind) {
        UsuarioDTO found = null;
        if (!listaUsuarios.isEmpty()) {
            for (UsuarioDTO usuarios : listaUsuarios) {
                if (usuarios.getCorreo().equals(toFind)) {
                    found = usuarios;
                    return found;
                } else {
                    continue;
                }
            }
        } else {
            return null;
        }
        return null;
    }
}