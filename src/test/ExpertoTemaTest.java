package test;

import dominio.*;
import logica.ExpertoTema;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.time.ZonedDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

class ExpertoTemaTest {

    private ExpertoTema expertoTema;

    @BeforeEach
    void setUp() {
        expertoTema = new ExpertoTema();
        BDSimulada.usuarios.clear();
        BDSimulada.alertaUsuarios.clear();
        BDSimulada.alertas.clear();
        BDSimulada.temas.clear();
    }

    @Test
    void guardarTema() {
        Tema tema = new Tema();
        expertoTema.guardar(tema);

        assertTrue(BDSimulada.temas.contains(tema));
    }

    @Test
    void guardarTodosTemas() {
        Tema tema1 = new Tema();
        Tema tema2 = new Tema();

        expertoTema.guardarTodos(List.of(tema1, tema2));

        assertTrue(BDSimulada.temas.contains(tema1));
        assertTrue(BDSimulada.temas.contains(tema2));
    }

    @Test
    void registrarUsuarioATemaExistente() {
        Tema tema = new Tema();
        Usuario usuario = new Usuario();

        expertoTema.guardar(tema);
        expertoTema.registrarUsuarioATema(tema, usuario);

        assertTrue(tema.getUsuarios().contains(usuario));
    }

    @Test
    void registrarUsuarioATemaNoExistente() {
        Tema temaDTO = new Tema();
        Usuario usuario = new Usuario();

        assertThrows(RuntimeException.class, () -> expertoTema.registrarUsuarioATema(temaDTO, usuario));
    }

    @Test
    void obtenerAlertasNoExpiradas() {
        Tema tema = new Tema();
        Alerta alerta1 = new Alerta();
        Alerta alerta2 = new Alerta();
        Alerta alerta3 = new Alerta();
        Alerta alerta4 = new Alerta();
        TipoAlerta tipoAlertaUrgente = new TipoAlerta();
        TipoAlerta tipoAlertaInformativa = new TipoAlerta();
        tipoAlertaUrgente.setNombre("URGENTE");
        tipoAlertaInformativa.setNombre("INFORMATIVA");

        // Simular alertas no expiradas
        alerta1.setTema(tema);
        alerta1.setTipoAlerta(tipoAlertaInformativa);
        alerta1.setFechaHoraExpiracion(ZonedDateTime.now().minusHours(1L));
        alerta2.setTema(tema);
        alerta2.setTipoAlerta(tipoAlertaInformativa);
        alerta2.setFechaHoraExpiracion(ZonedDateTime.now().plusHours(1L));
        alerta3.setTema(tema);
        alerta3.setTipoAlerta(tipoAlertaUrgente);
        alerta3.setFechaHoraExpiracion(ZonedDateTime.now().minusHours(1L));
        alerta4.setTema(tema);
        alerta4.setTipoAlerta(tipoAlertaUrgente);
        alerta4.setFechaHoraExpiracion(ZonedDateTime.now().minusHours(1L));

        BDSimulada.alertaUsuarios.add(new AlertaUsuario(alerta1, new Usuario()));
        BDSimulada.alertaUsuarios.add(new AlertaUsuario(alerta2, new Usuario()));
        BDSimulada.alertaUsuarios.add(new AlertaUsuario(alerta3, new Usuario()));
        BDSimulada.alertaUsuarios.add(new AlertaUsuario(alerta4, new Usuario()));

        List<Alerta> alertasNoExpiradas = expertoTema.obtenerAlertasNoExpiradas(tema);

        assertFalse(alertasNoExpiradas.isEmpty());
        assertEquals(3, alertasNoExpiradas.size());

        //Buscar el índice donde terminan las alertas urgentes
        int indexFinUrgentes = 0;
        for (int i = 0; i < alertasNoExpiradas.size(); i++) {
            if (!"URGENTE".equals(alertasNoExpiradas.get(i).getTipoAlerta().getNombre())) {
                indexFinUrgentes = i;
                break;
            }
        }

        //Afirmar que todas las alertas hasta el índiceFinUrgentes tienen TipoAlerta urgente
        for (int i = 0; i < indexFinUrgentes; i++) {
            assertEquals("URGENTE", alertasNoExpiradas.get(i).getTipoAlerta().getNombre());
        }

        //Afirmar que las alertas restantes tienen TipoAlerta informativa
        for (int i = indexFinUrgentes; i < alertasNoExpiradas.size(); i++) {
            assertEquals("INFORMATIVA", alertasNoExpiradas.get(i).getTipoAlerta().getNombre());
        }
    }

}
