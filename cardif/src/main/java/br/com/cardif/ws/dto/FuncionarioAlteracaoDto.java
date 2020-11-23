package br.com.cardif.ws.dto;

import java.io.Serializable;

import javax.validation.constraints.Min;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper=true)
public class FuncionarioAlteracaoDto extends FuncionarioNovoDto implements Serializable {

	private static final long serialVersionUID = -1103464239413972106L;
	
	@JsonProperty("Id")
	@Min(value = 1)
	private Long id;

}
