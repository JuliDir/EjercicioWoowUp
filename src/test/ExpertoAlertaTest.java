package test;
import dominio.*;
import logica.ExpertoAlerta;
import logica.ExpertoTema;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ExpertoAlertaTest {

    private ExpertoAlerta expertoAlerta;
    private ExpertoTema expertoTema;

    @BeforeEach
    void setUp() {
        expertoAlerta = new ExpertoAlerta();
        expertoTema = new ExpertoTema();
        BDSimulada.usuarios.clear();
        BDSimulada.alertaUsuarios.clear();
        BDSimulada.alertas.clear();
        BDSimulada.temas.clear();
    }

    @Test
    void guardarAlerta() {
        Alerta alerta = new Alerta();
        expertoAlerta.guardar(alerta);

        assertTrue(BDSimulada.alertas.contains(alerta));
    }

    @Test
    void guardarTodosAlertas() {
        Alerta alerta1 = new Alerta();
        Alerta alerta2 = new Alerta();

        expertoAlerta.guardarTodos(List.of(alerta1, alerta2));

        assertTrue(BDSimulada.alertas.contains(alerta1));
        assertTrue(BDSimulada.alertas.contains(alerta2));
    }

    @Test
    void agregarAlertaTema() {
        Alerta alerta = new Alerta();
        Tema tema = new Tema();
        Usuario usuario1 = new Usuario("Franco", "Asis");
        Usuario usuario2 = new Usuario("Julian", "Di Rocco");
        tema.addUsuario(usuario1);
        tema.addUsuario(usuario2);

        expertoTema.guardar(tema);
        expertoAlerta.agregarAlertaTema(alerta, tema);

        assertTrue(BDSimulada.alertas.contains(alerta));

        for (Usuario usuario : tema.getUsuarios()) {
            assertTrue(BDSimulada.alertaUsuarios.stream()
                    .anyMatch(au -> au.getUsuario().equals(usuario) && au.getAlerta().equals(alerta)));
        }
    }

    @Test
    void agregarAlertaUsuarioEspecifico() {
        Alerta alerta = new Alerta();
        Tema tema = new Tema();
        Usuario usuario = new Usuario();

        expertoTema.guardar(tema);

        expertoAlerta.agregarAlertaUsuarioEspeecifico(alerta, tema, usuario);

        assertTrue(BDSimulada.alertas.contains(alerta));
        assertTrue(BDSimulada.alertaUsuarios.stream()
                .anyMatch(au -> au.getUsuario().equals(usuario) && au.getAlerta().equals(alerta)));
    }

    @Test
    void alertarUsuarios() {
        Alerta alerta = new Alerta();
        Usuario usuario = new Usuario();
        AlertaUsuario alertaUsuario = new AlertaUsuario();
        alertaUsuario.setUsuario(usuario);
        alertaUsuario.setAlerta(alerta);
        alertaUsuario.setLeida(false);
        BDSimulada.alertaUsuarios.add(alertaUsuario);

        expertoAlerta.alertarUsuarios(alerta);

        assertNotNull(alertaUsuario.getFechaHoraAlerta());
    }
}


