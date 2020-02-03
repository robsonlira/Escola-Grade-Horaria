package br.com.dominio.escola.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.dominio.escola.model.Professor;


public interface Professores extends JpaRepository<Professor, Integer> {

}
