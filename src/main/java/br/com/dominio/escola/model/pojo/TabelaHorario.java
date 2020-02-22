package br.com.dominio.escola.model.pojo;

import java.util.List;

import br.com.dominio.escola.model.Horario;

public class TabelaHorario {

	String msg;
	List<Horario> result;

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public List<Horario> getResult() {
		return result;
	}

	public void setResult(List<Horario> result) {
		this.result = result;
	}

}
