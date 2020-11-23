package br.com.cardif.eao;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class FuncionarioDepartamentoPK implements Serializable{

	private static final long serialVersionUID = -1065090824809872898L;

	@Column(name = "funcionario_id")
	private Long idFuncionario;

	@Column(name = "departamento_id")
	private Long idDepartamento;

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((idDepartamento == null) ? 0 : idDepartamento.hashCode());
		result = prime * result + ((idFuncionario == null) ? 0 : idFuncionario.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!(obj instanceof FuncionarioDepartamentoPK))
			return false;
		FuncionarioDepartamentoPK other = (FuncionarioDepartamentoPK) obj;
		if (idDepartamento == null) {
			if (other.idDepartamento != null)
				return false;
		} else if (!idDepartamento.equals(other.idDepartamento))
			return false;
		if (idFuncionario == null) {
			if (other.idFuncionario != null)
				return false;
		} else if (!idFuncionario.equals(other.idFuncionario))
			return false;
		return true;
	}

	public Long getIdFuncionario() {
		return idFuncionario;
	}

	public void setIdFuncionario(Long idFuncionario) {
		this.idFuncionario = idFuncionario;
	}

	public Long getIdDepartamento() {
		return idDepartamento;
	}

	public void setIdDepartamento(Long idDepartamento) {
		this.idDepartamento = idDepartamento;
	}
}
