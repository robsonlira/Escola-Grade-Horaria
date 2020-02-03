package br.com.dominio.escola.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "ag_configuracao")
public class AgConfiguracao implements Serializable {

	private static final long serialVersionUID = 1L;	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@NotBlank
	@Column(name="nome", nullable = false, unique = true, length = 50)	
	private String nome;
	
	@Column(name="numero_geracoes")	
	private Integer numeroGeracoes;
	
	@Column(name="tamanho_populacao")	
	private Integer tamanhoPopulacao;
	
	@Column(name="taxa_corte")	
	private Integer taxaCorte;

	@Column(name="taxa_mutacao")	
	private Integer taxaMutacao;

	@Column(name="grau_desejavel_aceitavel")	
	private Integer grauDesejavelAceitavel;

	@Column(name="tentativas")	
	private Integer tentativas;

	@Column(name="mutacao")	
	private Integer mutacao;
	
	@Column(name="mutacoes")	
	private Integer mutacoes;

	@Column(name="remove_iguais")	
	private Integer removeIguais;

	@Column(name="remove_piores")	
	private Integer removePiores;

	@Column(name="ativa")	
	private Boolean ativa;

	@Column(name="observacoes", nullable = true, length = 250)	
	private String observacoes;
	
	public AgConfiguracao() {}
		
	public AgConfiguracao(Integer id, String nome, Boolean ativa) {
		super();
		this.id = id;
		this.nome = nome;
		this.ativa = ativa;
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


	public Integer getNumeroGeracoes() {
		return numeroGeracoes;
	}


	public void setNumeroGeracoes(Integer numeroGeracoes) {
		this.numeroGeracoes = numeroGeracoes;
	}


	public Integer getTamanhoPopulacao() {
		return tamanhoPopulacao;
	}


	public void setTamanhoPopulacao(Integer tamanhoPopulacao) {
		this.tamanhoPopulacao = tamanhoPopulacao;
	}


	public Integer getTaxaCorte() {
		return taxaCorte;
	}


	public void setTaxaCorte(Integer taxaCorte) {
		this.taxaCorte = taxaCorte;
	}


	public Integer getTaxaMutacao() {
		return taxaMutacao;
	}


	public void setTaxaMutacao(Integer taxaMutacao) {
		this.taxaMutacao = taxaMutacao;
	}


	public Integer getGrauDesejavelAceitavel() {
		return grauDesejavelAceitavel;
	}


	public void setGrauDesejavelAceitavel(Integer grauDesejavelAceitavel) {
		this.grauDesejavelAceitavel = grauDesejavelAceitavel;
	}


	public Integer getTentativas() {
		return tentativas;
	}


	public void setTentativas(Integer tentativas) {
		this.tentativas = tentativas;
	}


	public Integer getMutacao() {
		return mutacao;
	}


	public void setMutacao(Integer mutacao) {
		this.mutacao = mutacao;
	}


	public Integer getMutacoes() {
		return mutacoes;
	}


	public void setMutacoes(Integer mutacoes) {
		this.mutacoes = mutacoes;
	}


	public Integer getRemoveIguais() {
		return removeIguais;
	}


	public void setRemoveIguais(Integer removeIguais) {
		this.removeIguais = removeIguais;
	}


	public Integer getRemovePiores() {
		return removePiores;
	}


	public void setRemovePiores(Integer removePiores) {
		this.removePiores = removePiores;
	}


	public Boolean getAtiva() {
		return ativa;
	}


	public void setAtiva(Boolean ativa) {
		this.ativa = ativa;
	}


	public String getObservacoes() {
		return observacoes;
	}

	public void setObservacoes(String observacoes) {
		this.observacoes = observacoes;
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
		AgConfiguracao other = (AgConfiguracao) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	
	
}
