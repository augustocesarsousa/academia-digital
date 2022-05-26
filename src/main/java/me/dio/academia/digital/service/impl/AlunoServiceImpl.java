package me.dio.academia.digital.service.impl;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import me.dio.academia.digital.entity.Aluno;
import me.dio.academia.digital.entity.AvaliacaoFisica;
import me.dio.academia.digital.entity.form.AlunoForm;
import me.dio.academia.digital.entity.form.AlunoUpdateForm;
import me.dio.academia.digital.infra.utils.JavaTimeUtils;
import me.dio.academia.digital.repository.AlunoRepository;
import me.dio.academia.digital.service.IAlunoService;
import me.dio.academia.digital.service.exceptions.DataBaseException;
import me.dio.academia.digital.service.exceptions.ResourceNotFoundException;

@Service
public class AlunoServiceImpl implements IAlunoService {

  @Autowired
  private AlunoRepository repository;

  @Override
  public Aluno create(AlunoForm form) {
    Aluno aluno = new Aluno();
    aluno.setNome(form.getNome());
    aluno.setCpf(form.getCpf());
    aluno.setBairro(form.getBairro());
    aluno.setDataDeNascimento(form.getDataDeNascimento());

    return repository.save(aluno);
  }

  @Override
  public Aluno get(Long id) {
    Optional<Aluno> obj = repository.findById(id);
    Aluno aluno = obj.orElseThrow(
      () -> new ResourceNotFoundException("Aluno não encotrado!")
    );
    return aluno;
  }

  @Override
  public List<Aluno> getAll(String dataDeNascimento) {
    if (dataDeNascimento == null) {
      return repository.findAll();
    } else {
      LocalDate localDate = LocalDate.parse(
        dataDeNascimento,
        JavaTimeUtils.LOCAL_DATE_FORMATTER
      );
      return repository.findByDataDeNascimento(localDate);
    }
  }

  @Override
  public Aluno update(Long id, AlunoUpdateForm formUpdate) {
    try {
      Aluno aluno = repository.getById(id);
      aluno.setNome(formUpdate.getNome());
      aluno.setBairro(formUpdate.getBairro());
      aluno.setDataDeNascimento(formUpdate.getDataDeNascimento());
      return aluno = repository.save(aluno);
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

  @Override
  public List<AvaliacaoFisica> getAllAvaliacaoFisicaId(Long id) {
    Aluno aluno = repository.findById(id).get();
    return aluno.getAvaliacoes();
  }
}
