package com.projetocursos.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.projetocursos.entities.Cursos;
import com.projetocursos.service.CursosService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@Tag(name = "Cursos", description = "API REST DE GERENCIAMENTO DO Cursos")
@RestController
@RequestMapping("/cursos")
@CrossOrigin(origins = "*")
public class CursosController {

	private final CursosService CursosService;

	@Autowired
	public CursosController(CursosService CursosService) {
		this.CursosService = CursosService;
	}

	@GetMapping("/{id}")
	@Operation(summary = "Localiza Cursos por ID")
	public ResponseEntity<Cursos> buscaCursosControlId(@PathVariable Long id) {
		Cursos Cursos = CursosService.getCursosById(id);
		if (Cursos != null) {
			return ResponseEntity.ok(Cursos);
		} else {
			return ResponseEntity.notFound().build();
		}
	}

	@GetMapping
	@Operation(summary = "apresenta todos os Cursoss")
	public ResponseEntity<List<Cursos>> buscaTodasLigacoesControl() {
		List<Cursos> Cursos = CursosService.getAllCursoss();
		return ResponseEntity.ok(Cursos);
	}

	@PostMapping
	@Operation(summary = "cadastra os Cursoss")
	public ResponseEntity<Cursos> saveCursosControl(@RequestBody @Valid Cursos Cursos) {
		Cursos saveCursos = CursosService.saveCursos(Cursos);
		return ResponseEntity.status(HttpStatus.CREATED).body(saveCursos);
	}

	@PutMapping("/{id}")
	@Operation(summary = "altera os Cursoss")
	public ResponseEntity<Cursos> alteraCursosControl(@PathVariable Long id, @RequestBody @Valid Cursos Cursos) {
		Cursos alteraCursos = CursosService.changeCursos(id, Cursos);

		if (alteraCursos != null) {
			return ResponseEntity.ok(Cursos);
		} else {
			return ResponseEntity.notFound().build();
		}
	}

	@DeleteMapping("/{id}")
	@Operation(summary = "deleta os Cursoss")
	public ResponseEntity<String> deleteCursosControl(@PathVariable Long id) {
		boolean delete = CursosService.deleteCursos(id);
		if (delete) {
			return ResponseEntity.ok().body("O produto foi excluido com o sucesso");
		} else {
			return ResponseEntity.notFound().build();
		}

	}

}