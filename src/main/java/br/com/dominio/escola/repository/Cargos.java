package br.com.dominio.escola.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.dominio.escola.model.Cargo;


public interface Cargos extends JpaRepository<Cargo, Integer> {

}
