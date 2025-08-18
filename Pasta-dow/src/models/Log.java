package models;

import java.time.LocalDateTime;

public class Log {
    private Usuario usuario;
    private LocalDateTime dataHora;

    public Log(Usuario usuario, LocalDateTime dataHora) {
        this.usuario = usuario;
        this.dataHora = dataHora;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public LocalDateTime getDataHora() {
        return dataHora;
    }

    @Override
    public String toString() {
        return "Log{" +
                "usuario=" + usuario.getNome() +
                ", dataHora=" + dataHora +
                '}';
    }
}
