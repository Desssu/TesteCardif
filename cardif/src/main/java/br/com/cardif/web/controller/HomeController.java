package br.com.cardif.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.view.RedirectView;

@Controller
public class HomeController {

	@GetMapping
	private RedirectView home() {
		return new RedirectView("/cardif/funcionario/consultar");
	}

}
