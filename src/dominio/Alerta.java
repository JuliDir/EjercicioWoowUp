package dominio;

import java.time.ZonedDateTime;

public class Alerta {
    private TipoAlerta tipoAlerta;
    private ZonedDateTime fechaHoraExpiracion;
    private ZonedDateTime fechaHoraInicio;
    private Usuario usuarioEspecifico;

    public Alerta() {}

    public Alerta(ZonedDateTime fechaHoraExpiracion, ZonedDateTime fechaHoraInicio, Usuario usuarioEspecifico, TipoAlerta tipoAlerta) {
        this.tipoAlerta = tipoAlerta;
        this.fechaHoraExpiracion = fechaHoraExpiracion;
        this.fechaHoraInicio = fechaHoraInicio;
        this.usuarioEspecifico = usuarioEspecifico;
    }

    public void setFechaHoraExpiracion(ZonedDateTime fechaHoraExpiracion) {
        this.fechaHoraExpiracion = fechaHoraExpiracion;
    }

    public void setFechaHoraInicio(ZonedDateTime fechaHoraInicio) {
        this.fechaHoraInicio = fechaHoraInicio;
    }

    public void setUsuarioEspecifico(Usuario usuarioEspecifico) {
        this.usuarioEspecifico = usuarioEspecifico;
    }

    public void setTipoAlerta(TipoAlerta tipoAlerta) {
        this.tipoAlerta = tipoAlerta;
    }

    public Usuario getUsuarioEspecifico() {
        return usuarioEspecifico;
    }

    public ZonedDateTime getFechaHoraExpiracion() {
        return fechaHoraExpiracion;
    }

    public ZonedDateTime getFechaHoraInicio() {
        return fechaHoraInicio;
    }

    public TipoAlerta getTipoAlerta() {
        return tipoAlerta;
    }
}
