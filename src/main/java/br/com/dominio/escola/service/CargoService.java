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

import br.com.dominio.escola.model.Cargo;
import br.com.dominio.escola.repository.Cargos;
import br.com.dominio.escola.service.exceptions.DataIntegrityException;
import br.com.dominio.escola.service.exceptions.ObjectNotFoundException;

@Service
public class CargoService {
		
	@Autowired
	private Cargos repo;
	
	public Cargo find(Integer id) {
		Optional<Cargo> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! Id: " + id + ", Tipo: " + Cargo.class.getName()));
	}
	
	@Transactional
	public Cargo insert(Cargo obj) {
		obj.setId(null);

		try {
			obj = repo.save(obj);	
		} catch ( DataIntegrityViolationException e) {
			throw new DataIntegrityException("Violação de integridade de dados " + e.getMessage());	
		}

		return obj;
	}

	public Cargo update(Cargo obj) {
		Cargo newObj = find(obj.getId());
		newObj.setNome(obj.getNome());
		newObj.setDepartamento(obj.getDepartamento());

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
	
	public List<Cargo> findAll() {
		return repo.findAll();
	}
	
	public Page<Cargo> findPage(Integer page, Integer linesPerPage, String orderBy, String direction) {
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
		return repo.findAll(pageRequest);
	}
	
	
}
