package co.edu.unbosque.proyecto.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.edu.unbosque.proyecto.dto.PeliculaDTO;
import co.edu.unbosque.proyecto.model.Pelicula;
import co.edu.unbosque.proyecto.repository.PeliculaRepository;

@Service
public class PeliculaService {
	@Autowired
	private PeliculaRepository peliculaRepo;
	@Autowired
	private ModelMapper modelMapper;

	public int create(PeliculaDTO data) {
		Pelicula entity = modelMapper.map(data, Pelicula.class);
		try {
			peliculaRepo.save(entity);
			return 0;
		} catch (Exception e) {
			return 1;
		}
	}

	public List<PeliculaDTO> findAll() {
		List<Pelicula> entityList = (List<Pelicula>) peliculaRepo.findAll();
		List<PeliculaDTO> dtoList = new ArrayList<>();
		entityList.forEach((entity) -> {
			PeliculaDTO dto = modelMapper.map(entity, PeliculaDTO.class);
			dtoList.add(dto);
		});
		return dtoList;
	}

	public int deteleById(Integer id) {
		Optional<Pelicula> found = peliculaRepo.findById(id);
		if (found.isPresent()) {
			peliculaRepo.delete(found.get());
			return 0;
		} else {
			return 1;
		}
	}
	public int actualizarById(Integer id, PeliculaDTO data) {
		Optional<Pelicula> found = peliculaRepo.findById(id);
		if (found.isPresent()) {
			Pelicula entity = found.get();
			entity.setNombre(data.getNombre());
	        entity.setDuracion(data.getDuracion());
	        entity.setTipo(data.getTipo());
	        entity.setFechaEstreno(data.getFechaEstreno());
	        entity.setDescripcion(data.getDescripcion());
	        entity.setEdadRecomendada(data.getEdadRecomendada());
	        entity.setUrl(data.getUrl());
			peliculaRepo.save(entity);
			return 0;
		} else {
			return 1;
		}
	}
}
