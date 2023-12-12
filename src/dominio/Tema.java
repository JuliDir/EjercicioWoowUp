package dominio;

import java.util.List;
import java.util.Set;

public class Tema {
    private String nombre;
    private String descripcion;
    private List<Alerta> alertas;
    private Set<Usuario> usuarios;

    public Tema() {

    }

    public Tema(String nombre, String descripcion) {
        this.nombre = nombre;
        this.descripcion = descripcion;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public String getNombre() {
        return nombre;
    }

    public List<Alerta> getAlertas() {
        return alertas;
    }

    public Set<Usuario> getUsuarios() {
        return usuarios;
    }

    public void addAlerta(Alerta alerta) {
        alertas.add(alerta);
    }

    public void addUsuario(Usuario usuario) {
        usuarios.add(usuario);
    }
}
