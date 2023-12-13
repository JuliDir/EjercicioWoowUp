package logica;

import java.util.List;

public abstract class Experto<T> {
   public abstract void guardar(T entidad);
   public abstract void guardarTodos(List<T> entidades);
}
