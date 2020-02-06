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

import br.com.dominio.escola.model.Curso;
import br.com.dominio.escola.repository.Cursos;
import br.com.dominio.escola.service.exceptions.DataIntegrityException;
import br.com.dominio.escola.service.exceptions.ObjectNotFoundException;

@Service
public class CursoService {
		
	@Autowired
	private Cursos repo;
	
	public Curso find(Integer id) {
		Optional<Curso> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! Id: " + id + ", Tipo: " + Curso.class.getName()));
	}
	
	@Transactional
	public Curso insert(Curso obj) {
		obj.setId(null);
		try {
			obj = repo.save(obj);	
		} catch ( DataIntegrityViolationException e) {
			throw new DataIntegrityException("Violação de integridade de dados " + e.getMessage());	
		}

		return obj;
	}

	public Curso update(Curso obj) {
		Curso newObj = find(obj.getId());
		newObj.setNome(obj.getNome());
		
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
	
	public List<Curso> findAll() {
		return repo.findAll();
	}
	
	public Page<Curso> findPage(Integer page, Integer linesPerPage, String orderBy, String direction) {
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
		return repo.findAll(pageRequest);
	}
	
	
}
