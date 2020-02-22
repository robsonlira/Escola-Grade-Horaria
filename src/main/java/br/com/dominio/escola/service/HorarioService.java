package br.com.dominio.escola.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.dominio.escola.model.Horario;
import br.com.dominio.escola.repository.Horarios;
import br.com.dominio.escola.service.exceptions.DataIntegrityException;
import br.com.dominio.escola.service.exceptions.ObjectNotFoundException;
import br.com.dominio.escola.service.exceptions.RegistroJaCadastradoException;

@Service
public class HorarioService {
		
	@Autowired
	private Horarios repo;
	
	public Horario find(Integer id) {
		Optional<Horario> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! Id: " + id + ", Tipo: " + Horario.class.getName()));
	}
	
	@Transactional
	public Horario insert(Horario obj) {
		obj.setId(null);

		try {
			obj = repo.save(obj);	
		} catch ( DataIntegrityViolationException e) {
			throw new DataIntegrityException("Violação de integridade de dados.");	
		}

		return obj;
	}

	public Horario update(Horario obj) {
		Horario newObj = find(obj.getId());
		newObj.setNome(obj.getNome());
		newObj.setTurno(obj.getTurno());

		try {
			obj = repo.save(newObj);	
		} catch ( DataIntegrityViolationException e) {
			throw new DataIntegrityException("Violação de integridade de dados.");	
		}
		
		return obj;		
	}

	public void delete(Integer id) {
		find(id);
		try {
			repo.deleteById(id);
		}
		catch (DataIntegrityViolationException e) {
			throw new DataIntegrityException("Não é possível excluir porque há dados relacionados");
		}
	}	
	
	public List<Horario> findAll() {
		return repo.findAll();
	}
	
	public List<Horario> findByTurno(Integer turnoId) {
		return repo.findHorarios(turnoId);
	}
	
	public Page<Horario> findPage(Integer page, Integer linesPerPage, String orderBy, String direction) {
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
		return repo.findAll(pageRequest);
	}		
}
