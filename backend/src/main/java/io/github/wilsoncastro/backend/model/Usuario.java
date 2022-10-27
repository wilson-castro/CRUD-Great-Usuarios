package io.github.wilsoncastro.backend.model;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name="usuarios")
public class Usuario {

    public Usuario() {
        super();
    }

    public Usuario(LocalDate nascimento, String cpf, String rg, String nome, String nomeDaMae, LocalDate dataCadastro) {
        this.nascimento = nascimento;
        this.cpf = cpf;
        this.rg = rg;
        this.nome = nome;
        this.nomeMae = nomeDaMae;
        this.dataCadastro = dataCadastro;
    }

    public Usuario(LocalDate nascimento, String cpf, String rg, String nome, String nomeDaMae) {
        this.nascimento = nascimento;
        this.cpf = cpf;
        this.rg = rg;
        this.nome = nome;
        this.nomeMae = nomeDaMae;
    }

    public Usuario(Long id, LocalDate nascimento, String cpf, String rg, String nome, String nomeDaMae, LocalDate dataCadastro) {
        this.id = id;
        this.nascimento = nascimento;
        this.cpf = cpf;
        this.rg = rg;
        this.nome = nome;
        this.nomeMae = nomeDaMae;
        this.dataCadastro = dataCadastro;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="data_nascimento")
    private LocalDate nascimento;

    @Column(unique = true)
    private String cpf;

    @Column(unique = true)
    private String rg;
    private String nome;

    @Column(name="nome_da_mae")
    private String nomeMae;

    @Column(name="data_cadastro")
    private LocalDate dataCadastro;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getNascimento() {
        return nascimento;
    }

    public void setNascimento(LocalDate nascimento) {
        this.nascimento = nascimento;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getRg() {
        return rg;
    }

    public void setRg(String rg) {
        this.rg = rg;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getNomeDaMae() {
        return nomeMae;
    }

    public void setNomeDaMae(String nomeDaMae) {
        this.nomeMae = nomeDaMae;
    }

    public LocalDate getDataCadastro() {
        return dataCadastro;
    }

    public void setDataCadastro(LocalDate dataCadastro) {
        this.dataCadastro = dataCadastro;
    }

    @PrePersist
    public void prePersist() {
        setDataCadastro(LocalDate.now());
    }

    @Override
    public String toString() {
        return "Usuario{" +
                "id=" + id +
                ", nascimento=" + nascimento +
                ", cpf='" + cpf + '\'' +
                ", rg='" + rg + '\'' +
                ", nome='" + nome + '\'' +
                ", nomeDaMae='" + nomeMae + '\'' +
                ", dataCadastro=" + dataCadastro +
                '}';
    }
}
