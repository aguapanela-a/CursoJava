package platzi.play.project.DAO;

import platzi.play.project.contenido.Contenido;

import java.util.List;

public interface ContenidoDAO {
    List<Contenido> cargarContenido();
    void agregarContenido(Contenido contenido);
    void eliminarContenido(int id);
    void actualizarContenido(Contenido contenido);
}
