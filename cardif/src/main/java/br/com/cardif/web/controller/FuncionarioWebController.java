package br.com.cardif.web.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import br.com.cardif.converter.CargoConverter;
import br.com.cardif.converter.DepartamentoConverter;
import br.com.cardif.dto.FuncionarioDto;
import br.com.cardif.eao.Funcionario;
import br.com.cardif.service.CargoService;
import br.com.cardif.service.DepartamentoService;
import br.com.cardif.service.FuncionarioService;

@Controller
@RequestMapping("/funcionario")
public class FuncionarioWebController {

	@Autowired
	private FuncionarioService funcionarioService;
	
	@Autowired
	private CargoService cargoService;
	
	@Autowired
	private CargoConverter cargoConverter;
	
	@Autowired
	private DepartamentoConverter departamentoConverter;

	@Autowired
	private DepartamentoService departamentoService;
	
	@GetMapping("/consultar")
	public String consultar(Model model) {
		List<Funcionario> listaFuncionarios = funcionarioService.listarTodos();
		model.addAttribute("listaFuncionarios", listaFuncionarios);
		return "funcionario/consultar";
	}
	
	@GetMapping("/cadastrar")
	public String cadastrar(@ModelAttribute("funcionario")FuncionarioDto dto, Model model) {
		addListaCargoNoModel(model);
		addListaDepartamentoNoModel(model);
		return "/funcionario/cadastrar";
	}

	@PostMapping("/cadastrar")
	public String salvar(@Valid @ModelAttribute("funcionario") FuncionarioDto dto, BindingResult result, Model model) {
		if(result.hasErrors()) {
			return cadastrar(dto, model);
		}
		funcionarioService.salvar(dto);
		return consultar(model);
	}
	
	@GetMapping("/alterar/{id}")
	public String alterar(@PathVariable Long id, Model model) {
		Optional<Funcionario> optional = funcionarioService.consultarPorId(id);
		model.addAttribute("funcionario", new FuncionarioDto(optional.get()));
		addListaCargoNoModel(model);
		addListaDepartamentoNoModel(model);
		return "/funcionario/alterar";
	}

	@PostMapping("/alterar")
	public String alterar(@ModelAttribute("funcionario")FuncionarioDto dto, Model model) {
		funcionarioService.salvar(dto);
		return consultar(model);
	}

	@GetMapping("/delete/{id}")
	public String remover(@PathVariable Long id, Model model) {
		funcionarioService.deletar(id);
		return consultar(model);
	}
	
	private void addListaDepartamentoNoModel(Model model) {
		model.addAttribute("listaDepartamento", departamentoConverter.entidadeParaDto(departamentoService.listarTodos()));
	}

	private void addListaCargoNoModel(Model model) {
		model.addAttribute("listaCargo", cargoConverter.entidadeParaDto(cargoService.listarTodos()));
	}


}
