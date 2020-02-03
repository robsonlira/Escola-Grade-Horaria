package br.com.dominio.escola.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.com.dominio.escola.model.Horario;


public interface Horarios extends JpaRepository<Horario, Integer> {
	
	public Optional<Horario> findByNomeIgnoreCase(String nome);
	
	@Query("select a from Horario a where a.turno.id=?1")
	public List<Horario> findHorarios(Integer turnoId);
	
	

}
