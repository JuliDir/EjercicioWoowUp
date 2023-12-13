package logica;
import dominio.*;
import util.AlertaComparator;
import java.util.ArrayList;
import java.util.List;

public class ExpertoTema extends Experto<Tema> {
    @Override
    public void guardar(Tema tema) {
        BDSimulada.temas.add(tema);
    }

    @Override
    public void guardarTodos(List<Tema> temas) {
        BDSimulada.temas.addAll(temas);
    }

    public void registrarUsuarioATema(Tema temaDTO, Usuario usuario) {
        boolean encontrado = false;
        for (Tema tema : BDSimulada.temas) {
            if (tema.equals(temaDTO)) {
                tema.addUsuario(usuario);
                encontrado = true;
                break;
            }
        }
        if (!encontrado) {
            throw new RuntimeException("No fue posible encontrar el Tema.");
        }
    }

    public List<Alerta> obtenerAlertasNoExpiradas(Tema tema) {
        List<Alerta> alertasNoExpiradas = new ArrayList<>();

        for (AlertaUsuario alertaUsuario : BDSimulada.alertaUsuarios) {
            if (alertaUsuario.getAlerta().getTema().equals(tema) && !alertaUsuario.getAlerta().haExpirado()) {
                alertasNoExpiradas.add(alertaUsuario.getAlerta());
            }
        }

        alertasNoExpiradas.sort(new AlertaComparator());
        return alertasNoExpiradas;
    }
}
