package util;

import dominio.Alerta;
import dominio.AlertaUsuario;
import dominio.TipoAlerta;

import java.util.Comparator;

public class AlertaComparator implements Comparator<Alerta> {

    private final String ALERTA_URGENTE = "URGENTE";
    private final String ALERTA_INFORMATIVA = "INFORMATIVA";

    @Override
    public int compare(Alerta a1, Alerta a2) {
        int orderA1 = getTipoAlertaOrder(a1.getTipoAlerta());
        int orderA2 = getTipoAlertaOrder(a2.getTipoAlerta());
        return Integer.compare(orderA1, orderA2);
    }

    private int getTipoAlertaOrder(TipoAlerta tipoAlerta) {
        switch (tipoAlerta.getNombre()) {
            case ALERTA_URGENTE:
                return 0;
            case ALERTA_INFORMATIVA:
                return 1;
            default:
                return 2;
        }
    }
}
