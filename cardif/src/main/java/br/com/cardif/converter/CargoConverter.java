package br.com.cardif.converter;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import br.com.cardif.dto.CargoDto;
import br.com.cardif.eao.Cargo;
import br.com.cardif.service.CargoService;

@Component
public class CargoConverter implements Converter<String, CargoDto>{
	
	@Autowired
	private CargoService cargoService;

	public List<CargoDto> entidadeParaDto(List<Cargo> listaCargo) {
		return listaCargo.stream().map(CargoDto::new).collect(Collectors.toList());
	}

	@Override
	public CargoDto convert(String idCargoTexto) {
        try {
        	Long id = Long.valueOf(idCargoTexto);
            Optional<Cargo> optional = cargoService.consultarPorId(id);
            if (optional.isPresent()) {
            	return new CargoDto(optional.get());
            }else {
            	return null;
            }
        } catch (Exception ex) {
            return null;
        }
	}
}
