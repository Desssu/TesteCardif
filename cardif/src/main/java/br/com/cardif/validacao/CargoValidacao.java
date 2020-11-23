package br.com.cardif.validacao;

import java.text.MessageFormat;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.cardif.eao.Cargo;
import br.com.cardif.exception.ValidacaoNotFoundException;
import br.com.cardif.service.CargoService;

@Service
public class CargoValidacao {
	
	@Autowired
	private CargoService cargoService; 
	
	public void validaIdCargo(Long idCargo) {
		Optional<Cargo> optional = cargoService.consultarPorId(idCargo);
		if (!optional.isPresent()) {
			throw new ValidacaoNotFoundException("Id do Cargo", MessageFormat.format("Id {0} não existe na base de dados", idCargo));
		}
	}

}
