package org.firjan.livraria.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.firjan.livraria.domain.Livro;
import org.firjan.livraria.dto.LivroRequestDTO;
import org.firjan.livraria.dto.LivroResponseDTO;
import org.firjan.livraria.repository.AutorRepository;
import org.firjan.livraria.repository.EditoraRepository;
import org.firjan.livraria.repository.LivroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpServerErrorException;

@RestController
@RequestMapping("/livro")
public class LivroController {

	@Autowired
	private AutorRepository autorRepository;
	
	@Autowired
	private LivroRepository livroRepository;
	
	@Autowired
	private EditoraRepository editoraRepository;
	
	@GetMapping
	public ResponseEntity<List<LivroResponseDTO>> get(@RequestParam Map<String,String> parametros) {
					
		List<Livro> todos = livroRepository.findAll();
		List<LivroResponseDTO> todosDto = new ArrayList<>();
		
		for (Livro livro : todos) {
			todosDto.add(LivroResponseDTO.fromLivro(livro));
		}
		
		return ResponseEntity.status(HttpStatus.OK).body(todosDto);
	}	
	
	@PostMapping
	public ResponseEntity<?> post(@Valid @RequestBody LivroRequestDTO dto) {

		try {
			Livro livro = dto.toLivro(autorRepository, editoraRepository);
		
			livroRepository.save(livro);
			
			return ResponseEntity.status(HttpStatus.CREATED).build();
			
		} catch (HttpServerErrorException e) {
			return ResponseEntity.status(e.getStatusCode()).body(e.getMessage());
		}
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Livro> delete(@PathVariable Integer id) {
		
		Livro existente = livroRepository.findById(id).orElse(null);
		
		if (existente == null) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}
		
		livroRepository.delete(existente);
		
		return ResponseEntity.status(HttpStatus.OK).body(existente);
	}
}
