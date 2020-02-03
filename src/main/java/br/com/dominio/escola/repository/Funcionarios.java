package br.com.dominio.escola.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.dominio.escola.model.Funcionario;


public interface Funcionarios extends JpaRepository<Funcionario, Integer> {

}
