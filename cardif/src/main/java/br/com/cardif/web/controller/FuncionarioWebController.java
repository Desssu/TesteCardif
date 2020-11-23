package br.com.cardif.web.controller;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

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
	
	private static final Integer PAGINA = 1;
	
	private static final Integer QUANTIDADE_PAGINA = 5;

	private static final Integer PAGINA_UM = 1;

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
	public String consultar(Model model, @RequestParam("pagina") Optional<Integer> pagina,
		      @RequestParam("quantidade") Optional<Integer> quantidade) {
        Page<Funcionario> listaFuncionarios = consultarFuncionariosPorPagina(model, pagina, quantidade);
        incluirNumeroPaginasNoModel(model, listaFuncionarios);
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
		return consultar(model, null, null);
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
		return consultar(model, null, null);
	}

	@GetMapping("/delete/{id}")
	public String remover(@PathVariable Long id, Model model) {
		funcionarioService.deletar(id);
		return consultar(model, null, null);
	}
	
	private Page<Funcionario> consultarFuncionariosPorPagina(Model model, Optional<Integer> pagina,
			Optional<Integer> quantidade) {
		int currentPage = pagina.orElse(PAGINA);
        int pageSize = quantidade.orElse(QUANTIDADE_PAGINA);
		
        Page<Funcionario> listaFuncionarios = funcionarioService.listarTodos(PageRequest.of(currentPage - 1, pageSize));
		
        model.addAttribute("listaFuncionarios", listaFuncionarios);
		return listaFuncionarios;
	}

	private void incluirNumeroPaginasNoModel(Model model, Page<Funcionario> listaFuncionarios) {
		int totalPaginas = listaFuncionarios.getTotalPages();
        if (totalPaginas > 0) {
            List<Integer> numeroPaginas = IntStream.rangeClosed(PAGINA_UM, totalPaginas)
                .boxed()
                .collect(Collectors.toList());
            model.addAttribute("numeroPaginas", numeroPaginas);
        }
	}
	
	private void addListaDepartamentoNoModel(Model model) {
		model.addAttribute("listaDepartamento", departamentoConverter.entidadeParaDto(departamentoService.listarTodos()));
	}

	private void addListaCargoNoModel(Model model) {
		model.addAttribute("listaCargo", cargoConverter.entidadeParaDto(cargoService.listarTodos()));
	}


}
