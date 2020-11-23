package br.com.cardif.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.cardif.converter.FuncionarioConverter;
import br.com.cardif.dto.FuncionarioDto;
import br.com.cardif.eao.Funcionario;
import br.com.cardif.repository.FuncionarioRepository;
import br.com.cardif.ws.dto.FuncionarioAlteracaoDto;
import br.com.cardif.ws.dto.FuncionarioConsultaDto;
import br.com.cardif.ws.dto.FuncionarioNovoDto;

@Service
public class FuncionarioService {

	@Autowired
	private FuncionarioRepository funcionarioRepository;
	
	@Autowired
	private FuncionarioConverter funcionarioConverter;

	public List<Funcionario> listarTodos() {
		return funcionarioRepository.findAll();
	}
	
	public List<Funcionario> consultarPorIdDepartamento(Long idDepartamento) {
		return funcionarioRepository.findByListaDepartamentosId(idDepartamento);
	}
	
	public Optional<Funcionario> consultarPorId(Long id) {
		return funcionarioRepository.findById(id);
	}

	public Funcionario salvar(Funcionario funcionario) {
		return funcionarioRepository.save(funcionario);
	}

	public boolean deletar(Long id) {
		Optional<Funcionario> optional = consultarPorId(id);
		if (optional.isPresent()) {
			funcionarioRepository.delete(optional.get());
			return true;
		}else {
			return false;
		}
	}
	
	public FuncionarioConsultaDto salvar(FuncionarioNovoDto dto) {
		Funcionario funcionario = salvar(funcionarioConverter.dtoParaEntidade(dto));
		return new FuncionarioConsultaDto(funcionario);
	}
	
	public FuncionarioConsultaDto salvar(FuncionarioAlteracaoDto dto) {
		Funcionario funcionario = salvar(funcionarioConverter.dtoParaEntidade(dto));
		return new FuncionarioConsultaDto(funcionario);
	}
	
	public FuncionarioConsultaDto salvar(FuncionarioDto dto) {
		Funcionario funcionario = salvar(funcionarioConverter.dtoParaEntidade(dto));
		return new FuncionarioConsultaDto(funcionario);
	}

}
