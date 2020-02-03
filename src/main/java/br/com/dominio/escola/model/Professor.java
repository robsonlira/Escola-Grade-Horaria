package br.com.dominio.escola.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotBlank;

@Entity
public class Professor implements Serializable {

	private static final long serialVersionUID = 1L;	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@NotBlank
	@Column(name="nome", nullable = false, unique = true, length = 50)	
	private String nome;
	
	@ManyToMany
	@JoinTable(name="professor_disciplina",
	  joinColumns = @JoinColumn(name="professor_id"),
	  inverseJoinColumns = @JoinColumn(name="disciplina_id") )
	private List<Disciplina> disciplinas = new ArrayList<>();	
	
	@OneToMany(mappedBy="id.professor")
	private Set<HorarioProfessor> horariosProfessor = new HashSet<>();
	
	public Professor() {}
	
	public Professor(Integer id, String nome) {
		this.id = id;
		this.nome = nome;
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

	public List<Disciplina> getDisciplinas() {
		return disciplinas;
	}

	public void setDisciplinas(List<Disciplina> disciplinas) {
		this.disciplinas = disciplinas;
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
		Professor other = (Professor) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}


	
}
