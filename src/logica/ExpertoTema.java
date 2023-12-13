package logica;
import dominio.*;
import util.AlertaComparator;
import java.util.ArrayList;
import java.util.List;

public class ExpertoTema implements Experto<Tema> {
    @Override
    public void guardar(Tema tema) {
        BDSimulada.temas.add(tema);
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
