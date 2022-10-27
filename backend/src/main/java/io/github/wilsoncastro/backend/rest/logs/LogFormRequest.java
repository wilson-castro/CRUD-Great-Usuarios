package io.github.wilsoncastro.backend.rest.logs;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.github.wilsoncastro.backend.model.Log;

import java.time.LocalDate;
import java.time.LocalTime;

public class LogFormRequest {
    private Long id;
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate data;
    @JsonFormat(pattern = "HH:mm:ss")
    private LocalTime hora;
    private String tipo;
    private String descricao;

    public LogFormRequest() { super(); }

    public LogFormRequest(Long id, LocalDate data, LocalTime hora, String tipo, String descricao) {
        this.id = id;
        this.data = data;
        this.hora = hora;
        this.tipo = tipo;
        this.descricao = descricao;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getData() {
        return data;
    }

    public void setData(LocalDate data) {
        this.data = data;
    }

    public LocalTime getHora() {
        return hora;
    }

    public void setHora(LocalTime hora) {
        this.hora = hora;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Log toModel(){
        return new Log(id, data, hora, tipo, descricao);
    }

    public static LogFormRequest fromModel(Log log){
        return new LogFormRequest(log.getId(), log.getData(), log.getHora() ,log.getTipo(), log.getDescricao());
    }
}
