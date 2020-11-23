package br.com.cardif.dto;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;

import org.springframework.format.annotation.DateTimeFormat;

import br.com.cardif.eao.Funcionario;
import lombok.Data;

@Data
public class FuncionarioDto implements Serializable {

	private static final long serialVersionUID = -1103464239413972106L;

	private Long id;

	@NotBlank
	private String nome;

	@NotNull
	@Min(value = 1)
	@Max(value = 150)
	private Integer idade;

	@NotNull
	@Past
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate dataNascimento;

	@NotBlank
	private String documento;

	@NotNull(message = "Selecione um cargo")
	private CargoDto cargoDto;

	@NotEmpty(message = "Deve ser selecionado ao menos um departamento")
	private List<DepartamentoDto> listaDepartamentos;
	
	public FuncionarioDto() {
	}

	public FuncionarioDto(Funcionario funcionario) {
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
