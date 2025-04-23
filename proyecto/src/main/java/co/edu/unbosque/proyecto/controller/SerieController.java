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

import co.edu.unbosque.proyecto.dto.SerieDTO;
import co.edu.unbosque.proyecto.service.SerieService;

@RestController 
@RequestMapping("/serie")
@CrossOrigin(origins = { "*" })
public class SerieController {
	@Autowired
	private SerieService serieServ;
	
	public SerieController() {
		// TODO Auto-generated constructor stub
	}
	
	@PostMapping("/crearconjson")
	public ResponseEntity<String> crearConJson(@RequestBody SerieDTO nuevoSerie){
		int estado = serieServ.create(nuevoSerie);
		if(estado == 0) {
			return new ResponseEntity<String>("Serie creado",HttpStatus.CREATED);
		} else {
			return new ResponseEntity<String>("Error al crear serie", HttpStatus.NOT_ACCEPTABLE);
		}
	}
	
	@PostMapping("/crear")
	public ResponseEntity<String> crear (@RequestParam String nombre, @RequestParam String duracionCapitulo, @RequestParam int cantidadCapitulo, @RequestParam String tipo,@RequestParam String fechaEstreno,@RequestParam int edadRecomendada,@RequestParam String descripcion,@RequestParam String url) {

		SerieDTO nuevaSerie =  new SerieDTO(nombre, duracionCapitulo, cantidadCapitulo, tipo, fechaEstreno, descripcion, edadRecomendada, url);
		int estado = serieServ.create(nuevaSerie);
		if(estado == 0) {
			return new ResponseEntity<>("Serie creada con exito", HttpStatus.CREATED);
		}else {
			return new ResponseEntity<>("Error al crear la serie  ", HttpStatus.NOT_ACCEPTABLE); 
		}
	}
	
	@GetMapping("/mostrartodo") 
	public ResponseEntity<List<SerieDTO>> mostrarTodo() {
		List<SerieDTO > series = serieServ.findAll();
		if(series.isEmpty()) {
			return new ResponseEntity<List<SerieDTO>>(series, HttpStatus.NO_CONTENT); 
		}else {
			return new ResponseEntity<List<SerieDTO>>(series, HttpStatus.ACCEPTED);
		}
	}
	
	@DeleteMapping("/eliminarporid/{id}") 
	public ResponseEntity<String> deleteById(@PathVariable Integer id){
		int status = serieServ.deteleById(id);
		if(status == 0) {
			return new ResponseEntity<String>("Serie eliminada con exito", HttpStatus.ACCEPTED);
		}else {
			return new ResponseEntity<String>("Error al eliminar la serie", HttpStatus.NOT_FOUND);
		}
	}
	
	@PutMapping("/actualizar")
	public ResponseEntity<String> actualizar(@RequestParam Integer id,@RequestBody SerieDTO nuevaSerie){
		int estado = serieServ.actualizarById(id, nuevaSerie);
		if(estado == 0) {
			return new ResponseEntity<>("Viajero actualizado con exito", HttpStatus.CREATED);
		}else {
			return new ResponseEntity<>("Error al actualizar el viajero", HttpStatus.NOT_ACCEPTABLE); 
		}
	}

}
