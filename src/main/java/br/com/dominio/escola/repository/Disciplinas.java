package br.com.dominio.escola.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.dominio.escola.model.Disciplina;


public interface Disciplinas extends JpaRepository<Disciplina, Integer> {

}
