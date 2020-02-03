package br.com.dominio.escola.model;

import java.io.Serializable;

import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Embeddable
public class HorarioProfessorPK implements Serializable {
		
	private static final long serialVersionUID = 1L;
	
	@ManyToOne
	@JoinColumn(name="professor_id")
	private Professor professor;
	
	@ManyToOne
	@JoinColumn(name="horario_id")
	private Horario horario;
	
	@ManyToOne
	@JoinColumn(name="grade_horaria_id")
	private GradeHoraria gradeHoraria;
	
	public Professor getProfessor() {
		return professor;
	}
	public void setProfessor(Professor professor) {
		this.professor = professor;
	}
	public Horario getHorario() {
		return horario;
	}
	public void setHorario(Horario horario) {
		this.horario = horario;
	}
	public GradeHoraria getGradeHoraria() {
		return gradeHoraria;
	}
	public void setGradeHoraria(GradeHoraria gradeHoraria) {
		this.gradeHoraria = gradeHoraria;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((gradeHoraria == null) ? 0 : gradeHoraria.hashCode());
		result = prime * result + ((horario == null) ? 0 : horario.hashCode());
		result = prime * result + ((professor == null) ? 0 : professor.hashCode());
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
		HorarioProfessorPK other = (HorarioProfessorPK) obj;
		if (gradeHoraria == null) {
			if (other.gradeHoraria != null)
				return false;
		} else if (!gradeHoraria.equals(other.gradeHoraria))
			return false;
		if (horario == null) {
			if (other.horario != null)
				return false;
		} else if (!horario.equals(other.horario))
			return false;
		if (professor == null) {
			if (other.professor != null)
				return false;
		} else if (!professor.equals(other.professor))
			return false;
		return true;
	}
	
	
	
	

}
