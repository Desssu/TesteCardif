package br.com.cardif.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.cardif.eao.Cargo;
import br.com.cardif.repository.CargoRepository;

@Service
public class CargoService {
	
	@Autowired
	private CargoRepository cargoRepository;

	public List<Cargo> listarTodos() {
		return cargoRepository.findAll();
	}

	public Optional<Cargo> consultarPorId(Long id) {
		return cargoRepository.findById(id);
	}

}
