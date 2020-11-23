package br.com.cardif.dto;

import java.io.Serializable;

import br.com.cardif.eao.Departamento;
import lombok.Data;

@Data
public class DepartamentoDto implements Serializable{
	
	private static final long serialVersionUID = 8694042378582569424L;

	private Long id;
	
	private String nome;
	
	public DepartamentoDto(Departamento departamento) {
		this.id = departamento.getId();
		this.nome = departamento.getNome();
	}

}
