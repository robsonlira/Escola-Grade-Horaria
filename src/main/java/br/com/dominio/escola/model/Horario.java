package br.com.dominio.escola.model;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Horario implements Serializable {

	private static final long serialVersionUID = 1L;	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@NotBlank
	@Column(name="nome", nullable = false, unique = true, length = 20)	
	private String nome;	
	
	@NotNull
	@ManyToOne
	@JoinColumn(name="turno_id")
	private Turno turno;	
	
	@JsonIgnore
	@OneToMany(mappedBy="id.horario")
	private Set<HorarioProfessor> horariosProfessor = new HashSet<>();
	
	public Horario() {}
	
	public Horario(Integer id, String nome, Turno turno) {
		this.id = id;
		this.nome = nome;
		this.turno = turno;
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

	public Turno getTurno() {
		return turno;
	}

	public void setTurno(Turno turno) {
		this.turno = turno;
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
		Horario other = (Horario) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Horario [id=" + id + ", nome=" + nome + ", turno=" + turno + "]";
	}
	
	
}
