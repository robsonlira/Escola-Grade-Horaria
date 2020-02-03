package br.com.dominio.escola.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.com.dominio.escola.model.Curso;
import br.com.dominio.escola.model.Disciplina;
import br.com.dominio.escola.repository.Cursos;
import br.com.dominio.escola.service.DisciplinaService;
import br.com.dominio.escola.service.exceptions.DataIntegrityException;

@Controller
@RequestMapping("/disciplinas")
public class DisciplinaController {
	
	private static final String VIEW_LIST = "/disciplina/lista";
	private static final String VIEW_FORM = "/disciplina/cadastro";
	
	@Autowired
	private DisciplinaService service;

	@Autowired
	private Cursos repo;

	@GetMapping("/cadastrar")
	public ModelAndView cadastrar(Disciplina registro) {
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
	public ModelAndView salvar(@Valid Disciplina registro, BindingResult result, RedirectAttributes attr) {
		
		if (result.hasErrors()) {
			return cadastrar(registro);
		}
		
		service.insert(registro);
		attr.addFlashAttribute("success", "Registro inserido com sucesso.");
		return new ModelAndView("redirect:/disciplinas/cadastrar");
	}
	
	@GetMapping("{id}")
	public ModelAndView preEditar(@PathVariable("id") Disciplina disciplina) {
		ModelAndView mv = cadastrar(disciplina);
		mv.addObject(disciplina);
		return mv;
	}
	
	@PostMapping("/editar")
	public ModelAndView editar(@Valid Disciplina registro, BindingResult result, RedirectAttributes attr) {
		
		if (result.hasErrors()) {
			return cadastrar(registro);
		}
		
		service.update(registro);
		attr.addFlashAttribute("success", "Registro alterado com sucesso.");
		return new ModelAndView("redirect:/disciplinas/cadastrar");
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
	
	@ModelAttribute("cursos")
	public List<Curso> getCursos() {
		return repo.findAll();
	}
			
}
