package br.com.dominio.escola.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.dominio.escola.model.Departamento;


public interface Departamentos extends JpaRepository<Departamento, Integer> {

}
