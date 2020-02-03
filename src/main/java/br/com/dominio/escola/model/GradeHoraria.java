package br.com.dominio.escola.model;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

@Entity
@Table(name = "grade_horaria")
public class GradeHoraria implements Serializable {

	private static final long serialVersionUID = 1L;	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@NotBlank
	@Column(name="nome", nullable = false, unique = true, length = 40)	
	private String nome;
	
	@Column(name="numero_semestres")	
	private Integer numeroSemestres;
		
	@Column(name="ano")	
	private Integer ano;

	@Column(name = "data_criacao", columnDefinition = "TIMESTAMP")
	private LocalDateTime dataCriacao;
	
	@Column(name = "data_geracao", columnDefinition = "TIMESTAMP")
	private LocalDateTime dataGeracao;
	
	@Column(name = "data_limite", columnDefinition = "DATE")
	private LocalDate dataLimite;
	
	@OneToMany(mappedBy="id.gradeHoraria")
	private Set<HorarioProfessor> horariosProfessor = new HashSet<>();
	
	public GradeHoraria() {
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

	public Integer getAno() {
		return ano;
	}

	public void setAno(Integer ano) {
		this.ano = ano;
	}

	public LocalDateTime getDataCriacao() {
		return dataCriacao;
	}

	public void setDataCriacao(LocalDateTime dataCriacao) {
		this.dataCriacao = dataCriacao;
	}

	public LocalDateTime getDataGeracao() {
		return dataGeracao;
	}

	public void setDataGeracao(LocalDateTime dataGeracao) {
		this.dataGeracao = dataGeracao;
	}

	public LocalDate getDataLimite() {
		return dataLimite;
	}

	public void setDataLimite(LocalDate dataLimite) {
		this.dataLimite = dataLimite;
	}
	
	public Set<HorarioProfessor> getHorariosProfessor() {
		return horariosProfessor;
	}

	public void setHorariosProfessor(Set<HorarioProfessor> horariosProfessor) {
		this.horariosProfessor = horariosProfessor;
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
		GradeHoraria other = (GradeHoraria) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}



	
	
}
