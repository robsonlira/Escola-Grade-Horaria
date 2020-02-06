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

import br.com.dominio.escola.model.Disciplina;
import br.com.dominio.escola.repository.Disciplinas;
import br.com.dominio.escola.service.exceptions.DataIntegrityException;
import br.com.dominio.escola.service.exceptions.ObjectNotFoundException;

@Service
public class DisciplinaService {
		
	@Autowired
	private Disciplinas repo;
	
	public Disciplina find(Integer id) {
		Optional<Disciplina> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! Id: " + id + ", Tipo: " + Disciplina.class.getName()));
	}
	
	@Transactional
	public Disciplina insert(Disciplina obj) {
		obj.setId(null);
		try {
			obj = repo.save(obj);	
		} catch ( DataIntegrityViolationException e) {
			throw new DataIntegrityException("Violação de integridade de dados " + e.getMessage());	
		}

		return obj;
	}

	public Disciplina update(Disciplina obj) {
		Disciplina newObj = find(obj.getId());
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
	
	public List<Disciplina> findAll() {
		return repo.findAll();
	}
	
	public Page<Disciplina> findPage(Integer page, Integer linesPerPage, String orderBy, String direction) {
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
		return repo.findAll(pageRequest);
	}
	
	
}
