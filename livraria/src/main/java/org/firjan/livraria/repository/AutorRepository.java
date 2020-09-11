package org.firjan.livraria.repository;

import java.util.List;

import org.firjan.livraria.domain.Autor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface AutorRepository extends JpaRepository<Autor, String> {

	Autor findByCodigo(String codigo);	
		
	@Query("SELECT a FROM Autor a WHERE a.codigo LIKE CONCAT(:uf, '%') ")
	List<Autor> findAllByUf(@Param("uf") String uf);
}
