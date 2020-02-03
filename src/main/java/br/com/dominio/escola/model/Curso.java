package br.com.dominio.escola.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotBlank;

@Entity
public class Curso implements Serializable {

	private static final long serialVersionUID = 1L;	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@NotBlank
	@Column(name="nome", nullable = false, unique = true, length = 40)	
	private String nome;
	
	@Column(name="numero_semestres")	
	private Integer numeroSemestres;
		
	@OneToMany(mappedBy="curso")
	private List<Disciplina> disciplinas = new ArrayList<>();

	public Curso() {
	}
	
	public Curso(Integer id, String nome, Integer numeroSemestres) {
		this.id = id;
		this.nome = nome;
		this.numeroSemestres = numeroSemestres;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Integer getNumeroSemestres() {
		return numeroSemestres;
	}

	public void setNumeroSemestres(Integer numeroSemestres) {
		this.numeroSemestres = numeroSemestres;
	}

	
	
}
