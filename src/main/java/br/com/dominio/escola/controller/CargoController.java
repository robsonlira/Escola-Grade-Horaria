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

import br.com.dominio.escola.model.Cargo;
import br.com.dominio.escola.model.Departamento;
import br.com.dominio.escola.service.CargoService;
import br.com.dominio.escola.service.DepartamentoService;
import br.com.dominio.escola.service.exceptions.DataIntegrityException;

@Controller
@RequestMapping("/cargos")
public class CargoController {
	
	private static final String VIEW_LIST = "/cargo/lista";
	private static final String VIEW_FORM = "/cargo/cadastro";
	
	@Autowired
	private CargoService service;
	@Autowired
	private DepartamentoService departamentoService;

	@GetMapping("/cadastrar")
	public ModelAndView cadastrar(Cargo registro) {
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
	public ModelAndView salvar(@Valid Cargo registro, BindingResult result, RedirectAttributes attr) {
		
		if (result.hasErrors()) {
			return cadastrar(registro);
		}
				
		service.insert(registro);
		attr.addFlashAttribute("success", "Registro inserido com sucesso.");
		return new ModelAndView("redirect:/cargos/cadastrar");		
	}
	
	@GetMapping("{id}")
	public ModelAndView preEditar(@PathVariable("id") Cargo cargo) {
		ModelAndView mv = cadastrar(cargo);
		mv.addObject(cargo);
		return mv;
	}

	
	@PostMapping("/editar")
	public ModelAndView editar(@Valid Cargo registro, BindingResult result, RedirectAttributes attr) {
		
		if (result.hasErrors()) {
			return cadastrar(registro);
		}	
		
		service.update(registro);
		attr.addFlashAttribute("success", "Registro atualizado com sucesso.");
		return new ModelAndView("redirect:/cargos/cadastrar");
	}
	
	
	@DeleteMapping("/{codigo}")
	public @ResponseBody ResponseEntity<?> excluir(@PathVariable("codigo") Integer id) {
		try {
			service.delete(id);
		} catch (DataIntegrityException e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
		return ResponseEntity.ok().build();
	}	
	
	@ModelAttribute("departamentos")
	public List<Departamento> listaDeDepartamentos() {
		return departamentoService.findAll();
	}	
}
