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

import br.com.dominio.escola.model.Departamento;
import br.com.dominio.escola.repository.Departamentos;
import br.com.dominio.escola.service.exceptions.DataIntegrityException;
import br.com.dominio.escola.service.exceptions.ObjectNotFoundException;

@Service
public class DepartamentoService {
		
	@Autowired
	private Departamentos repo;
	
	public Departamento find(Integer id) {
		Optional<Departamento> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! Id: " + id + ", Tipo: " + Departamento.class.getName()));
	}
	
	@Transactional
	public Departamento insert(Departamento obj) {
		obj.setId(null);
		obj = repo.save(obj);
		return obj;
	}

	public Departamento update(Departamento obj) {
		Departamento newObj = find(obj.getId());
		newObj.setNome(obj.getNome());
		return repo.save(newObj);
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
	
	public List<Departamento> findAll() {
		return repo.findAll();
	}
	
	public Page<Departamento> findPage(Integer page, Integer linesPerPage, String orderBy, String direction) {
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
		return repo.findAll(pageRequest);
	}
	
	
}
