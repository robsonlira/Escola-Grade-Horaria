package br.com.dominio.escola.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.dominio.escola.model.HorarioProfessor;


public interface HorariosProfessores extends JpaRepository<HorarioProfessor, Integer> {

}
