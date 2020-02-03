package br.com.dominio.escola.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Entity
public class Disciplina implements Serializable {

	private static final long serialVersionUID = 1L;	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@NotBlank
	@Column(name="nome", nullable = false, unique = true, length = 50)	
	private String nome;
	
	@NotBlank
	@Column(name="sigla", length = 5)	
	private String sigla;
	
	@Column(name="numero_semestres")	
	private Integer numeroSemestres;
		
	@Column(name="numero_creditos")	
	private Integer numeroCreditos;		
	
	@NotNull
	@ManyToOne
	@JoinColumn(name="curso_id")
	private Curso curso;
	
	@ManyToMany(mappedBy = "disciplinas")
	private List<Professor> professores = new ArrayList<>();
		
	public Disciplina() {}
	
	public Disciplina(Integer id, String nome, String sigla, Curso curso) {
		this.id = id;
		this.nome = nome;
		this.sigla = sigla;
		this.curso = curso;
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

	public String getSigla() {
		return sigla;
	}

	public void setSigla(String sigla) {
		this.sigla = sigla;
	}

	public Integer getNumeroSemestres() {
		return numeroSemestres;
	}

	public void setNumeroSemestres(Integer numeroSemestres) {
		this.numeroSemestres = numeroSemestres;
	}

	public Integer getNumeroCreditos() {
		return numeroCreditos;
	}

	public void setNumeroCreditos(Integer numeroCreditos) {
		this.numeroCreditos = numeroCreditos;
	}

	public Curso getCurso() {
		return curso;
	}

	public void setCurso(Curso curso) {
		this.curso = curso;
	}

	public List<Professor> getProfessores() {
		return professores;
	}

	public void setProfessores(List<Professor> professores) {
		this.professores = professores;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Disciplina other = (Disciplina) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
	
	
}
