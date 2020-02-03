package br.com.dominio.escola.model.enums;

public enum UfEnum {
	
	AC("AC", "Acre"),
	AL("AL", "Alagoas");

	private String cod;
	private String descricao;
	
	private UfEnum(String cod, String descricao) {
		this.cod = cod;
		this.descricao = descricao;
	}
	
	public String getCod() {
		return cod;
	}
	
	public String getDescricao () {
		return descricao;
	}
	
    public static UfEnum toEnum(Integer cod) {
		
		if (cod == null) {
			return null;
		}
		
		for (UfEnum x : UfEnum.values()) {
			if (cod.equals(x.getCod())) {
				return x;
			}
		}
		
		throw new IllegalArgumentException("Id inválido: " + cod);
	}
	
	
}
