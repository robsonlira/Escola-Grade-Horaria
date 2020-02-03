package br.com.dominio.escola.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.dominio.escola.model.GradeHoraria;


public interface GradesHorarias extends JpaRepository<GradeHoraria, Integer> {

}
