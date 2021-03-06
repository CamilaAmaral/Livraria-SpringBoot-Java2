package org.firjan.livraria.controller;

import java.util.List;

import org.firjan.livraria.domain.Autor;
import org.firjan.livraria.repository.AutorRepository;
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
@RequestMapping("/autor")
public class AutorController {

	@Autowired
	private AutorRepository repository;
	
	@GetMapping
	public ResponseEntity<List<Autor>> get() {
	
		List<Autor> todos = repository.findAll();	
		
		return ResponseEntity.status(HttpStatus.OK).body(todos);
	}
	
	@GetMapping("/uf/{uf}")
	public ResponseEntity<List<Autor>> get(@PathVariable String uf) {
		
		List<Autor> todos = repository.findAllByUf(uf);
		
		return ResponseEntity.status(HttpStatus.OK).body(todos);
	}
	
	@PostMapping
	public ResponseEntity<Autor> post(@RequestBody Autor novo) {
		
		repository.save(novo);
		
		return ResponseEntity.status(HttpStatus.CREATED).body(novo);
	}
	
	@PutMapping("/{codigo}")
	public ResponseEntity<Autor> put(@RequestBody Autor modificado, @PathVariable String codigo) {
		
		Autor existente = repository.findByCodigo(codigo);
		existente.setNome(modificado.getNome());
		
		repository.save(existente);
		
		return ResponseEntity.status(HttpStatus.OK).body(existente);
	}
	
}
