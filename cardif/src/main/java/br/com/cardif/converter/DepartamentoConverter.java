package br.com.cardif.converter;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import br.com.cardif.dto.DepartamentoDto;
import br.com.cardif.eao.Departamento;
import br.com.cardif.service.DepartamentoService;

@Component
public class DepartamentoConverter implements Converter<String, DepartamentoDto>{

	@Autowired
	private DepartamentoService departamentoService;

	public List<DepartamentoDto> entidadeParaDto(List<Departamento> listaDepartamento) {
		return listaDepartamento.stream().map(DepartamentoDto::new).collect(Collectors.toList());
	}
	
	@Override
	public DepartamentoDto convert(String idCargoTexto) {
        try {
        	Long id = Long.valueOf(idCargoTexto);
            Optional<Departamento> optional = departamentoService.consultarPorId(id);
            if (optional.isPresent()) {
            	return new DepartamentoDto(optional.get());
            }else {
            	return null;
            }
        } catch (NumberFormatException ex) {
            return null;
        }
	}
}
