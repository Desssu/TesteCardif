package br.com.cardif.ws.restcontroller;

import java.net.URI;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.cardif.eao.Funcionario;
import br.com.cardif.service.FuncionarioService;
import br.com.cardif.validacao.CargoValidacao;
import br.com.cardif.validacao.DepartamentoValidacao;
import br.com.cardif.validacao.FuncionarioValidacao;
import br.com.cardif.ws.dto.FuncionarioAlteracaoDto;
import br.com.cardif.ws.dto.FuncionarioConsultaDto;
import br.com.cardif.ws.dto.FuncionarioNovoDto;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/api/funcionario")
public class FuncionarioWsController {

	@Autowired
	private FuncionarioService funcionarioService;

	@Autowired
	private CargoValidacao cargoValidacao;

	@Autowired
	private DepartamentoValidacao departamentoValidacao;

	@Autowired
	private FuncionarioValidacao funcionarioValidacao;

	private final static String PATH_CONSULTA = "/api/funcionario/{id}";

	@PostMapping
	@Transactional
	@ApiOperation(value = "Cria um funcionário na base de dados")
	public ResponseEntity<FuncionarioConsultaDto> salvar(@RequestBody @Valid FuncionarioNovoDto dto,
			UriComponentsBuilder uriBuilder) {
		validarCadastro(dto);
		FuncionarioConsultaDto funcionarioDto = funcionarioService.salvar(dto);
		URI uri = uriBuilder.path(PATH_CONSULTA).buildAndExpand(funcionarioDto.getId()).toUri();
		return ResponseEntity.created(uri).body(funcionarioDto);
	}

	@PutMapping
	@Transactional
	@ApiOperation(value = "Altera dados de um funcionário na base de dados")
	public ResponseEntity<FuncionarioConsultaDto> alterar(@RequestBody @Valid FuncionarioAlteracaoDto dto,
			UriComponentsBuilder uriBuilder) {
		validarAlteracao(dto);
		FuncionarioConsultaDto funcionarioDto = funcionarioService.salvar(dto);
		URI uri = uriBuilder.path(PATH_CONSULTA).buildAndExpand(dto.getId()).toUri();
		return ResponseEntity.created(uri).body(funcionarioDto);
	}

	@DeleteMapping("/{id}")
	@Transactional
	@ApiOperation(value = "Remove um funcionário da base de dados")
	public ResponseEntity<?> deletar(@PathVariable Long id) {
		boolean deletou = funcionarioService.deletar(id);
		if (deletou) {
			return ResponseEntity.ok().build();
		} else {
			return ResponseEntity.notFound().build();
		}
	}

	@GetMapping("/{id}")
	@Transactional
	@ApiOperation(value = "Consulta um funcionário pelo Id")
	public ResponseEntity<FuncionarioConsultaDto> consultarPorId(@PathVariable Long id) {
		Optional<Funcionario> OptionalFuncionario = funcionarioService.consultarPorId(id);

		if (OptionalFuncionario.isPresent()) {
			return ResponseEntity.ok(new FuncionarioConsultaDto(OptionalFuncionario.get()));
		} else {
			return ResponseEntity.notFound().build();
		}
	}

	@GetMapping("/departamento/{id}")
	@ApiOperation(value = "Lista todos os funcionários de um departamento")
	public ResponseEntity<List<FuncionarioConsultaDto>> consultarPorIdDepartamento(@PathVariable Long id) {
		List<Funcionario> lista = funcionarioService.consultarPorIdDepartamento(id);
		if (lista.isEmpty()) {
			return ResponseEntity.notFound().build();
		} else {
			return ResponseEntity.ok(lista.stream().map(FuncionarioConsultaDto::new).collect(Collectors.toList()));
		}
	}

	@GetMapping
	@ApiOperation(value = "Lista todos os funcionários cadastrados")
	public ResponseEntity<List<FuncionarioConsultaDto>> listarTodos() {
		List<Funcionario> lista = funcionarioService.listarTodos();
		if (lista.isEmpty()) {
			return ResponseEntity.notFound().build();
		} else {
			return ResponseEntity.ok(lista.stream().map(FuncionarioConsultaDto::new).collect(Collectors.toList()));
		}
	}

	private void validarCadastro(FuncionarioNovoDto dto) {
		cargoValidacao.validaIdCargo(dto.getIdCargo());
		departamentoValidacao.validaIdDepartamento(dto.getListaDepartamentos());
	}

	private void validarAlteracao(@Valid FuncionarioAlteracaoDto dto) {
		funcionarioValidacao.validaIdFuncionario(dto.getId());
		cargoValidacao.validaIdCargo(dto.getIdCargo());
		departamentoValidacao.validaIdDepartamento(dto.getListaDepartamentos());
	}
}
