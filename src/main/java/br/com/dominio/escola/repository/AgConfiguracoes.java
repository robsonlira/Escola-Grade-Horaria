package br.com.dominio.escola.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.dominio.escola.model.AgConfiguracao;


public interface AgConfiguracoes extends JpaRepository<AgConfiguracao, Integer> {

}
