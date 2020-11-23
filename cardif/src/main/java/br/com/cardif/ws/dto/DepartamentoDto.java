package br.com.cardif.ws.dto;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonProperty;

import br.com.cardif.eao.Departamento;
import lombok.Data;

@Data
public class DepartamentoDto implements Serializable{
	
	private static final long serialVersionUID = 8694042378582569424L;

	@JsonProperty("Id")
	private Long id;
	
	@JsonProperty("Nome")
	private String nome;
	
	public DepartamentoDto(Departamento departamento) {
		this.id = departamento.getId();
		this.nome = departamento.getNome();
	}

}
