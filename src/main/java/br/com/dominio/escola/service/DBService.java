package br.com.dominio.escola.service;

import java.math.BigDecimal;
import java.text.ParseException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.dominio.escola.model.AgConfiguracao;
import br.com.dominio.escola.model.Cargo;
import br.com.dominio.escola.model.Curso;
import br.com.dominio.escola.model.Departamento;
import br.com.dominio.escola.model.Disciplina;
import br.com.dominio.escola.model.GradeHoraria;
import br.com.dominio.escola.model.Horario;
import br.com.dominio.escola.model.HorarioProfessor;
import br.com.dominio.escola.model.Professor;
import br.com.dominio.escola.model.Turno;
import br.com.dominio.escola.repository.AgConfiguracoes;
import br.com.dominio.escola.repository.Cargos;
import br.com.dominio.escola.repository.Cursos;
import br.com.dominio.escola.repository.Departamentos;
import br.com.dominio.escola.repository.Disciplinas;
import br.com.dominio.escola.repository.GradesHorarias;
import br.com.dominio.escola.repository.Horarios;
import br.com.dominio.escola.repository.HorariosProfessores;
import br.com.dominio.escola.repository.Professores;
import br.com.dominio.escola.repository.Turnos;



@Service
public class DBService {
	
	@Autowired
	Cargos cargos;
	
	@Autowired
	Cursos cursos;

	@Autowired
	Turnos turnos;

	@Autowired
	Horarios horarios;

	@Autowired
	GradesHorarias gradesHorarias;	
	
	@Autowired
	Disciplinas disciplinas;
	
	@Autowired
	Professores professores;

	@Autowired
	Departamentos departamentos;
	
	@Autowired
	HorariosProfessores horariosProfessores;
	
	@Autowired
	AgConfiguracoes agConfiguracoes;
	
	
	public void instantiateTestDatabase() throws ParseException {
		
		AgConfiguracao agConfig = new AgConfiguracao(null, "Configuração Padrão", true);
		agConfig.setGrauDesejavelAceitavel(1);
		agConfig.setMutacao(1);
		agConfig.setMutacoes(1);
		agConfig.setNumeroGeracoes(50);
		agConfig.setRemoveIguais(1);
		agConfig.setRemovePiores(1);
		agConfig.setTaxaCorte(2);
		agConfig.setTaxaMutacao(2);	
		agConfig.setTentativas(10);
		agConfig.setTamanhoPopulacao(50);
		
		agConfiguracoes.save(agConfig);
		
		GradeHoraria gh = new GradeHoraria();
		gh.setNome("Grade 2020/1");
		gh.setAno(2020);
		gh.setDataCriacao(LocalDateTime.parse("2020-01-02T10:24:10"));
		gh.setDataLimite(LocalDate.parse("2020-10-30"));
		
		Curso curso = new Curso(null, "Quimica Industrial", 4);
		
		Turno turno1 = new Turno(null, "Manha", "M");
		Turno turno2 = new Turno(null, "Tarde", "T");
		Turno turno3 = new Turno(null, "Noite", "N");
		
		Horario hr1 = new Horario(null,"07:30 - 08:20", turno1);
		Horario hr2 = new Horario(null,"08:20 - 09:10", turno1);
		Horario hr3 = new Horario(null,"09:30 - 10:20", turno1);
		Horario hr4 = new Horario(null,"10:20 - 11:10", turno1);
		Horario hr5 = new Horario(null,"11:10 - 12:00", turno1);		

		Horario hr6 = new Horario(null,"14:00 - 14:50", turno2);		
		Horario hr7 = new Horario(null,"14:50 - 15:40", turno2);		
		Horario hr8 = new Horario(null,"16:00 - 16:50", turno2);		
		Horario hr9 = new Horario(null,"16:50 - 17:40", turno2);		
		Horario hr10= new Horario(null,"17:40 - 18:30", turno2);		

		Professor prof1 = new Professor(null,"Mario");
		Professor prof2 = new Professor(null,"Veronica");

		Disciplina disc1 = new Disciplina(null, "Quimica Organica", "QO", curso);
		Disciplina disc2 = new Disciplina(null, "Quimica Inorganica", "QI", curso);
		Disciplina disc3 = new Disciplina(null, "Corrosão", "CO", curso);
		
		disc1.setNumeroSemestres(4);
		disc1.setNumeroCreditos(6);
		
		disc2.setNumeroSemestres(4);
		disc2.setNumeroCreditos(6);
		
		disc3.setNumeroSemestres(4);
		disc3.setNumeroCreditos(6);
		
		disc1.getProfessores().addAll(Arrays.asList(prof1));
		disc2.getProfessores().addAll(Arrays.asList(prof1));
		disc3.getProfessores().addAll(Arrays.asList(prof2));

		prof1.getDisciplinas().addAll(Arrays.asList(disc1, disc2));
		prof2.getDisciplinas().addAll(Arrays.asList(disc3));				
		
		Departamento dep1 = new Departamento(null,"Administração");
		Departamento dep2 = new Departamento(null,"Contabilidade");
		Departamento dep3 = new Departamento(null,"Recursos Humanos");
		Departamento dep4 = new Departamento(null,"Tecnologia da Informação");

		Cargo c1 = new Cargo(null, "Aux. Contabil", dep2);
		Cargo c2 = new Cargo(null, "Analista RH", dep3);
		Cargo c3 = new Cargo(null, "Programador Junior", dep4);
		
		gradesHorarias.save(gh);
		
		departamentos.saveAll(Arrays.asList(dep1, dep2, dep3, dep4));
		cargos.saveAll(Arrays.asList(c1, c2, c3));
		
		cursos.save(curso);
		turnos.saveAll(Arrays.asList(turno1, turno2, turno3));
		horarios.saveAll(Arrays.asList(hr1,hr2,hr3,hr4,hr5,hr6,hr7,hr8,hr9,hr10));
		disciplinas.saveAll(Arrays.asList(disc1, disc2, disc3));
		professores.saveAll(Arrays.asList(prof1, prof2));
		
		HorarioProfessor hp11 = new HorarioProfessor(prof1, hr1, gh, 1, 0, 1, 1, 0, 0);
		HorarioProfessor hp12 = new HorarioProfessor(prof1, hr2, gh, 1, 0, 1, 1, 1, 0);
		HorarioProfessor hp13 = new HorarioProfessor(prof1, hr3, gh, 0, 1, 1, 1, 1, 0);
		HorarioProfessor hp14 = new HorarioProfessor(prof1, hr4, gh, 0, 1, 1, 0, 1, 0);
		HorarioProfessor hp15 = new HorarioProfessor(prof1, hr5, gh, 0, 1, 1, 0, 1, 0);

		HorarioProfessor hp21 = new HorarioProfessor(prof2, hr1, gh, 0, 0, 0, 0, 0, 0);
		HorarioProfessor hp22 = new HorarioProfessor(prof2, hr2, gh, 0, 0, 0, 0, 0, 0);
		HorarioProfessor hp23 = new HorarioProfessor(prof2, hr3, gh, 0, 0, 0, 0, 1, 0);
		HorarioProfessor hp24 = new HorarioProfessor(prof2, hr4, gh, 0, 0, 0, 0, 0, 0);
		HorarioProfessor hp25 = new HorarioProfessor(prof2, hr5, gh, 0, 1, 0, 0, 0, 0);
		
		prof1.getHorariosProfessor().addAll(Arrays.asList(hp11, hp12, hp13, hp14, hp15));
		prof2.getHorariosProfessor().addAll(Arrays.asList(hp21, hp22, hp23, hp24, hp25));
		
		hr1.getHorariosProfessor().addAll(Arrays.asList(hp11,hp21));
		hr2.getHorariosProfessor().addAll(Arrays.asList(hp12,hp22));
		hr3.getHorariosProfessor().addAll(Arrays.asList(hp13,hp23));
		hr4.getHorariosProfessor().addAll(Arrays.asList(hp14,hp24));
		hr5.getHorariosProfessor().addAll(Arrays.asList(hp15,hp25));
		gh.getHorariosProfessor().addAll(Arrays.asList(hp11,hp12,hp13,hp14,hp15,hp21,hp22,hp23,hp24,hp25));
		
		horariosProfessores.saveAll(Arrays.asList(hp11,hp12,hp13,hp14,hp15,hp21,hp22,hp23,hp24,hp25));
						
		
	}

}
