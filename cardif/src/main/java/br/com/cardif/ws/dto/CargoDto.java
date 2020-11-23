package br.com.cardif.ws.dto;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonProperty;

import br.com.cardif.eao.Cargo;
import lombok.Data;

@Data
public class CargoDto implements Serializable{
	
	private static final long serialVersionUID = 8694042378582569424L;

	@JsonProperty("Id")
	private Long id;
	
	@JsonProperty("Nome")
	private String nome;
	
	public CargoDto(Cargo cargo) {
		this.id = cargo.getId();
		this.nome = cargo.getNome();
	}

}
