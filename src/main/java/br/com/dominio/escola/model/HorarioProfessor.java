package br.com.dominio.escola.model;

import java.io.Serializable;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;

@Entity
public class HorarioProfessor implements Serializable {
	
    private static final long serialVersionUID = 1L;
	
    @EmbeddedId
	private HorarioProfessorPK id = new HorarioProfessorPK();	
	private Integer seg;
	private Integer ter;
	private Integer qua;
	private Integer qui;
	private Integer sex;
	private Integer sab;
	
	public HorarioProfessor() {}

	public HorarioProfessor(Professor professor, Horario horario, GradeHoraria grade, 
			Integer seg, Integer ter, Integer qua, Integer qui, Integer sex, Integer sab) {
		super();
		this.id.setProfessor(professor);
		this.id.setHorario(horario);
		this.id.setGradeHoraria(grade);
		this.seg = seg;
		this.ter = ter;
		this.qua = qua;
		this.qui = qui;
		this.sex = sex;
		this.sab = sab;
	}
	
	public Professor getProfessor() {
		return id.getProfessor();
	}

	public Horario getHorario() {
		return id.getHorario();
	}

	public GradeHoraria getGradeHoraria() {
		return id.getGradeHoraria();
	}
	
	public HorarioProfessorPK getId() {
		return id;
	}

	public void setId(HorarioProfessorPK id) {
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
		HorarioProfessor other = (HorarioProfessor) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	

}
