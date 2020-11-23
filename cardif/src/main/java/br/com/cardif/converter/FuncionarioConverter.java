package br.com.cardif.converter;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import br.com.cardif.dto.FuncionarioDto;
import br.com.cardif.eao.Cargo;
import br.com.cardif.eao.Departamento;
import br.com.cardif.eao.Funcionario;
import br.com.cardif.ws.dto.FuncionarioAlteracaoDto;
import br.com.cardif.ws.dto.FuncionarioNovoDto;

@Service
public class FuncionarioConverter {

	public Funcionario dtoParaEntidade(FuncionarioNovoDto dto) {
		Funcionario entidade = new Funcionario();
		entidade.setDataNascimento(dto.getDataNascimento());
		entidade.setDocumento(dto.getDocumento());
		entidade.setIdade(dto.getIdade());
		entidade.setNome(dto.getNome());
		entidade.setCargo(new Cargo(dto.getIdCargo(), null));
		List<Departamento> listaDepartamento = new ArrayList<Departamento>();
		dto.getListaDepartamentos().forEach(id -> listaDepartamento.add(new Departamento(id, null)));
		entidade.setListaDepartamentos(listaDepartamento);
		return entidade;
	}

	public Funcionario dtoParaEntidade(FuncionarioDto dto) {

		Funcionario entidade = new Funcionario();
		entidade.setId(dto.getId());
		entidade.setDataNascimento(dto.getDataNascimento());
		entidade.setDocumento(dto.getDocumento());
		entidade.setIdade(dto.getIdade());
		entidade.setNome(dto.getNome());
		entidade.setCargo(new Cargo(dto.getCargoDto().getId(), dto.getCargoDto().getNome()));
		List<Departamento> listaDepartamento = new ArrayList<Departamento>();
		dto.getListaDepartamentos().forEach(
				departamento -> listaDepartamento.add(new Departamento(departamento.getId(), departamento.getNome())));
		entidade.setListaDepartamentos(listaDepartamento);
		return entidade;
	}

	public Funcionario dtoParaEntidade(FuncionarioAlteracaoDto dto) {
		Funcionario entidade = dtoParaEntidade((FuncionarioNovoDto) dto);
		entidade.setId(dto.getId());
		return entidade;
	}
}
