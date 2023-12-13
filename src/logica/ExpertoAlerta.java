package logica;
import dominio.Alerta;
import dominio.AlertaUsuario;
import dominio.Tema;
import dominio.Usuario;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.*;
import dominio.BDSimulada;

public class ExpertoAlerta implements Experto<Alerta>{

    @Override
    public void guardar(Alerta alerta) {
        BDSimulada.alertas.add(alerta);
    }

    public void agregarAlertaTema(Alerta alerta, Tema tema) {
        //Añado la alerta al tema
        alerta.setTema(tema);

        //Buscar a todos los usuarios sucriptos a ese tema
        Set<Usuario> usuarios = tema.getUsuarios();

        //Por cada usuario creo la clase intermedia y lo guardo en la lista estatica
        for (Usuario usuario : usuarios) {
            AlertaUsuario alertaUsuario = new AlertaUsuario();
            alertaUsuario.setAlerta(alerta);
            alertaUsuario.setUsuario(usuario);
            alertaUsuario.setLeida(false);
            BDSimulada.alertaUsuarios.add(alertaUsuario);
        }
    }

    public void agregarAlertaUsuarioEspeecifico(Alerta alerta, Tema tema, Usuario usuario) {
        //Añado la alerta al tema
        alerta.setTema(tema);

        //Creo la clase intermedia pero solo para ese usuario en especifico
        AlertaUsuario alertaUsuario = new AlertaUsuario();
        alertaUsuario.setUsuario(usuario);
        alertaUsuario.setAlerta(alerta);
        alertaUsuario.setLeida(false);
        BDSimulada.alertaUsuarios.add(alertaUsuario);
    }

    public void alertarUsuarios(Alerta alerta) {

        // Este método simula la búsqueda en una base de datos de las AlertaUsuario por alerta
        for (AlertaUsuario alertaUsuario : BDSimulada.alertaUsuarios) {
            if (alertaUsuario.getAlerta().equals(alerta)) {
                // A cada AlertaUsuario le seteo la fecha de alerta para simular que el usuario fue alertado
                alertaUsuario.setFechaHoraAlerta(ZonedDateTime.now(ZoneId.of("UTC")));
            }
        }
    }

}
