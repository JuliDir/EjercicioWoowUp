package dominio;

import java.time.ZonedDateTime;
import java.util.Objects;

public class Alerta {
    private TipoAlerta tipoAlerta;
    private ZonedDateTime fechaHoraExpiracion;
    private ZonedDateTime fechaHoraInicio;

    private Tema tema;

    public Alerta() {}

    public Alerta(ZonedDateTime fechaHoraExpiracion, ZonedDateTime fechaHoraInicio, TipoAlerta tipoAlerta) {
        this.tipoAlerta = tipoAlerta;
        this.fechaHoraExpiracion = fechaHoraExpiracion;
        this.fechaHoraInicio = fechaHoraInicio;
    }

    public void setFechaHoraExpiracion(ZonedDateTime fechaHoraExpiracion) {
        this.fechaHoraExpiracion = fechaHoraExpiracion;
    }

    public void setFechaHoraInicio(ZonedDateTime fechaHoraInicio) {
        this.fechaHoraInicio = fechaHoraInicio;
    }

    public void setTipoAlerta(TipoAlerta tipoAlerta) {
        this.tipoAlerta = tipoAlerta;
    }

    public void setTema(Tema tema) {
        this.tema = tema;
    }

    public Tema getTema() {
        return tema;
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

    public boolean haExpirado() {
        if (ZonedDateTime.now().isBefore(fechaHoraExpiracion)) {
            return true;
        }
        return false;
    }
}
