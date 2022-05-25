package me.dio.academia.digital.entity.form;

public class MatriculaForm {

  private Long alunoId;

  public MatriculaForm() {}

  public MatriculaForm(Long alunoId) {
    this.alunoId = alunoId;
  }

  public Long getAlunoId() {
    return alunoId;
  }

  public void setAlunoId(Long alunoId) {
    this.alunoId = alunoId;
  }
}
