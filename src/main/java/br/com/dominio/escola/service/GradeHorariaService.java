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

import br.com.dominio.escola.model.GradeHoraria;
import br.com.dominio.escola.repository.GradesHorarias;
import br.com.dominio.escola.service.exceptions.DataIntegrityException;
import br.com.dominio.escola.service.exceptions.ObjectNotFoundException;

@Service
public class GradeHorariaService {
		
	@Autowired
	private GradesHorarias repo;
	
	public GradeHoraria find(Integer id) {
		Optional<GradeHoraria> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! Id: " + id + ", Tipo: " + GradeHoraria.class.getName()));
	}
	
	@Transactional
	public GradeHoraria insert(GradeHoraria obj) {
		obj.setId(null);
		obj = repo.save(obj);
		return obj;
	}

	public GradeHoraria update(GradeHoraria obj) {
		
		if (obj.getId()==null) {
			return insert(obj);
		}
		
		GradeHoraria newObj = find(obj.getId());
		
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
	
	public List<GradeHoraria> findAll() {
		return repo.findAll();
	}
	
	public Page<GradeHoraria> findPage(Integer page, Integer linesPerPage, String orderBy, String direction) {
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
		return repo.findAll(pageRequest);
	}
	
	
}
