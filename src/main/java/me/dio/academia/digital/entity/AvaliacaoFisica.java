package me.dio.academia.digital.entity;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "tb_avaliacao")
public class AvaliacaoFisica implements Serializable {

  private static final long serialVersionUID = 1L;

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @ManyToOne(cascade = CascadeType.ALL)
  @JoinColumn(name = "aluno_id")
  private Aluno aluno;

  private LocalDateTime dataDaAvaliacao = LocalDateTime.now();

  @Column(name = "peso_atual")
  private double peso;

  @Column(name = "altura_atual")
  private double altura;

  public AvaliacaoFisica() {}

  public AvaliacaoFisica(
    Long id,
    Aluno aluno,
    LocalDateTime dataDaAvaliacao,
    double peso,
    double altura
  ) {
    this.id = id;
    this.aluno = aluno;
    this.dataDaAvaliacao = dataDaAvaliacao;
    this.peso = peso;
    this.altura = altura;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public Aluno getAluno() {
    return aluno;
  }

  public void setAluno(Aluno aluno) {
    this.aluno = aluno;
  }

  public LocalDateTime getDataDaAvaliacao() {
    return dataDaAvaliacao;
  }

  public void setDataDaAvaliacao(LocalDateTime dataDaAvaliacao) {
    this.dataDaAvaliacao = dataDaAvaliacao;
  }

  public double getPeso() {
    return peso;
  }

  public void setPeso(double peso) {
    this.peso = peso;
  }

  public double getAltura() {
    return altura;
  }

  public void setAltura(double altura) {
    this.altura = altura;
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((id == null) ? 0 : id.hashCode());
    return result;
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj) return true;
    if (obj == null) return false;
    if (getClass() != obj.getClass()) return false;
    AvaliacaoFisica other = (AvaliacaoFisica) obj;
    if (id == null) {
      if (other.id != null) return false;
    } else if (!id.equals(other.id)) return false;
    return true;
  }

  @Override
  public String toString() {
    return (
      "AvaliacaoFisica [altura=" +
      altura +
      ", aluno=" +
      aluno +
      ", dataDaAvaliacao=" +
      dataDaAvaliacao +
      ", id=" +
      id +
      ", peso=" +
      peso +
      "]"
    );
  }
}
