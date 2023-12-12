package dominio;

import java.util.HashSet;
import java.util.Set;

public class Usuario {
    private String nombre;
    private String apellido;
    private Set<Tema> temas;

    public Usuario() {
        temas = new HashSet<>();
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getNombre() {
        return nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public Set<Tema> getTemas() {
        return temas;
    }
}
