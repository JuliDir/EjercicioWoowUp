import dominio.Usuario;
import logica.Experto;
import logica.ExpertoUsuario;
import logica.FabricaExpertos;

public class WoowUp {
    public static void main (String args[]) {
        FabricaExpertos fabricaExpertos = FabricaExpertos.getInstance();

        Experto expertoUsuario = fabricaExpertos.getExperto("ExpertoUsuario");

    }
}
