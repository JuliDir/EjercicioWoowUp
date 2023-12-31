package logica;
import dominio.*;
import util.AlertaComparator;
import java.util.ArrayList;
import java.util.List;

public class ExpertoUsuario extends Experto<Usuario> {
    @Override
    public void guardar(Usuario usuario) {
        BDSimulada.usuarios.add(usuario);
    }

    @Override
    public void guardarTodos(List<Usuario> usuarios) {
        BDSimulada.usuarios.addAll(usuarios);
    }

    //La alerta puede ser leida mientras no haya expirado
    public void leerAlerta(Alerta alerta, Usuario usuario) {
        boolean encontrado = false;
        for (AlertaUsuario alertaUsuario : BDSimulada.alertaUsuarios) {
            if (alertaUsuario.getAlerta().equals(alerta) &&
                    alertaUsuario.getUsuario().equals(usuario) &&
                    !alertaUsuario.getAlerta().haExpirado()) {
                alertaUsuario.setLeida(true);
                encontrado = true;
                break;
            }
        }
        if (!encontrado) {
            throw new RuntimeException("AlertaUsuario no encontrado para la alerta y usuario proporcionados.");
        }
    }

    public List<Alerta> obtenerAlertasNoExpiradasYNoLeidas(Usuario usuario) {
        List<Alerta> alertasNoExpiradas = new ArrayList<>();

        for (AlertaUsuario alertaUsuario : BDSimulada.alertaUsuarios) {
            if (alertaUsuario.getUsuario().equals(usuario) &&
                    !alertaUsuario.getAlerta().haExpirado() &&
                    !alertaUsuario.isLeida()) {
                alertasNoExpiradas.add(alertaUsuario.getAlerta());
            }
        }

        alertasNoExpiradas.sort(new AlertaComparator());
        return alertasNoExpiradas;
    }
}
