package br.com.dominio.escola.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.com.dominio.escola.model.AgConfiguracao;
import br.com.dominio.escola.service.AgConfiguracaoService;
import br.com.dominio.escola.service.exceptions.DataIntegrityException;

@Controller
@RequestMapping("/agconfiguracoes")
public class AgController {
	
	private static final String VIEW_LIST = "/agconfig/lista";
	private static final String VIEW_FORM = "/agconfig/cadastro";
	
	@Autowired
	private AgConfiguracaoService service;

	@GetMapping("/cadastrar")
	public ModelAndView cadastrar(AgConfiguracao registro) {
		ModelAndView mv = new ModelAndView(VIEW_FORM);
		return mv;
	}
	
	@GetMapping
	public ModelAndView listar() {
		ModelAndView mv = new ModelAndView(VIEW_LIST);
		mv.addObject("itens", service.findAll());
		return mv;
	}
	
	@PostMapping("/salvar")
	public ModelAndView salvar(@Valid AgConfiguracao registro, BindingResult result, RedirectAttributes attr) {
		
		if (result.hasErrors()) {
			return cadastrar(registro);
		}
		
		service.insert(registro);
		attr.addFlashAttribute("success", "Registro inserido com sucesso.");
		return new ModelAndView("redirect:/agconfiguracoes/cadastrar");
	}
	
	@GetMapping("{id}")
	public ModelAndView preEditar(@PathVariable("id") AgConfiguracao agConfiguracao) {
		ModelAndView mv = cadastrar(agConfiguracao);
		mv.addObject(agConfiguracao);
		return mv;
	}
	
	@PostMapping("/editar")
	public ModelAndView editar(@Valid AgConfiguracao registro, BindingResult result, RedirectAttributes attr) {
		
		if (result.hasErrors()) {
			return cadastrar(registro);
		}
		
		service.update(registro);
		attr.addFlashAttribute("success", "Registro alterado com sucesso.");
		return new ModelAndView("redirect:/agconfiguracoes/cadastrar");
	}
	
	@DeleteMapping("/{id}")
	public @ResponseBody ResponseEntity<?> excluir(@PathVariable("id") Integer id) {
		try {
			service.delete(id);
		} catch (DataIntegrityException e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
		return ResponseEntity.ok().build();
	}
	
}
