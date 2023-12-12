package logica;

public class FabricaExpertos {
    private static FabricaExpertos fabricaExpertos;
    private final String EXPERTO_ALERTA = "ExpertoAlerta";
    private final String EXPERTO_TEMA = "ExpertoTema";
    private final String EXPERTO_USUARIO = "ExpertoUsuario";

    public static FabricaExpertos getInstance() {
        if (fabricaExpertos == null) {
            fabricaExpertos = new FabricaExpertos();
        }
        return fabricaExpertos;
    }

    public Experto getExperto(String experto) {
        switch (experto) {
            case EXPERTO_ALERTA:
                return new ExpertoAlerta();
            case EXPERTO_TEMA:
                return new ExpertoTema();
            case EXPERTO_USUARIO:
                return new ExpertoUsuario();
            default:
                throw new IllegalArgumentException("Experto no válido: " + experto);
        }
    }


}
