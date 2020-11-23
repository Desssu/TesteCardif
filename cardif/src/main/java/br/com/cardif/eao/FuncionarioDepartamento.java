package br.com.cardif.eao;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "funcionario_departamento")
@Getter
@Setter
public class FuncionarioDepartamento {
	
	@EmbeddedId
	private FuncionarioDepartamentoPK id;
	
	@Column(name = "flag_func_chefe_departamento")
	private String flagfuncChefeDepartamento;

}
