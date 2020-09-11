package org.firjan.livraria.dto;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;

import org.firjan.livraria.domain.Autor;
import org.firjan.livraria.domain.Livro;
import org.firjan.livraria.domain.TipoDoLivro;

public class LivroResponseDTO {
	
	@NotNull
	@Size(min=5, max=30)
	private String titulo;
	
	@NotNull
	private TipoDoLivro tipo;
	
	@NotNull
	private Set<String> autores;
	
	@NotNull
	private String editora;
	
	@NotNull
	@Past
	private LocalDate dataPublicacao;
	
	public static LivroResponseDTO fromLivro(Livro livro) {
		LivroResponseDTO dto = new LivroResponseDTO();
		dto.setTitulo(livro.getTitulo());
		dto.setEditora(livro.getEditora().getNome());
		dto.setTipo(livro.getTipo());
		dto.setDataPublicacao(livro.getDataPublicacao());
		
		dto.autores = new HashSet<>();
		
		for (Autor a : livro.getAutores()) {
			dto.getAutores().add(a.getNome());
		}
		
		return dto;
	}
	
	public String getEditora() {
		return editora;
	}

	public void setEditora(String editora) {
		this.editora = editora;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public TipoDoLivro getTipo() {
		return tipo;
	}

	public void setTipo(TipoDoLivro tipo) {
		this.tipo = tipo;
	}

	public Set<String> getAutores() {
		return autores;
	}

	public void setAutor(Set<String> autores) {
		this.autores = autores;
	}

	public LocalDate getDataPublicacao() {
		return dataPublicacao;
	}

	public void setDataPublicacao(LocalDate dataPublicacao) {
		this.dataPublicacao = dataPublicacao;
	}
}
