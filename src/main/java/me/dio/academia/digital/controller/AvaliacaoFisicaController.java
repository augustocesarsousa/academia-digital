package me.dio.academia.digital.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import me.dio.academia.digital.entity.AvaliacaoFisica;
import me.dio.academia.digital.entity.form.AvaliacaoFisicaForm;
import me.dio.academia.digital.entity.form.AvaliacaoFisicaUpdateForm;
import me.dio.academia.digital.service.impl.AvaliacaoFisicaServiceImpl;

@RestController
@RequestMapping("/avaliacoes")
public class AvaliacaoFisicaController {

  @Autowired
  private AvaliacaoFisicaServiceImpl service;

  @PostMapping
  public AvaliacaoFisica create(@RequestBody AvaliacaoFisicaForm form) {
    return service.create(form);
  }

  @GetMapping(value = "/{id}")
  public ResponseEntity<AvaliacaoFisica> findById(@PathVariable Long id) {
    AvaliacaoFisica avaliacaoFisica = service.findById(id);
    return ResponseEntity.ok().body(avaliacaoFisica);
  }

  @GetMapping
  public List<AvaliacaoFisica> findAll() {
    return service.findAll();
  }

  @PutMapping(value = "/{id}")
  public ResponseEntity<AvaliacaoFisica> update(
    @PathVariable Long id,
    @Valid @RequestBody AvaliacaoFisicaUpdateForm formUpdate
  ) {
    AvaliacaoFisica avaliacaoFisica = service.update(id, formUpdate);
    return ResponseEntity.ok().body(avaliacaoFisica);
  }

  @DeleteMapping(value = "/{id}")
  public ResponseEntity<AvaliacaoFisica> delete(@PathVariable Long id) {
    service.delete(id);
    return ResponseEntity.noContent().build();
  }
}
