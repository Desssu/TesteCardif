package br.com.cardif.dto;

import java.io.Serializable;

import br.com.cardif.eao.Cargo;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CargoDto implements Serializable{
	
	private static final long serialVersionUID = 8694042378582569424L;

	private Long id;
	
	private String nome;
	
	public CargoDto(Cargo cargo) {
		this.id = cargo.getId();
		this.nome = cargo.getNome();
	}

}
