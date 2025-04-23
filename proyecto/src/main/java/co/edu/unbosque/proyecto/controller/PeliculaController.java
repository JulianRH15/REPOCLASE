package co.edu.unbosque.proyecto.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import co.edu.unbosque.proyecto.dto.PeliculaDTO;
import co.edu.unbosque.proyecto.service.PeliculaService;

@RestController 
@RequestMapping("/pelicula")
@CrossOrigin(origins = { "*" })
public class PeliculaController {
	
	@Autowired
	private PeliculaService peliculaServ;
	
	public PeliculaController() {
	}
	
	@PostMapping("/crearconjson")
	public ResponseEntity<String> crearConJson(@RequestBody PeliculaDTO nuevoPelicula){
		int estado = peliculaServ.create(nuevoPelicula);
		if(estado == 0) {
			return new ResponseEntity<String>("Pelicula creado",HttpStatus.CREATED);
		} else {
			return new ResponseEntity<String>("Error al crear pelicula", HttpStatus.NOT_ACCEPTABLE);
		}
	}
	
	@PostMapping("/crear")
	public ResponseEntity<String> crear (@RequestParam String nombre, @RequestParam String duracion, @RequestParam String tipo, @RequestParam String fechaEstreno,@RequestParam String descripcion,@RequestParam int edadRecomendada,@RequestParam String url) {
		
		PeliculaDTO nuevoPelicula = new PeliculaDTO(nombre, duracion, tipo, fechaEstreno, descripcion, edadRecomendada, url);
		int estado = peliculaServ.create(nuevoPelicula);
		if(estado == 0) {
			return new ResponseEntity<>("Pelicula creada con exito", HttpStatus.CREATED);
		}else {
			return new ResponseEntity<>("Error al crear la pelicula", HttpStatus.NOT_ACCEPTABLE); 
		}
	}
	@GetMapping("/mostrartodo") 
	public ResponseEntity<List<PeliculaDTO>> mostrarTodo() {
		List<PeliculaDTO > peliculas = peliculaServ.findAll();
		if(peliculas.isEmpty()) {
			return new ResponseEntity<List<PeliculaDTO>>(peliculas, HttpStatus.NO_CONTENT); 
		}else {
			return new ResponseEntity<List<PeliculaDTO>>(peliculas, HttpStatus.ACCEPTED);
		}
	}
	@DeleteMapping("/eliminarporid/{id}") 
	public ResponseEntity<String> deleteById(@PathVariable Integer id){
		int status = peliculaServ.deteleById(id);
		if(status == 0) {
			return new ResponseEntity<String>("Pelicula eliminada con exito", HttpStatus.ACCEPTED);
		}else {
			return new ResponseEntity<String>("Error al eliminar la pelicula", HttpStatus.NOT_FOUND);
		}
	}
	@PutMapping("/actualizar")
	public ResponseEntity<String> actualizar(@RequestParam Integer id,@RequestBody PeliculaDTO nuevoPelicula){
		int estado = peliculaServ.actualizarById(id, nuevoPelicula);
		if(estado == 0) {
			return new ResponseEntity<>("Viajero actualizado con exito", HttpStatus.CREATED);
		}else {
			return new ResponseEntity<>("Error al actualizar el viajero", HttpStatus.NOT_ACCEPTABLE); 
		}
	}
	

}
