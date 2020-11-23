package br.com.cardif.ws.dto;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class FuncionarioNovoDto implements Serializable {

	private static final long serialVersionUID = -1103464239413972106L;

	
	@JsonProperty("Nome")
	@NotEmpty
	@Size(min = 1, max = 50, message = "O nome deve conter entre 1 e 50 caracteres") 
	private String nome;

	@JsonProperty("Idade")
	@Min(value = 1)
	@Max(value = 150)
	private Integer idade;

	@JsonProperty("Data de Nascimento")
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@NotNull
	@Past
	private LocalDate dataNascimento;

	@JsonProperty("Numero de Documento")
	@NotEmpty
	@Size(min = 1, max = 50, message = "O número do documento deve conter entre 1 e 50 caracteres") 
	private String documento;

	@JsonProperty("Id do Cargo")
	@Min(value = 1)
	private Long idCargo;
	
	@Size(min=1)
	@JsonProperty("Lista de Ids dos Departamentos")
	private List<Long> listaDepartamentos;

}
