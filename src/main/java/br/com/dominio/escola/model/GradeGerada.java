package br.com.dominio.escola.model;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.persistence.*;

@Entity
@Table(name = "grade_gerada")
public class GradeGerada implements Serializable {

	private static final long serialVersionUID = 1L;	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column(name = "data_geracao", columnDefinition = "TIMESTAMP")
	private LocalDateTime dataGeracao;
	
	private Integer finalizada;
		
	@ManyToOne
	@JoinColumn(name = "grade_horaria_id")
	private GradeHoraria gradeHoraria;

	public GradeGerada() {}
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	} 

	public LocalDateTime getDataGeracao() {
		return dataGeracao;
	}

	public void setDataGeracao(LocalDateTime dataGeracao) {
		this.dataGeracao = dataGeracao;
	}

	public Integer getFinalizada() {
		return finalizada;
	}

	public void setFinalizada(Integer finalizada) {
		this.finalizada = finalizada;
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
		GradeGerada other = (GradeGerada) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
}
