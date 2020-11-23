package br.com.cardif.ws.restcontroller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.cardif.service.CargoService;
import br.com.cardif.ws.dto.CargoDto;

@RestController
@RequestMapping("/api/cargo")
public class CargoWsController {

	@Autowired
	private CargoService cargoService;

	@GetMapping
	public List<CargoDto> listarTodos() {
		return cargoService.listarTodos().stream().map(CargoDto::new).collect(Collectors.toList());
	}
}
