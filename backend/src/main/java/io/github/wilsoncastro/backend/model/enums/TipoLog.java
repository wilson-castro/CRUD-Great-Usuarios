package io.github.wilsoncastro.backend.model.enums;

public enum TipoLog {
    INSERT("INSERT"), SELECT("SELECT"), UPDATE("UPDATE"), DELETE("DELETE");

    private String descricao;
     TipoLog(String descricao) {
        this.descricao = descricao;
    }

    public String getDescricao() {
         return this.descricao;
    }
}
