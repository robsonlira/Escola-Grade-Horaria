package br.com.dominio.escola.controller;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.com.dominio.escola.model.Horario;
import br.com.dominio.escola.model.Turno;
import br.com.dominio.escola.model.pojo.TabelaHorario;
import br.com.dominio.escola.service.HorarioService;
import br.com.dominio.escola.service.TurnoService;
import br.com.dominio.escola.service.exceptions.DataIntegrityException;
import br.com.dominio.escola.service.exceptions.RegistroJaCadastradoException;

@Controller
@RequestMapping("/turnos")
public class TurnoController {
	
	private static Logger log = LoggerFactory.getLogger(TurnoController.class);
	
	private static final String VIEW_LIST = "/turno/lista";
	private static final String VIEW_FORM = "/turno/cadastro";
	
	@Autowired
	private TurnoService service;
	
	@Autowired
	private HorarioService horarioService;	

	@GetMapping("/cadastrar")
	public ModelAndView cadastrar(Turno turno) {
		ModelAndView mv = new ModelAndView(VIEW_FORM);
		mv.addObject("itens", horarioService.findByTurno(0));		
		return mv;
	}
	
	@GetMapping
	public ModelAndView listar() {
		ModelAndView mv = new ModelAndView(VIEW_LIST);
		mv.addObject("itens", service.findAll());
		return mv;
	}
	
	@PostMapping("/salvar")
	public ModelAndView salvar(@Valid Turno registro, BindingResult result, RedirectAttributes attr) {
		
		if (result.hasErrors()) {
			return cadastrar(registro);
		}
		
		try {
		   service.insert(registro);
		} catch (RegistroJaCadastradoException e) {
			result.rejectValue("nome", e.getMessage(), e.getMessage());
			return cadastrar(registro);
		}
		
		attr.addFlashAttribute("success", "Registro inserido com sucesso.");
		return new ModelAndView("redirect:/turnos/cadastrar");
	}
	
	@GetMapping("{id}")
	public ModelAndView preEditar(@PathVariable("id") Turno turno) {
		
		ModelAndView mv = cadastrar(turno);		
		mv.addObject(turno);
		mv.addObject("itens", horarioService.findByTurno(turno.getId()));		
		
		return mv;
	}
			
	@PostMapping("/editar")
	public ModelAndView editar(@Valid Turno registro, BindingResult result, RedirectAttributes attr) {
		
		if (result.hasErrors()) {
			return cadastrar(registro);
		}
		
		service.update(registro);
		
		attr.addFlashAttribute("success", "Registro atualizado com sucesso.");
		return new ModelAndView("redirect:/turnos/cadastrar");		
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
	
/*	
	@PostMapping("/{id}/item")
	public ModelAndView adicionarHorario(Horario horario, @PathVariable("id") Turno turno) {
		ModelAndView mv = new ModelAndView("/turno/tabelaHorarios");
		horario.setTurno(turno);	
		
		horario = horarioService.insert(horario);
				
		mv.addObject("itens", horarioService.findByTurno(turno.getId()));						
		return mv;
	}*/	

	@PostMapping("/{id}/item")
	public ResponseEntity<?> adicionarHorario(Horario horario, @PathVariable("id") Turno turno) {
		
		TabelaHorario tabelaHorario = new TabelaHorario();		
		horario.setTurno(turno);	
				
		
		try {
			horario = horarioService.insert(horario);			
		} catch (DataIntegrityException e) {
			tabelaHorario.setMsg(e.getMessage());
			return ResponseEntity.status(500).body(tabelaHorario);
		} catch (Exception e) {			
			tabelaHorario.setMsg(e.getMessage());
			return ResponseEntity.badRequest().body(tabelaHorario);			
		}
			
		tabelaHorario.setMsg("success");				
		tabelaHorario.setResult(horarioService.findByTurno(turno.getId()));		 
        return ResponseEntity.ok(tabelaHorario);
	}	
	
	
}
