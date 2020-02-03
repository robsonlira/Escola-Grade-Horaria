package br.com.dominio.escola.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.dominio.escola.model.Turno;


public interface Turnos extends JpaRepository<Turno, Integer> {

}
