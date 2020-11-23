package br.com.cardif.validacao;

import java.text.MessageFormat;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.cardif.eao.Funcionario;
import br.com.cardif.exception.ValidacaoNotFoundException;
import br.com.cardif.service.FuncionarioService;

@Service
public class FuncionarioValidacao {
	
	@Autowired
	private FuncionarioService funcionarioService; 
	
	public void validaIdFuncionario(Long idFuncionario) {
		Optional<Funcionario> optional = funcionarioService.consultarPorId(idFuncionario);
		if (!optional.isPresent()) {
			throw new ValidacaoNotFoundException("Id do Funcionario", MessageFormat.format("Id {0} não existe na base de dados", idFuncionario));
		}
	}

}
