package br.com.dominio.escola.controller.exception;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

import br.com.dominio.escola.service.exceptions.DataIntegrityException;
import br.com.dominio.escola.service.exceptions.ObjectNotFoundException;

@ControllerAdvice
public class ControllerExceptionHandler {

	@ExceptionHandler(ObjectNotFoundException.class)
	public ModelAndView objectNotFound(ObjectNotFoundException e) {
		ModelAndView mv = new ModelAndView("error");
		mv.addObject("status",404);
		mv.addObject("error","Não encontrado");
		mv.addObject("message", e.getMessage());
		
		return mv;
	}

	@ExceptionHandler(DataIntegrityException.class)
	public ModelAndView dataIntegrity(DataIntegrityException e) {

		ModelAndView mv = new ModelAndView("error");
		mv.addObject("status",500);
		mv.addObject("error","Operação não realizada");
		mv.addObject("message", e.getMessage());
		
		return mv;				
	}
	
	
	
}
