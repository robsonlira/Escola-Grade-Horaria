package br.com.dominio.escola.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.dominio.escola.model.Curso;


public interface Cursos extends JpaRepository<Curso, Integer> {

}
