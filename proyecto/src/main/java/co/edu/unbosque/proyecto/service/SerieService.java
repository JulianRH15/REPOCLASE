package co.edu.unbosque.proyecto.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.edu.unbosque.proyecto.dto.SerieDTO;
import co.edu.unbosque.proyecto.model.Serie;
import co.edu.unbosque.proyecto.repository.SerieRepository;

@Service
public class SerieService {
	@Autowired
	private SerieRepository serieRepo;
	@Autowired
	private ModelMapper modelMapper;

	public SerieService() {
		// TODO Auto-generated constructor stub
	}
	
	public int create(SerieDTO data) {
		Serie entity = modelMapper.map(data, Serie.class);
		try {
			serieRepo.save(entity);
			return 0;
		} catch (Exception e) {
			return 1;
		}
	}

	public List<SerieDTO> findAll() {
		ArrayList<Serie> entityList = (ArrayList<Serie>) serieRepo.findAll();
		ArrayList<SerieDTO> dtoList = new ArrayList<>();
		entityList.forEach((entity) -> {
			SerieDTO dto = modelMapper.map(entity, SerieDTO.class);
			dtoList.add(dto);
		});
		return dtoList;
	}

	public int deteleById(Integer id) {
		Optional<Serie> found = serieRepo.findById(id);
		if (found.isPresent()) {
			serieRepo.delete(found.get());
			return 0;
		} else {
			return 1;
		}
	}
	
	public int actualizarById(Integer id, SerieDTO data) {
		Optional<Serie> found = serieRepo.findById(id);
		if (found.isPresent()) {
			Serie entity = found.get();
			entity.setNombre(data.getNombre());
	        entity.setDuracionCapitulo(data.getDuracionCapitulo());
	        entity.setCantidadCapitulo(data.getCantidadCapitulo());
	        entity.setTipo(data.getTipo());
	        entity.setFechaEstreno(data.getFechaEstreno());
	        entity.setDescripcion(data.getDescripcion());
	        entity.setEdadRecomendada(data.getEdadRecomendada());
	        entity.setUrl(data.getUrl());
			serieRepo.save(entity);
			return 0;
		} else {
			return 1;
		}
	}

}
