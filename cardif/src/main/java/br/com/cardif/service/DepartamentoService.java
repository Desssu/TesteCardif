package br.com.cardif.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.cardif.eao.Departamento;
import br.com.cardif.repository.DepartamentoRepository;

@Service
public class DepartamentoService {

	@Autowired
	private DepartamentoRepository departamentoRepository;

	public List<Departamento> listarTodos() {
		return departamentoRepository.findAll();
	}

	public Optional<Departamento> consultarPorId(Long id) {
		return departamentoRepository.findById(id);
	}

}
