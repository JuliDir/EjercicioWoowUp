package logica;
import dominio.Alerta;
import dominio.AlertaUsuario;
import dominio.Tema;
import dominio.Usuario;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.*;
import dominio.BDSimulada;

public class ExpertoAlerta extends Experto<Alerta>{

    @Override
    public void guardar(Alerta alerta) {
        BDSimulada.alertas.add(alerta);
    }

    @Override
    public void guardarTodos(List<Alerta> alertas) {
        BDSimulada.alertas.addAll(alertas);
    }

    public void agregarAlertaTema(Alerta alerta, Tema temaDTO) {
        boolean encontrado = false;
        for (Tema tema : BDSimulada.temas) {
            if (tema.equals(temaDTO)) {
                alerta.setTema(tema);
                encontrado = true;
                guardar(alerta);
                break;
            }
        }
        if (!encontrado) {
            throw new RuntimeException("El tema no pudo ser encontrado.");
        }

        //Buscar a todos los usuarios sucriptos a ese tema
        Set<Usuario> usuarios = temaDTO.getUsuarios();

        //Por cada usuario creo la clase intermedia y lo guardo en la lista estatica
        if (usuarios != null) {
            for (Usuario usuario : usuarios) {
                AlertaUsuario alertaUsuario = new AlertaUsuario();
                alertaUsuario.setAlerta(alerta);
                alertaUsuario.setUsuario(usuario);
                alertaUsuario.setLeida(false);
                BDSimulada.alertaUsuarios.add(alertaUsuario);
            }
        }
    }

    public void agregarAlertaUsuarioEspeecifico(Alerta alerta, Tema temaDTO, Usuario usuario) {
        boolean encontrado = false;
        for (Tema tema : BDSimulada.temas) {
            if (tema.equals(temaDTO)) {
                alerta.setTema(tema);
                encontrado = true;
                guardar(alerta);
                break;
            }
        }
        if (!encontrado) {
            throw new RuntimeException("El tema no pudo ser encontrado.");
        }

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
