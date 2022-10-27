package io.github.wilsoncastro.backend.model;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Table(name = "logs")
public class Log {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDate data;
    private LocalTime hora;
    private String tipo;
    private String descricao;

    public Log(){
        super();
    }

    public Log(LocalDate data, LocalTime hora, String tipo, String descricao) {
        this.data = data;
        this.hora = hora;
        this.tipo = tipo;
        this.descricao = descricao;
    }

    public Log(Long id, LocalDate data, LocalTime hora, String tipo, String descricao) {
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

    @PrePersist
    public void prePersist() {
        setData(LocalDate.now());
        setHora(LocalTime.now());
    }

    @Override
    public String toString() {
        return "Log{" +
                "id=" + id +
                ", data=" + data +
                ", hora=" + hora +
                ", tipo='" + tipo + '\'' +
                ", descricao='" + descricao + '\'' +
                '}';
    }
}
