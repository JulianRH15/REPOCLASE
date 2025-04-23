package co.edu.unbosque.proyecto.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import co.edu.unbosque.proyecto.dto.UsuarioDTO;
import co.edu.unbosque.proyecto.model.ExcelExporter;
import co.edu.unbosque.proyecto.service.UsuarioService;

@RestController
@RequestMapping("/usuario")
@CrossOrigin(origins = { "*" })
public class UsuarioController {

	@Autowired
	private UsuarioService usuarioServ;
	
	@Autowired
	private ExcelExporter excelExporter;

	public UsuarioController() {
	}

	@PostMapping("/crearconjson")
	public ResponseEntity<String> crearConJson(@RequestBody UsuarioDTO nuevoUsuario) {
		int estado = usuarioServ.create(nuevoUsuario);
		if (estado == 0) {
			return new ResponseEntity<String>("Viajero creado", HttpStatus.CREATED);
		} else {
			return new ResponseEntity<String>("Error al crear viajero", HttpStatus.NOT_ACCEPTABLE);
		}
	}

	@PostMapping("/crear")
	public ResponseEntity<String> crear(@RequestParam String nombre, @RequestParam String clave,
			@RequestParam String fechaNacimiento, @RequestParam String correo, @RequestParam int documento) {

		UsuarioDTO nuevoUsuario = new UsuarioDTO(nombre, clave, fechaNacimiento, correo, documento);
		int estado = usuarioServ.create(nuevoUsuario);
		if (estado == 0) {
			return new ResponseEntity<>("Usuario creado con exito", HttpStatus.CREATED);
		} else {
			return new ResponseEntity<>("Error al crear el usuario ", HttpStatus.NOT_ACCEPTABLE);
		}
	}

	@GetMapping("/mostrartodo")
	public ResponseEntity<ArrayList<UsuarioDTO>> mostrarTodo() {
		ArrayList<UsuarioDTO> usuarios = usuarioServ.findAll();
		if (usuarios.isEmpty()) {
			return new ResponseEntity<ArrayList<UsuarioDTO>>(usuarios, HttpStatus.NO_CONTENT);
		} else {
			return new ResponseEntity<ArrayList<UsuarioDTO>>(usuarios, HttpStatus.ACCEPTED);
		}
	}

	@PutMapping("/actualizar")
	public ResponseEntity<String> actualizar(@RequestParam Integer id, @RequestBody UsuarioDTO nuevoUsuario) {
		int estado = usuarioServ.actualizarById(id, nuevoUsuario);
		if (estado == 0) {
			return new ResponseEntity<>("Viajero actualizado con exito", HttpStatus.CREATED);
		} else {
			return new ResponseEntity<>("Error al actualizar el viajero", HttpStatus.NOT_ACCEPTABLE);
		}
	}
	
	@GetMapping("/download-excel")
    public ResponseEntity<byte[]> downloadExcel() {
        List<UsuarioDTO> usuarios = usuarioServ.findAll();

        if (usuarios == null || usuarios.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        byte[] excel = excelExporter.generateExcel(usuarios);

        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Disposition", "attachment; filename=usuarios.xlsx");
        headers.add("Content-Type", "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");

        return new ResponseEntity<>(excel, headers, HttpStatus.OK);
    }
    
}
