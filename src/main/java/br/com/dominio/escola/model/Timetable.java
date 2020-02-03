package br.com.dominio.escola.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Timetable  implements Serializable {

	private static final long serialVersionUID = 1L;	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	private Integer seg;
	private Integer ter;
	private Integer qua;
	private Integer qui;
	private Integer sex;
	private Integer sab;

	@ManyToOne
	@JoinColumn(name = "grade_gerada_id")
	private GradeGerada gradeGerada;
	
	@ManyToOne
	@JoinColumn(name = "grade_horaria_id")
	private GradeHoraria gradeHoraria;

	@ManyToOne
	@JoinColumn(name = "professor_id")
	private Professor professor;
	
	@ManyToOne
	@JoinColumn(name = "disciplina_id")
	private Disciplina disciplina;

	@ManyToOne
	@JoinColumn(name = "horario_id")
	private Horario horario;
	
	public Timetable() {}
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getSeg() {
		return seg;
	}

	public void setSeg(Integer seg) {
		this.seg = seg;
	}

	public Integer getTer() {
		return ter;
	}

	public void setTer(Integer ter) {
		this.ter = ter;
	}

	public Integer getQua() {
		return qua;
	}

	public void setQua(Integer qua) {
		this.qua = qua;
	}

	public Integer getQui() {
		return qui;
	}

	public void setQui(Integer qui) {
		this.qui = qui;
	}

	public Integer getSex() {
		return sex;
	}

	public void setSex(Integer sex) {
		this.sex = sex;
	}

	public Integer getSab() {
		return sab;
	}

	public void setSab(Integer sab) {
		this.sab = sab;
	}

	public GradeGerada getGradeGerada() {
		return gradeGerada;
	}

	public void setGradeGerada(GradeGerada gradeGerada) {
		this.gradeGerada = gradeGerada;
	}

	public GradeHoraria getGradeHoraria() {
		return gradeHoraria;
	}

	public void setGradeHoraria(GradeHoraria gradeHoraria) {
		this.gradeHoraria = gradeHoraria;
	}

	public Professor getProfessor() {
		return professor;
	}

	public void setProfessor(Professor professor) {
		this.professor = professor;
	}

	public Disciplina getDisciplina() {
		return disciplina;
	}

	public void setDisciplina(Disciplina disciplina) {
		this.disciplina = disciplina;
	}

	public Horario getHorario() {
		return horario;
	}

	public void setHorario(Horario horario) {
		this.horario = horario;
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
		Timetable other = (Timetable) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	

}
