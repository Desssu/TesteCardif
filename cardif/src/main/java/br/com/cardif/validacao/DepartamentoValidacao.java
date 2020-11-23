package br.com.cardif.validacao;

import java.text.MessageFormat;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.cardif.eao.Departamento;
import br.com.cardif.exception.ValidacaoNotFoundException;
import br.com.cardif.service.DepartamentoService;

@Service
public class DepartamentoValidacao {

	@Autowired
	private DepartamentoService departamentoService;

	public void validaIdDepartamento(List<Long> listaIds) {
		Set<String> idsInexistentes = new HashSet<String>();

		listaIds.forEach(id -> {
			Optional<Departamento> optional = departamentoService.consultarPorId(id);
			if (!optional.isPresent()) {
				idsInexistentes.add(id.toString());
			}
		});

		if (!idsInexistentes.isEmpty()) {
			throw new ValidacaoNotFoundException("Id do Departamento",
					MessageFormat.format("Id {0} não existe na base de dados", String.join(",", idsInexistentes)));
		}
	}
}
