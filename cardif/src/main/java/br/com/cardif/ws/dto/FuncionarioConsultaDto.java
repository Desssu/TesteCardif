package br.com.cardif.ws.dto;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

import br.com.cardif.eao.Funcionario;
import lombok.Data;

@Data
public class FuncionarioConsultaDto implements Serializable {

	private static final long serialVersionUID = -1103464239413972106L;

	@JsonProperty("Id")
	private Long id;

	@JsonProperty("Nome")
	private String nome;

	@JsonProperty("Idade")
	private Integer idade;

	@JsonProperty("Data de Nascimento")
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
	private LocalDate dataNascimento;

	@JsonProperty("Numero de Documento")
	private String documento;

	@JsonProperty("Cargo")
	private CargoDto cargoDto;

	@JsonProperty("Lista de Departamentos")
	private List<DepartamentoDto> listaDepartamentos;

	public FuncionarioConsultaDto(Funcionario funcionario) {
		this.id = funcionario.getId();
		this.nome = funcionario.getNome();
		this.idade = funcionario.getIdade();
		this.dataNascimento = funcionario.getDataNascimento();
		this.documento = funcionario.getDocumento();
		this.cargoDto = new CargoDto(funcionario.getCargo());
		this.listaDepartamentos = funcionario.getListaDepartamentos().stream().map(DepartamentoDto::new)
				.collect(Collectors.toList());

	}

}
