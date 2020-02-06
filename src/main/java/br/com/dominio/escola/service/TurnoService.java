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
import br.com.dominio.escola.model.Turno;
import br.com.dominio.escola.repository.Turnos;
import br.com.dominio.escola.service.exceptions.DataIntegrityException;
import br.com.dominio.escola.service.exceptions.ObjectNotFoundException;
import br.com.dominio.escola.service.exceptions.RegistroJaCadastradoException;

@Service
public class TurnoService {
		
	@Autowired
	private Turnos repo;
	
	public Turno find(Integer id) {
		Optional<Turno> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! Id: " + id + ", Tipo: " + Turno.class.getName()));
	}
	
	@Transactional
	public Turno insert(Turno obj) {
		obj.setId(null);
		
		Optional<Turno> optional = repo.findByNomeIgnoreCase(obj.getNome());
		if (optional.isPresent()) {
			throw new RegistroJaCadastradoException("Registro já existe");
		}		
		
		return repo.save(obj);
	}

	public Turno update(Turno obj) {
		Turno newObj = find(obj.getId());
		newObj.setNome(obj.getNome());
		newObj.setAbrev(obj.getAbrev());
		
		try {
			obj = repo.save(newObj);	
		} catch ( DataIntegrityViolationException e) {
			throw new DataIntegrityException("Violação de integridade de dados " + e.getMessage());	
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
	
	public List<Turno> findAll() {
		return repo.findAll();
	}
	
	public Page<Turno> findPage(Integer page, Integer linesPerPage, String orderBy, String direction) {
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
		return repo.findAll(pageRequest);
	}
	
	
}
