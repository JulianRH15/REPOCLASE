package co.edu.unbosque.proyecto.service;

import java.util.ArrayList;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.edu.unbosque.proyecto.dto.UsuarioDTO;
import co.edu.unbosque.proyecto.model.Usuario;
import co.edu.unbosque.proyecto.repository.UsuarioRepository;

@Service
public class UsuarioService {
	@Autowired
	private UsuarioRepository usuarioRepo;
	@Autowired
	private ModelMapper modelMapper;

	public UsuarioService() {
	}

	public int create(UsuarioDTO data) {
		Usuario entity = modelMapper.map(data, Usuario.class);
		try {
			usuarioRepo.save(entity);
			return 0;
		} catch (Exception e) {
			return 1;
		}

	}

	public ArrayList<UsuarioDTO> findAll() {
		ArrayList<Usuario> entityList = (ArrayList<Usuario>) usuarioRepo.findAll();
		ArrayList<UsuarioDTO> dtoList = new ArrayList<>();
		entityList.forEach((entity) -> {
			UsuarioDTO dto = modelMapper.map(entity, UsuarioDTO.class);
			dtoList.add(dto);
		});
		return dtoList;
	}
	
	public int actualizarById(Integer id, UsuarioDTO data) {
		Optional<Usuario> found = usuarioRepo.findById(id);
		if (found.isPresent()) {
			Usuario entity = found.get();
			entity.setNombre(data.getNombre());
	        entity.setDocumento(data.getDocumento());
	        entity.setClave(data.getClave());
	        entity.setCorreo(data.getCorreo());
	        entity.setFechaNacimiento(data.getFechaNacimiento());
			usuarioRepo.save(entity);
			return 0;
		} else {
			return 1;
		}
	}
	
}
