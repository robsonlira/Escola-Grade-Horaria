package br.com.dominio.escola.controller;

import java.util.Arrays;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
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
import br.com.dominio.escola.model.Funcionario;
import br.com.dominio.escola.model.enums.UF;
import br.com.dominio.escola.service.CargoService;
import br.com.dominio.escola.service.FuncionarioService;
import br.com.dominio.escola.service.exceptions.DataIntegrityException;

@Controller
@RequestMapping("/funcionarios")
public class FuncionarioController {
	
	private static final String VIEW_LIST = "/funcionario/lista";
	private static final String VIEW_FORM = "/funcionario/cadastro";
	
	@Autowired
	private CargoService cargoService;
	
	@Autowired	
	private FuncionarioService service;

	@GetMapping("/cadastrar")
	public ModelAndView cadastrar(Funcionario registro) {
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
	public ModelAndView salvar(@Valid Funcionario registro, BindingResult result, RedirectAttributes attr) {
		
		if (result.hasErrors()) {
			return cadastrar(registro);
		}
				
		service.insert(registro);
		attr.addFlashAttribute("success", "Registro inserido com sucesso.");
		return new ModelAndView("redirect:/cargos/cadastrar");		
	}
	
	@GetMapping("{id}")
	public ModelAndView preEditar(@PathVariable("id") Funcionario funcionario) {
		ModelAndView mv = cadastrar(funcionario);
		mv.addObject(funcionario);
		return mv;
	}
	
	@PostMapping("/editar")
	public ModelAndView editar(@Valid Funcionario registro, BindingResult result, RedirectAttributes attr) {
		
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
		
	@ModelAttribute("cargos")
	public List<Cargo> getCargos() {
		return cargoService.findAll();
	}
	
	@ModelAttribute("ufs")
	public List<UF> todasUF() {
		return Arrays.asList(UF.values());
	}
			
}
