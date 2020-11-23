package br.com.cardif.converter;

import java.util.Optional;

import org.springframework.core.convert.converter.Converter;

import br.com.cardif.dto.CargoDto;
import br.com.cardif.eao.Cargo;
import br.com.cardif.service.CargoService;

public class CargoDtoIdToCargoDtoConverter implements Converter<String, CargoDto> {

    private CargoService tradeService;
    
    public CargoDtoIdToCargoDtoConverter (CargoService tradeService) {
        this.tradeService = tradeService;
    }

    @Override
    public CargoDto convert (String idTexto) {
        try {
            Long id = Long.valueOf(idTexto);
            Optional<Cargo> optional = tradeService.consultarPorId(id);
            if (optional.isPresent()) {
            	return new CargoDto(optional.get());
            }else {
            	return null;
            }
        } catch (NumberFormatException e) {
            return null;
        }
    }
}