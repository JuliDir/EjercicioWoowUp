package dominio;

import java.time.ZonedDateTime;

public class AlertaUsuario {
    private Alerta alerta;
    private Usuario usuario;
    private ZonedDateTime fechaHoraAlerta;
    private boolean leida;

    public AlertaUsuario() {}

    public void setAlerta(Alerta alerta) {
        this.alerta = alerta;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public void setLeida(boolean leida) {
        this.leida = leida;
    }

    public void setFechaHoraAlerta(ZonedDateTime fechaHoraAlerta) {
        this.fechaHoraAlerta = fechaHoraAlerta;
    }

    public boolean isLeida() {
        return leida;
    }

    public Alerta getAlerta() {
        return alerta;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public ZonedDateTime getFechaHoraAlerta() {
        return fechaHoraAlerta;
    }
}
