package br.com.cardif.ws.restcontroller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.cardif.service.DepartamentoService;
import br.com.cardif.ws.dto.DepartamentoDto;

@RestController
@RequestMapping("/api/departamento")
public class DepartamentoWsController {
	
	@Autowired
	private DepartamentoService departamentoService;

	@GetMapping
	public List<DepartamentoDto> listarTodos() {
		return departamentoService.listarTodos().stream().map(DepartamentoDto::new).collect(Collectors.toList());
	}

}
