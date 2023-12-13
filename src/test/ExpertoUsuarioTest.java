package test;

import dominio.*;
import logica.ExpertoAlerta;
import logica.ExpertoUsuario;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.ZonedDateTime;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

class ExpertoUsuarioTest {

    private ExpertoUsuario expertoUsuario;

    @BeforeEach
    void setUp() {
        expertoUsuario = new ExpertoUsuario();
        BDSimulada.usuarios.clear();
        BDSimulada.alertaUsuarios.clear();
        BDSimulada.alertas.clear();
        BDSimulada.temas.clear();
    }

    @Test
    void guardarUsuario() {
        Usuario usuario = new Usuario();
        expertoUsuario.guardar(usuario);

        assertTrue(BDSimulada.usuarios.contains(usuario));
    }

    @Test
    void guardarTodosUsuarios() {
        Usuario usuario1 = new Usuario();
        Usuario usuario2 = new Usuario();

        expertoUsuario.guardarTodos(List.of(usuario1, usuario2));

        assertTrue(BDSimulada.usuarios.contains(usuario1));
        assertTrue(BDSimulada.usuarios.contains(usuario2));
    }

    @Test
    void leerAlertaExistente() {
        Usuario usuario = new Usuario();
        Alerta alerta = new Alerta();
        alerta.setFechaHoraExpiracion(ZonedDateTime.now().minusHours(1));
        AlertaUsuario alertaUsuario = new AlertaUsuario(alerta, usuario);
        BDSimulada.alertaUsuarios.add(alertaUsuario);

        expertoUsuario.leerAlerta(alerta, usuario);

        assertTrue(alertaUsuario.isLeida());
    }

    @Test
    void leerAlertaNoExistente() {
        Usuario usuario = new Usuario();
        Alerta alerta = new Alerta();

        assertThrows(RuntimeException.class, () -> expertoUsuario.leerAlerta(alerta, usuario));
    }

    @Test
    void obtenerAlertasNoExpiradasYNoLeidas() {
        Usuario usuario = new Usuario();
        Alerta alerta1 = new Alerta();
        Alerta alerta2 = new Alerta();
        Alerta alerta3 = new Alerta();
        Alerta alerta4 = new Alerta();
        TipoAlerta tipoAlertaUrgente = new TipoAlerta();
        TipoAlerta tipoAlertaInformativa = new TipoAlerta();
        tipoAlertaUrgente.setNombre("URGENTE");
        tipoAlertaInformativa.setNombre("INFORMATIVA");

        alerta1.setTipoAlerta(tipoAlertaInformativa);
        alerta1.setFechaHoraExpiracion(ZonedDateTime.now().minusHours(1L));
        alerta2.setTipoAlerta(tipoAlertaInformativa);
        alerta2.setFechaHoraExpiracion(ZonedDateTime.now().plusHours(1L));
        alerta3.setTipoAlerta(tipoAlertaUrgente);
        alerta3.setFechaHoraExpiracion(ZonedDateTime.now().minusHours(1L));
        alerta4.setTipoAlerta(tipoAlertaUrgente);
        alerta4.setFechaHoraExpiracion(ZonedDateTime.now().minusHours(1L));

        AlertaUsuario alertaUsuario1 = new AlertaUsuario(alerta1, usuario);
        AlertaUsuario alertaUsuario2 = new AlertaUsuario(alerta2, usuario);
        AlertaUsuario alertaUsuario3 = new AlertaUsuario(alerta3, usuario);
        AlertaUsuario alertaUsuario4 = new AlertaUsuario(alerta4, usuario);
        alertaUsuario2.setLeida(true);
        alertaUsuario3.setLeida(true);
        alertaUsuario4.setLeida(true);
        BDSimulada.alertaUsuarios.addAll(List.of(alertaUsuario1, alertaUsuario2, alertaUsuario3, alertaUsuario4));

        List<Alerta> alertasNoExpiradasYNoLeidas = expertoUsuario.obtenerAlertasNoExpiradasYNoLeidas(usuario);

        assertEquals(1, alertasNoExpiradasYNoLeidas.size());
        assertEquals(alerta1, alertasNoExpiradasYNoLeidas.get(0));

        //Buscar el índice donde terminan las alertas urgentes
        int indexFinUrgentes = 0;
        for (int i = 0; i < alertasNoExpiradasYNoLeidas.size(); i++) {
            if (!"URGENTE".equals(alertasNoExpiradasYNoLeidas.get(i).getTipoAlerta().getNombre())) {
                indexFinUrgentes = i;
                break;
            }
        }

        //Afirmar que todas las alertas hasta el índiceFinUrgentes tienen TipoAlerta urgente
        for (int i = 0; i < indexFinUrgentes; i++) {
            assertEquals("URGENTE", alertasNoExpiradasYNoLeidas.get(i).getTipoAlerta().getNombre());
        }

        //Afirmar que las alertas restantes tienen TipoAlerta informativa
        for (int i = indexFinUrgentes; i < alertasNoExpiradasYNoLeidas.size(); i++) {
            assertEquals("INFORMATIVA", alertasNoExpiradasYNoLeidas.get(i).getTipoAlerta().getNombre());
        }
    }
}

