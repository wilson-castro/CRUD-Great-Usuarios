package io.github.wilsoncastro.backend.rest.usuarios;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.github.wilsoncastro.backend.model.Usuario;

import java.time.LocalDate;

public class UsuarioFormRequest {
    private Long id;
    private String nome;
    private String cpf;
    private String rg;
    private String nomeMae;

    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate dataNascimento;
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate dataCadastro;

    public UsuarioFormRequest() {
        super();
    }

    public UsuarioFormRequest(Long id, String nome, String cpf, String rg, String nomeMae, LocalDate dataNascimento, LocalDate dataCadastro) {
        this.id = id;
        this.nome = nome;
        this.cpf = cpf;
        this.rg = rg;
        this.nomeMae = nomeMae;
        this.dataNascimento = dataNascimento;
        this.dataCadastro = dataCadastro;
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

    public String getRg() {
        return rg;
    }

    public void setRg(String rg) {
        this.rg = rg;
    }

    public String getNomeMae() {
        return nomeMae;
    }

    public void setNomeMae(String nomeMae) {
        this.nomeMae = nomeMae;
    }

    public LocalDate getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(LocalDate dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public LocalDate getDataCadastro() {
        return dataCadastro;
    }

    public void setDataCadastro(LocalDate dataCadastro) {
        this.dataCadastro = dataCadastro;
    }

    public Usuario toMoldel() {
        return new Usuario(id, dataNascimento, cpf, rg, nome, nomeMae, dataCadastro);
    }

    public static UsuarioFormRequest fromModel(Usuario usuario) {
        return new UsuarioFormRequest(usuario.getId(), usuario.getNome(), usuario.getCpf(),usuario.getRg(), usuario.getNomeDaMae(), usuario.getNascimento(), usuario.getDataCadastro());
    }
}
