package org.firjan.livraria.controller;

import java.util.List;

import org.firjan.livraria.domain.Editora;
import org.firjan.livraria.repository.EditoraRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/editora")
public class EditoraController {

	@Autowired
	private EditoraRepository repository;
	
	@GetMapping
	public ResponseEntity<List<Editora>> get() {
	
		List<Editora> todos = repository.findAll();
		
		return ResponseEntity.ok().body(todos);
	}
	
	@PostMapping
	public ResponseEntity<Editora> post(@RequestBody Editora novo) {
		
		Editora existente = repository.findByCodigo(novo.getCodigo());
		
		if (existente != null) {
			return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(existente);
		}
		
		repository.save(novo);
		
		return ResponseEntity.status(HttpStatus.CREATED).body(novo);
	}
	
	@PutMapping("/{codigo}")
	public ResponseEntity<Editora> put(@RequestBody Editora modificado, @PathVariable String codigo) {
		
		Editora existente = repository.findByCodigo(codigo);
		
		if (existente == null) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}
		
		existente.setNome(modificado.getNome());
		
		repository.save(existente);
		
		return ResponseEntity.status(HttpStatus.OK).body(existente);
	}
	
}
