package br.com.cardif.config.validacao;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import br.com.cardif.exception.ValidacaoNotFoundException;

@RestControllerAdvice
public class ErroDeValidacaoHandler {

	@Autowired
	private MessageSource messageSource;

	@ResponseStatus(code = HttpStatus.BAD_REQUEST)
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public List<ErroDeValidacaoDto> handle(MethodArgumentNotValidException exception) {
		List<ErroDeValidacaoDto> dto = new ArrayList<>();

		List<FieldError> listaErros = exception.getBindingResult().getFieldErrors();
		listaErros.forEach(e -> {
			String mensagem = messageSource.getMessage(e, LocaleContextHolder.getLocale());
			dto.add(new ErroDeValidacaoDto(e.getField(), mensagem));
		});
		
		dto.sort(Comparator.comparing(ErroDeValidacaoDto::getCampo));
		return dto;
	}
	
	@ResponseStatus(code = HttpStatus.NOT_FOUND)
	@ExceptionHandler(ValidacaoNotFoundException.class)
	public List<ErroDeValidacaoDto> handle(ValidacaoNotFoundException exception) {
		List<ErroDeValidacaoDto> dto = new ArrayList<>();

		dto.add(new ErroDeValidacaoDto(exception.getCampo(), exception.getMessage()));
		
		return dto;
	}

}
