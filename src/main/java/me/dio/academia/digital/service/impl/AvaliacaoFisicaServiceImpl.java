package me.dio.academia.digital.service.impl;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import me.dio.academia.digital.entity.AvaliacaoFisica;
import me.dio.academia.digital.entity.form.AvaliacaoFisicaForm;
import me.dio.academia.digital.entity.form.AvaliacaoFisicaUpdateForm;
import me.dio.academia.digital.repository.AlunoRepository;
import me.dio.academia.digital.repository.AvaliacaoFisicaRepository;
import me.dio.academia.digital.service.IAvaliacaoFisicaService;
import me.dio.academia.digital.service.exceptions.DataBaseException;
import me.dio.academia.digital.service.exceptions.ResourceNotFoundException;

@Service
public class AvaliacaoFisicaServiceImpl implements IAvaliacaoFisicaService {

  @Autowired
  private AvaliacaoFisicaRepository repository;

  @Autowired
  private AlunoRepository alunoRepository;

  @Override
  public AvaliacaoFisica create(AvaliacaoFisicaForm form) {
    AvaliacaoFisica avaliacaoFisica = new AvaliacaoFisica();
    avaliacaoFisica.setAluno(alunoRepository.findById(form.getAlunoId()).get());
    avaliacaoFisica.setAltura(form.getAltura());
    avaliacaoFisica.setPeso(form.getPeso());
    return repository.save(avaliacaoFisica);
  }

  @Override
  public AvaliacaoFisica findById(Long id) {
    Optional<AvaliacaoFisica> obj = repository.findById(id);
    AvaliacaoFisica avaliacaoFisica = obj.orElseThrow(
      () -> new ResourceNotFoundException("Avaliação não encontrada!")
    );
    return avaliacaoFisica;
  }

  @Override
  public List<AvaliacaoFisica> findAll() {
    return repository.findAll();
  }

  @Override
  public AvaliacaoFisica update(Long id, AvaliacaoFisicaUpdateForm formUpdate) {
    try {
      AvaliacaoFisica avaliacaoFisica = repository.getById(id);
      avaliacaoFisica.setPeso(formUpdate.getPeso());
      avaliacaoFisica.setAltura(formUpdate.getAltura());
      return avaliacaoFisica = repository.save(avaliacaoFisica);
    } catch (EntityNotFoundException e) {
      throw new ResourceNotFoundException("Id not found = " + id);
    }
  }

  @Override
  public void delete(Long id) {
    try {
      repository.deleteById(id);
    } catch (EmptyResultDataAccessException e) {
      throw new ResourceNotFoundException("Id not found = " + id);
    } catch (DataIntegrityViolationException e) {
      throw new DataBaseException("Integrity violation");
    }
  }
}
