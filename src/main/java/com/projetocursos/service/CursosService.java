package com.projetocursos.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.projetocursos.entities.Cursos;
import com.projetocursos.repository.CursosRepository;

@Service
public class CursosService {
	private final CursosRepository CursosRepository;
	

	@Autowired
	public CursosService(CursosRepository CursosRepository) {
		this.CursosRepository = CursosRepository;
	}

	public List<Cursos> getAllCursoss() {
		return CursosRepository.findAll();
	}

	public Cursos getCursosById(Long id) {
		Optional<Cursos> Cursos = CursosRepository.findById(id);
		return Cursos.orElse(null);
	}

	public Cursos saveCursos(Cursos Cursos) {
		return CursosRepository.save(Cursos);
	}

	public Cursos changeCursos(Long id, Cursos changeU) {
		Optional<Cursos> existeCursos = CursosRepository.findById(id);
		if (existeCursos.isPresent()) {
			changeU.setId(id);
			return CursosRepository.save(changeU);
		}
		return null;
	}

	public boolean deleteCursos(Long id) {
		Optional<Cursos> existeCursos= CursosRepository.findById(id);
		if (existeCursos.isPresent()) {
			CursosRepository.deleteById(id);
			return true;
		}
		return false;
	}

}
