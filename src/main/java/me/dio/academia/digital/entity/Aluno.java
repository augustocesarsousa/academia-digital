package me.dio.academia.digital.entity;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "tb_aluno")
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
public class Aluno implements Serializable {

  private static final long serialVersionUID = 1L;

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private String nome;

  @Column(unique = true)
  private String cpf;

  private String bairro;

  private LocalDate dataDeNascimento;

  @OneToMany(
    mappedBy = "aluno",
    cascade = CascadeType.REMOVE,
    fetch = FetchType.LAZY
  )
  @JsonIgnore
  private List<AvaliacaoFisica> avaliacoes = new ArrayList<>();

  public Aluno() {}

  public Aluno(
    Long id,
    String nome,
    String cpf,
    String bairro,
    LocalDate dataDeNascimento
  ) {
    this.id = id;
    this.nome = nome;
    this.cpf = cpf;
    this.bairro = bairro;
    this.dataDeNascimento = dataDeNascimento;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getNome() {
    return nome;
  }

  public void setNome(String nome) {
    this.nome = nome;
  }

  public String getCpf() {
    return cpf;
  }

  public void setCpf(String cpf) {
    this.cpf = cpf;
  }

  public String getBairro() {
    return bairro;
  }

  public void setBairro(String bairro) {
    this.bairro = bairro;
  }

  public LocalDate getDataDeNascimento() {
    return dataDeNascimento;
  }

  public void setDataDeNascimento(LocalDate dataDeNascimento) {
    this.dataDeNascimento = dataDeNascimento;
  }

  public List<AvaliacaoFisica> getAvaliacoes() {
    return avaliacoes;
  }
}
